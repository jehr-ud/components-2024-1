package com.example.clasesyobjetos
class Pan (val harina:String, val horno:String) {
    // constructor personalizado :D
    constructor(cantidadHarina: Int,  tipoHorno: String) : this("Harina de Maiz", tipoHorno) {
        println("Se uso el constructor nuevo")
    }
    fun verificarPan(): Boolean {
        return  harina.isNotEmpty() && horno.isNotEmpty()
    }
}
class Panadero (val nombre:String) {
    // Método para hacer pan
    fun hacerPan(harina: String, horno: String): Pan {
        println("$nombre está haciendo pan...")
        return Pan(harina, horno)
    }
    fun hacerPanMaiz (cantidadHarina:Int,  tipoHorno: String,): Pan {
        println("$nombre está haciendo un pan especial ...")
        return Pan(cantidadHarina,  tipoHorno)
    }
}
fun main() {
    val panadero = Panadero("Juan")
    val pan = panadero.hacerPan("Harina de trigo", "Horno industrial")
    println("¡${panadero.nombre} ha hecho un pan con ${pan.harina} en un ${pan.horno}!")
    val pan2 = panadero.hacerPanMaiz(123,"Horno de Piedra")
    println("¡${panadero.nombre} ha hecho un pan con ${pan2.harina} en un ${pan2.horno}!")
}