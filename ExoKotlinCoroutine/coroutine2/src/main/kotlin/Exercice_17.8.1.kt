package org.example
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

fun main() = runBlocking {
    println("--- Début de Transmission ---")
    val batterie = MutableStateFlow<Int>(100)

    val Sonde = launch {
        val valeurs = listOf(80, 50, 20, 5)
        for (n in valeurs) {
            delay(1000)
            batterie.value = n
            println("--- Sonde [Ares-1] : Nouveau niveau -> ${batterie.value}%")
        }
    }

    val Console_de_Controle = launch {
        batterie.collect { println(">>> Console : État reçu -> ${it}%") }
    }

    delay(6000)

    if(batterie.value < 20){
        println("!!! ALERTE CRITIQUE : Énergie faible sur Ares-1 !!!")
    }

    Sonde.cancelAndJoin()
    Console_de_Controle.cancelAndJoin()
    println("--- Transmission terminée ---")
}