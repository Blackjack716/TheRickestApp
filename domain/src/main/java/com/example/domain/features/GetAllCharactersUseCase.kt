package com.example.domain.features

import com.example.domain.model.CharacterItem
import com.example.domain.repository.CharacterListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(
    private val characterListRepository: CharacterListRepository
) {
    suspend fun execute(): Flow<List<CharacterItem>> {
        return characterListRepository.getAllCharacters()
    }
}