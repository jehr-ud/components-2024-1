package com.example.battleship.logic

import com.example.battleship.model.Enums.StatusCell

class Board {
    var cells: MutableList<Cell> = mutableListOf()
    var ships: MutableMap<String, MutableList<Ship>> = mutableMapOf()
}