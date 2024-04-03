package com.example.battleship

import com.example.battleship.model.Message

object Rules {
    val messages: List<Message> = listOf(


        Message(
            "Regla 1: Jugadores",
            "Batalla naval tiene un minimo y maximo de 2 jugadores por partida.",
            R.drawable.regla1
        ),
        Message(
            "Regla 2: Barcos",
            "Cada jugador tendrá 10 barcos con distintos puntos de salud para posicionar " +
                    "alrededor del tablero.",
            R.drawable.regla2
        ),
        Message(
            "Regla 3: Posición",
            "Los jugadores deberan posicionar sus barcos unicamente de forma horizontal o " +
                    "vertical en la cuadricula sin que su oponente vea donde se encuentran situados," +
                    " una vez el jugador confirme la posición de sus barcos no podrá moverlos nuevamente.",
            R.drawable.regla3
        ),
        Message(
            "Regla 4: Ataque",
            "El sistema de ataque es por turnos, iniciando por el jugador 1, El jugador deberá" +
                    " seleccionar una casilla, si en la casilla seleccionada se encuentra posicionado" +
                    " un barco enemigo, el jugador podrá repetir el ataque para intentar derribar " +
                    "el barco completamente, por el contrario, si el jugador selecciona una casilla" +
                    " en donde solo hay agua, se acaba el turno y deberá atacar el siguiente jugador;" +
                    " cuando el jugador realice un ataque a una casilla, esta casilla quedara " +
                    "marcada y no podrá volver a ser atacada.",
            R.drawable.regla4
        ),
        Message(
            "Regla 5: Perdida de turno",
            "El jugador tendra un tiempo limite de 15 segundos para realizar su ataque, si no" +
                    " se realiza ningún movimiento en este tiempo limite el jugador perdera su " +
                    "turno y no podrá realizar el ataque.",
            R.drawable.regla5
        ),
        Message(
            "Regla 6: Como ganar",
            "El jugador que destruya primero la flota enemiga será el ganador de la partida.",
            R.drawable.regla6
        )
    )
}