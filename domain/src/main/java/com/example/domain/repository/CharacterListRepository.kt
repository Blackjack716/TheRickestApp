package com.example.domain.repository

import com.example.domain.model.CharacterItem
import kotlinx.coroutines.flow.Flow

interface CharacterListRepository {
    suspend fun getAllCharacters(): Flow<List<CharacterItem>>
    suspend fun setCharacterAsFavourite(characterItem: CharacterItem, isFav: Boolean)
    suspend fun getFavouriteCharacters(): Flow<List<CharacterItem>>
}