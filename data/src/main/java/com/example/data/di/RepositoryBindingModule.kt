package com.example.data.di

import com.example.data.remote.CharacterListRepositoryImpl
import com.example.domain.repository.CharacterListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryBindingModule {

    @Binds
    @Singleton
    abstract fun bindCharacterListRepository(impl: CharacterListRepositoryImpl): CharacterListRepository
}