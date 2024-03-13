package com.example.battleship.logic

import com.example.battleship.logic.Enums.StatusCell

class Board (
    var rows: Int,
    var cols: Int) {
    var cells: MutableList<Cell> = mutableListOf()

    fun updateCell(row: Int, col: Int){
        var newStatus: StatusCell = StatusCell.values().get((1..4).random())
        var position = (row * col) + col
        cells[position].status = newStatus
    }
}