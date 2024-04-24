package com.example.battleship.logic

import com.example.battleship.model.Enums.StatusCell

class Board (
    var rows: Int = 10,
    var cols: Int = 10) {
    var cells: MutableList<Cell> = mutableListOf()

    fun updateCell(row: Int, col: Int){
        var newStatus: StatusCell = StatusCell.entries.get((1..4).random())
        var position = (row * col) + col
        cells[position].status = newStatus
    }
}