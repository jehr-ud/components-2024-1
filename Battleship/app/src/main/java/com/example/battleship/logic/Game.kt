package com.example.battleship.logic

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.battleship.model.Enums.ShipAlignment
import com.example.battleship.model.Enums.StatusCell
import com.example.battleship.model.Enums.TypeShip

fun Color.toHex(): String {
    return String.format("#%06X", (this.toArgb() and 0xFFFFFF))
}
                                                                                //funciones para manejar colores
fun String.toColor(): Color {
    return Color(android.graphics.Color.parseColor(this))
}


class Game(
    var rows: Int = 10,
    var cols: Int = 10,
    var board: Board = Board(),
    var aliasMatch: String = "",
    var canStart: Boolean = false,
    var turn: String = "",
    var player1: Player = Player("", ""),
    var player2: Player? = null,
    var finishAt: String = ""
) {
    fun generateCells() {
        for (row in 0 until rows) {
            for (col in 0 until cols) {
                val cell = Cell(StatusCell.EMPTY, row, col)
                board.cells[row][col] = cell
            }
        }
    }
    val shipColors: Map<String, String> = mapOf( // Mapa de colores segun el barco, no implemented
        "CARRIER" to "#0000FF", // Azul
        "BATTLE_SHIP" to "#FF0000", // Rojo
        "CRUISE" to "#00FF00", // Verde
        "SUBMARINE" to "#FFFF00", // Amarillo
        "DESTROYER" to "#FF00FF" // Rosado raro xd
    )

    fun generateShips() {
        // Tipos de barcos que se van a generar
        val shipTypes = listOf(
            TypeShip.CARRIER,
            TypeShip.BATTLE_SHIP,
            TypeShip.CRUISE,
            TypeShip.SUBMARINE,
            TypeShip.DESTROYER
        )

        // Itera sobre cada tipo de barco
        for (type in shipTypes) {
            // Crea un nuevo barco del tipo actual con alineación vertical
            val ship = Ship(type, ShipAlignment.VERTICAL)
            // Intenta colocar el barco en una posición aleatoria hasta que se coloque correctamente
            while (true) {
                // Genera una posición aleatoria dentro del tablero
                val row = (0 until rows).random()
                val col = (0 until cols).random()
                // Define la alineación del barco de forma aleatoria
                ship.aling = if ((0..1).random() == 0) ShipAlignment.VERTICAL else ShipAlignment.HORIZONTAL
                // Intenta colocar el barco en la posición aleatoria
                if (board.placeShip(ship, row, col)) {
                    break // El barco se colocó correctamente, termina el bucle
                }
            }
            // Agrega el barco al jugador 1
            board.ships.getOrPut("player1") { mutableListOf() }.add(ship)
        }
        // Imprime el tablero después de colocar los barcos
        board.printBoard()
/*              ==========> ESTE FOR ES PARA COLOCAR LOS BARCOS AL JUGADOR 2 PERO SE ESTAN COLOCANDO 10 BARCOS EN EL MISMO TABLERO <===========
        for (type in shipTypes) {
            val ship = Ship(type, ShipAlignment.HORIZONTAL)
            while (true) {
                val row = (0 until rows).random()
                val col = (0 until cols).random()
                ship.aling = if ((0..1).random() == 0) ShipAlignment.VERTICAL else ShipAlignment.HORIZONTAL
                if (board.placeShip(ship, row, col)) {
                    break
                }
            }
            board.ships.getOrPut("player2") { mutableListOf() }.add(ship)
        }
        */
    }
}
