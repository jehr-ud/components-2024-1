package com.example.battleship.logic

import com.example.battleship.model.Enums.ShipAlignment
import com.example.battleship.model.Enums.TypeShip

class Ship(
    var type: TypeShip,
    var aling: ShipAlignment,
    var listPositions: MutableList<Cell> = mutableListOf()
) {
    constructor() : this(TypeShip.CARRIER, ShipAlignment.VERTICAL, mutableListOf()) // constructor vacio para el firebase
    val size: Int
        get() = when (type) { // obtener el tamaño de cada tipo de barco
            TypeShip.CARRIER -> 5
            TypeShip.BATTLE_SHIP -> 4
            TypeShip.CRUISE -> 3
            TypeShip.SUBMARINE -> 3
            TypeShip.DESTROYER -> 2
        }
}
