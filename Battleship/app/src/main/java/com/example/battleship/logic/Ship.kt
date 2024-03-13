package com.example.battleship.logic

import com.example.battleship.logic.Enums.TypeShip
import androidx.compose.material3.Button

class Ship (
    var type: TypeShip,
    var listPositions: MutableList<Cell>){

}