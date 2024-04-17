package com.example.battleship.logic

import com.example.battleship.model.Enums.StatusCell

data class Cell (
    var status: StatusCell,
    var row: Int,
    var col: Int
)