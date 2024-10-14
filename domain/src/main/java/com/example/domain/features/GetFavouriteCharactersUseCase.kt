package com.example.domain.features

import com.example.domain.model.CharacterItem
import com.example.domain.repository.CharacterListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetFavouriteCharactersUseCase @Inject constructor(
    private val characterListRepository: CharacterListRepository
) {
    suspend fun execute(): Flow<List<CharacterItem>> {
        return characterListRepository.getFavouriteCharacters()
    }
}