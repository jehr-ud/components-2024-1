package com.example.battleship.logic

import com.example.battleship.model.Enums.StatusCell

data class Cell(
    var status: StatusCell? = null,
    var row: Int = 0,
    var col: Int = 0
)