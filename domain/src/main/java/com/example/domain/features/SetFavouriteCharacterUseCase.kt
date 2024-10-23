package com.example.domain.features

import com.example.domain.model.CharacterItem
import com.example.domain.repository.CharacterListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SetFavouriteCharacterUseCase @Inject constructor(
    private val characterListRepository: CharacterListRepository
) {
    fun execute(characterItem: CharacterItem, isFavourite: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            characterListRepository.setCharacterAsFavourite(characterItem, isFavourite)
        }
    }
}