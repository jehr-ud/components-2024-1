package com.example.battleship.logic

import com.example.battleship.logic.Enums.StatusCell

class Game (
    var board: Board,
    val player1: Player,
    val player2: Player) {

    fun generateCells(rows: Int, cols: Int){
        for (row in 1..rows){
            for (col in 1..cols){
                var cell = Cell(StatusCell.EMPTY)
                board.cells.add(cell)
            }
        }
    }
}