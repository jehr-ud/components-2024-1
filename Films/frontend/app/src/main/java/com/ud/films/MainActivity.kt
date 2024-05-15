package com.ud.films

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.films.viewmodels.FilmViewModel
import com.ud.films.views.composable.FilmList

class MainActivity : ComponentActivity() {
    private val filmViewModel: FilmViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val films by filmViewModel.filmsLiveData.observeAsState(emptyList())
            FilmList(films = films)
        }
    }
}