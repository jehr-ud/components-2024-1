package com.example.battleship.logic

import com.example.battleship.model.Enums.ShipAlignment
import com.example.battleship.model.Enums.StatusCell
import com.example.battleship.model.Enums.TypeShip

class Game {
    var board: Board
    var aliasMatch: String
    var canStart: Boolean = false
    var turn: String
    var finishAt: String
    val player1: Player
    var player2: Player?
    var rows: Int = 10
    var cols: Int = 10

    constructor() {
        board = Board()
        aliasMatch = ""
        turn = ""
        player1 = Player("", "")
        player2 = null
        finishAt = ""
        rows = 10
        cols = 10
    }

    constructor(
        rows: Int,
        cols: Int,
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
        this.cols = cols
        this.rows = rows
    }

    fun generateCells() {
        for (row in 1..rows) {
            for (col in 1..cols) {
                var cell = Cell(StatusCell.EMPTY, row, col)
                board.cells.add(cell)
            }
        }
    }

    fun generateShips() {
        val ships1 = mutableListOf(
            Ship(
                TypeShip.CARRIER,
                ShipAlignment.VERTICAL,
                mutableListOf(
                    Cell(StatusCell.OCCUPIED, 1, 1),
                    Cell(StatusCell.OCCUPIED, 2, 1)
                )
            ),
            Ship(
                TypeShip.BATTLE_SHIP,
                ShipAlignment.VERTICAL,
                mutableListOf(Cell(StatusCell.OCCUPIED, 1, 1))
            ),
            Ship(
                TypeShip.CRUISE,
                ShipAlignment.VERTICAL,
                mutableListOf(Cell(StatusCell.OCCUPIED, 1, 1))
            ),
            Ship(
                TypeShip.DESTROYER,
                ShipAlignment.VERTICAL,
                mutableListOf(Cell(StatusCell.OCCUPIED, 1, 1))
            ),
            Ship(
                TypeShip.SUBMARINE,
                ShipAlignment.VERTICAL,
                mutableListOf(Cell(StatusCell.OCCUPIED, 1, 1))
            ),
        )
        val ships2 = mutableListOf(
            Ship(
                TypeShip.CARRIER,
                ShipAlignment.VERTICAL,
                mutableListOf(Cell(StatusCell.OCCUPIED, 1, 1))
            ),
            Ship(
                TypeShip.BATTLE_SHIP,
                ShipAlignment.VERTICAL,
                mutableListOf(Cell(StatusCell.OCCUPIED, 1, 1))
            ),
            Ship(
                TypeShip.CRUISE,
                ShipAlignment.VERTICAL,
                mutableListOf(Cell(StatusCell.OCCUPIED, 1, 1))
            ),
            Ship(
                TypeShip.DESTROYER,
                ShipAlignment.VERTICAL,
                mutableListOf(Cell(StatusCell.OCCUPIED, 1, 1))
            ),
            Ship(
                TypeShip.SUBMARINE,
                ShipAlignment.VERTICAL,
                mutableListOf(Cell(StatusCell.OCCUPIED, 1, 1))
            ),
        )

        board.ships.put("player1", ships1)
        board.ships.put("player2", ships2)
    }
}
