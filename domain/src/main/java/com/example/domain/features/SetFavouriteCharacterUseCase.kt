package com.example.domain.features

import com.example.domain.model.CharacterItem
import com.example.domain.repository.CharacterListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetFavouriteCharacterUseCase @Inject constructor(
    private val characterListRepository: CharacterListRepository
) {
    suspend fun execute(isFavourite: Boolean) {
        return characterListRepository.serCharacterAsFavourite(isFavourite)
    }
}