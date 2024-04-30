package com.example.battleship.logic

import com.example.battleship.model.Enums.ShipAlignment
import com.example.battleship.model.Enums.TypeShip

class Ship(
    var type: TypeShip = TypeShip.BATTLE_SHIP,
    var aling: ShipAlignment = ShipAlignment.VERTICAL,
    var listPositions: MutableList<Cell> = mutableListOf()
) {
}