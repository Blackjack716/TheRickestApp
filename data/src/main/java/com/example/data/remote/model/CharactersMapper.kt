package com.example.data.remote.model

import com.example.domain.model.CharacterItem

fun Characters.toDomain(): CharacterItem {
    return CharacterItem(
        id = this.id ?: 0,
        name = this.name ?: "",
        image = this.image ?: "",
        status = this.status ?: "",
        isFavourite = false
    )
}