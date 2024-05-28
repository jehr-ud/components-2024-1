package com.example.battleship.logic

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.battleship.model.Enums.ShipAlignment
import com.example.battleship.model.Enums.StatusCell
import com.example.battleship.model.Enums.TypeShip


class Board(val rows: Int = 10, val cols: Int = 10) {
    var cells: MutableList<MutableList<Cell>> = MutableList(rows) { row -> //mutableList de filas
        MutableList(cols) { col -> // dentro de cada mutableList de filas se crea un mutable list de columnas
            Cell(status = StatusCell.EMPTY, row = row, col = col) // se crea un objeto Cell para cada casilla del board
        }
    }
    var ships: MutableMap<String, MutableList<Ship>> = mutableMapOf() //reprencenta mapa de barcos, p1 = jugador, p2 = Tipo de barco

    fun placeShip(ship: Ship, startRow: Int, startCol: Int): Boolean {
        // Comprueba si el barco puede ser colocado en la posición especificada
        for (i in 0 until ship.size) {

            val row = if (ship.aling == ShipAlignment.VERTICAL) startRow + i else startRow    // Calcula la fila y columna según la alineación del barco
            val col = if (ship.aling == ShipAlignment.HORIZONTAL) startCol + i else startCol

            if (row >= rows || col >= cols || cells[row][col].status != StatusCell.EMPTY) {     // Verifica si la posición está dentro de los límites del tablero y si está vacía
                return false // No se puede colocar el barco
            }
        }
        // Coloca el barco en el tablero
        for (i in 0 until ship.size) {
            val row = if (ship.aling == ShipAlignment.VERTICAL) startRow + i else startRow
            val col = if (ship.aling == ShipAlignment.HORIZONTAL) startCol + i else startCol
            cells[row][col].status = StatusCell.OCCUPIED // Marca la celda como ocupada
            ship.listPositions.add(cells[row][col]) // Agrega la celda al listado de posiciones ocupadas por el barco
        }
        return true // El barco colocado
    }
    fun printBoard() {
        for (row in cells) {
            for (cell in row) {
                // Imprime el estado de la celda: 'E' para vacía, 'O' para ocupada
                print("${cell.status?.name?.first() ?: 'E'} ")
            }
            println() // Nueva línea para la siguiente fila del tablero
        }
    }
}
