package com.ud.films.views.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ud.films.models.Film

@Composable
fun FilmList(films: List<Film>) {
    LazyColumn {
        items(films) { film ->
            FilmItem(film = film)
        }
    }
}

@Composable
fun FilmItem(film: Film) {
    Text(text = film.title, modifier = Modifier.padding(16.dp), color = Color.Green)
}
