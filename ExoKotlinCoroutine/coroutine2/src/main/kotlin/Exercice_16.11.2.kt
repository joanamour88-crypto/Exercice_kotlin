package org.example
import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Ouverture de l'application !")
    val attente = launch {
        telechargerPochette()
    }
    println("Menu principal (disponible, pendant le téléchargement)")
    attente.join()
    println("Fin de l'application !")
}

suspend fun telechargerPochette(){
    println("Téléchargement...")
    delay(10000L)
    println("Pochette téléchargée !")
}