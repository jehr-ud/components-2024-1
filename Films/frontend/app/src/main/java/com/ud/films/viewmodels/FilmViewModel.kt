package com.example.films.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ud.films.models.Film
import com.ud.films.repositories.FilmsRepository
import kotlinx.coroutines.launch

class FilmViewModel : ViewModel() {
    private val filmsRepository = FilmsRepository()

    private val _filmsLiveData = MutableLiveData<List<Film>>()
    val filmsLiveData: LiveData<List<Film>> = _filmsLiveData

    init {
        loadFilms()
    }

    fun loadFilms() {
        viewModelScope.launch {
            val films = filmsRepository.getFilms()
            _filmsLiveData.value = films
        }
    }
}
