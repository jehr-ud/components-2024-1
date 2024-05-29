package com.example.battleship.ui.composables.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.battleship.logic.Game
import com.example.battleship.model.Enums.StatusCell

@Composable
fun GameContent(game: Game, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(game.rows) { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(game.cols) { col ->

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                    ) {
                        Button(
                            onClick = { /* Acciones del juego */ },
                            modifier = Modifier.fillMaxSize(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = when (game.board.cells[row][col].status) { //Condicional para poner el color de la celda dependiendo del estado de la celda
                                    StatusCell.EMPTY -> Color.Gray
                                    StatusCell.OCCUPIED -> Color.Red
                                    StatusCell.HIT -> Color.Green
                                    StatusCell.MISSED -> Color.Blue
                                    else -> Color.Black
                                },
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "$row, $col")
                        }
                    }
                }
            }
        }
    }
}
