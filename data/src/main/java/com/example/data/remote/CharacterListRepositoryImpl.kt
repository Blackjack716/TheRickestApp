package com.example.data.remote

import com.example.data.remote.api.RickAndMortyApi
import com.example.data.remote.model.Characters
import com.example.data.remote.model.toEntity
import com.example.domain.model.CharacterItem
import com.example.domain.repository.CharacterListRepository
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterListRepositoryImpl @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi
) : CharacterListRepository {

    private val _characters = MutableStateFlow<List<Characters>>(emptyList())

    init {
        CoroutineScope(Dispatchers.IO).launch {
            when (val response = rickAndMortyApi.getAllCharacters()) {
                is NetworkResponse.Success -> {
                    _characters.emit(response.body.results)
                    println(response.body)
                }

                else -> {}
            }
        }
    }

    override suspend fun getAllCharacters(): Flow<List<CharacterItem>> {
        return _characters.asStateFlow().mapNotNull { charactersList -> charactersList.map { it.toEntity() } }
    }

    override suspend fun serCharacterAsFavourite(isFav: Boolean) {
        TODO("Not yet implemented")
    }
}