package com.example.battleship.logic

import com.example.battleship.model.Enums.TypeShip

class Ship (
    var type: TypeShip,
    var listPositions: MutableList<Cell>){

}