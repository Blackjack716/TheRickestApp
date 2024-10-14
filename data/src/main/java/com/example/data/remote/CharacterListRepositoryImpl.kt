package com.example.data.remote

import com.example.data.local.dao.CharacterDao
import com.example.data.local.model.toDomain
import com.example.data.local.model.toEntity
import com.example.data.remote.api.RickAndMortyApi
import com.example.data.remote.model.Characters
import com.example.data.remote.model.toDomain
import com.example.domain.model.CharacterItem
import com.example.domain.repository.CharacterListRepository
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterListRepositoryImpl @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi,
    private val characterDao: CharacterDao
) : CharacterListRepository {

    private val _characters = MutableStateFlow<List<Characters>>(emptyList())
    private val _favouriteCharacters = MutableStateFlow<List<CharacterItem>>(emptyList())

    init {
        CoroutineScope(Dispatchers.IO).launch {
            when (val response = rickAndMortyApi.getAllCharacters()) {
                is NetworkResponse.Success -> {
                    _characters.emit(response.body.results)
                }
                else -> {}
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            characterDao.getFavouriteCharacters().distinctUntilChanged().collect { list ->
                _favouriteCharacters.emit(
                    list.map {
                        it.toDomain()
                    })
            }
        }
    }

    override suspend fun getAllCharacters(): Flow<List<CharacterItem>> {
        return combine(
            _characters,
            _favouriteCharacters
        ) { characterList, favouriteList ->
            Pair(characterList, favouriteList)
        }.map { (characterList, favouriteList) ->
            characterList.map { character ->
                if (favouriteList.map { it.id }.contains(character.id)) {
                    character.toDomain().copy(isFavourite = true)
                } else {
                    character.toDomain()
                }

            }
        }
    }

    override suspend fun setCharacterAsFavourite(characterItem: CharacterItem, isFav: Boolean) {
        if (isFav) {
            characterDao.addFavouriteCharacter(characterItem.toEntity().copy(isFavourite = true))
        } else {
            characterDao.delete(characterItem.toEntity())
        }
    }

    override suspend fun getFavouriteCharacters(): Flow<List<CharacterItem>> {
        return _favouriteCharacters.asStateFlow()
    }
}