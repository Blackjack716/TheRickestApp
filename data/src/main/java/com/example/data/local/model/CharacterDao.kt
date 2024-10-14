package com.example.data.local.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Query("SELECT * FROM CharacterEntity WHERE isFav = 1")
    fun getFavouriteCharacters(): Flow<List<CharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavouriteCharacter(vararg characterEntity: CharacterEntity)

    @Delete
    fun delete(characterEntity: CharacterEntity)
}