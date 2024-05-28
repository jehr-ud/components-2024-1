package com.example.battleship.logic

data class Player(
    var name: String = "",
    var uid: String = ""

){
    constructor() : this("", "") // constructor vacio pal firabase
}