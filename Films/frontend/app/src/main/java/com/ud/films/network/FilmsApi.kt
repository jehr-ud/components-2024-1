package com.ud.films.network

import com.ud.films.models.Film
import retrofit2.http.GET
import retrofit2.http.POST

interface FilmsApi {
    @GET("films")
    suspend fun getAll(): List<Film>
    @POST("films")
    suspend fun save(): Film
}