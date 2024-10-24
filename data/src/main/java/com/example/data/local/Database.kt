package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.CharacterDao
import com.example.data.local.model.CharacterEntity

@Database(
    entities = [CharacterEntity::class],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun CharacterDao() : CharacterDao

    companion object {
        const val DATABASE_NAME = "RickNMorty-database"
    }
}