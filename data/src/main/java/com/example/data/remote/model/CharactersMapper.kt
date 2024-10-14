package com.example.data.remote.model

import com.example.domain.model.CharacterItem

fun CharacterItem.toEntity(): Characters {
    return Characters(
        id = this.id,
        name = this.name,
        image = this.image,
        status = this.status
    )
}

fun Characters.toEntity(): CharacterItem {
    return CharacterItem(
        id = this.id ?: 0,
        name = this.name ?: "",
        image = this.image ?: "",
        status = this.status ?: "",
        isFavourite = false
    )
}