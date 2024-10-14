package com.example.domain.model

data class CharacterItem(
    val id: Int,
    val name: String,
    val status: String,
    val image: String,
    var isFavourite: Boolean
)
