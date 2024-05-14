package com.ud.films.repositories

import com.ud.films.models.Film
import com.ud.films.network.FilmsApi
import com.ud.films.network.RetrofitClient

class FilmsRepository {
    private val filmsApi: FilmsApi = RetrofitClient.filmsApi

    suspend fun getFilms(): List<Film> {
        return filmsApi.getAll()
    }
}