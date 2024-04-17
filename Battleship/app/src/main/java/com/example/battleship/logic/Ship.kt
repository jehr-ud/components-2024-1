package com.example.battleship.logic

import com.example.battleship.model.Enums.ShipAlignment
import com.example.battleship.model.Enums.TypeShip

class Ship (
    var type: TypeShip,
    var aling: ShipAlignment,
    var listPositions: MutableList<Cell>){

}