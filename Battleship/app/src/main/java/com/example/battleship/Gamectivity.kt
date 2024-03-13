package com.example.battleship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.battleship.logic.Board
import com.example.battleship.logic.Game
import com.example.battleship.logic.Player

class Gamectivity : AppCompatActivity() {
    private lateinit var game: Game
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var rows = 10
        var cols = 10
        var board = Board(rows,cols)
        var player1 = Player("juan")
        var player2 = Player("pedro")

        game = Game(board, player1, player2)
        game.generateCells(rows, cols)

        setContent {
            BoardContent(game)
        }
    }
}

@Composable
fun BoardContent(game: Game){
    LazyColumn {
        items(game.board.rows) { row ->
            Row {
                repeat(game.board.cols) { col ->
                    Column {
                        Button(onClick = {
                            game.board.updateCell(row, col)
                        }) {
                            var position = (row * col) + col
                            var cell =  game.board.cells[position]
                            Text(text = "${cell.status.name[0]}")
                        }
                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun boardView(){
    //BoardContent()
}