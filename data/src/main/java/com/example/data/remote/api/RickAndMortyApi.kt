package com.example.data.remote.api

import com.example.data.remote.ErrorResponse
import com.example.data.remote.model.CharactersDto
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("api/character")
    suspend fun getAllCharacters(): NetworkResponse<CharactersDto, ErrorResponse>
}