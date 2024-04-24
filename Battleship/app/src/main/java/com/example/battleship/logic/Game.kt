package com.example.battleship.logic

import com.example.battleship.model.Enums.StatusCell

class Game{
    var board: Board
    var aliasMatch: String
    var canStart: Boolean = false
    var turn: String
    var finishAt: String
    val player1: Player
    var player2: Player?

    constructor() {
        board = Board()
        aliasMatch = ""
        turn = ""
        player1 = Player("", "")
        player2 = null
        finishAt = ""
    }

    constructor(
        board: Board,
        aliasMatch: String,
        canStart: Boolean = false,
        turn: String,
        player1: Player,
        player2: Player?,
        finishAt: String
    ) {
        this.board = board
        this.aliasMatch = aliasMatch
        this.canStart = canStart
        this.turn = turn
        this.player1 = player1
        this.player2 = player2
        this.finishAt = finishAt
    }

    fun generateCells(rows: Int, cols: Int){
        for (row in 1..rows){
            for (col in 1..cols){
                var cell = Cell(StatusCell.EMPTY, row, col)
                board.cells.add(cell)
            }
        }
    }
}
