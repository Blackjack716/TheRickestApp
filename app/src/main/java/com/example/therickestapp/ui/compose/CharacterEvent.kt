package com.example.therickestapp.ui.compose

import com.example.domain.model.CharacterItem

sealed class CharacterEvent {

    data class OnFavCharacterClicked(val character: CharacterItem, val isFavourite: Boolean) : CharacterEvent()
    data object OnFavListClicked : CharacterEvent()
    data object OnAllListClicked : CharacterEvent()
}