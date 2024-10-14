package com.example.domain.features

import com.example.domain.model.CharacterItem
import com.example.domain.repository.CharacterListRepository
import javax.inject.Inject

class SetFavouriteCharacterUseCase @Inject constructor(
    private val characterListRepository: CharacterListRepository
) {
    suspend fun execute(characterItem: CharacterItem, isFavourite: Boolean) {
        return characterListRepository.setCharacterAsFavourite(characterItem, isFavourite)
    }
}