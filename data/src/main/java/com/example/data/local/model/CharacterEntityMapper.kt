package com.example.data.local.model

import com.example.domain.model.CharacterItem

fun CharacterItem.toEntity(): CharacterEntity {
    return CharacterEntity(
        id = this.id,
        name = this.name,
        photoUrl = this.image,
        status = this.status,
        isFavourite = this.isFavourite
    )
}

fun CharacterEntity.toDomain(): CharacterItem {
    return CharacterItem(
        id = this.id ?: 0,
        name = this.name ?: "",
        image = this.photoUrl ?: "",
        status = this.status ?: "",
        isFavourite = this.isFavourite ?: false
    )
}