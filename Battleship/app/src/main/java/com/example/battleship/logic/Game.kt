package com.example.battleship.logic

import com.example.battleship.model.Enums.StatusCell

class Game (
    var board: Board,
    var aliasMatch: String,
    var canStart: Boolean = false,
    var turn: String,
    val player1: Player,
    val player2: Player?) {

    fun generateCells(rows: Int, cols: Int){
        for (row in 1..rows){
            for (col in 1..cols){
                var cell = Cell(StatusCell.EMPTY, row, col)
                board.cells.add(cell)
            }
        }
    }
}