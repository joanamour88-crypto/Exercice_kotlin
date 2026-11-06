package org.example
import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Début de la mission ---")
    val rescam = async{
        recupererDonneesCamera()
    }

    val resspec = async {
        recupererDonneesSpectro()
    }
    println("Récupération des données en cours...")
    rescam.join()
    resspec.join()
    println("Resultat : ${rescam.await()} + ${resspec.await()}")
    println("mission terminé !")
}

suspend fun recupererDonneesCamera() : String {
    println("Début caméra")
    delay(2000L)
    println("Fin caméra (après 2s)")
    return "Image Infrarouge"
}

suspend fun recupererDonneesSpectro() : String {
    println("Début spectro")
    delay(2000L)
    println("Fin spectro (après 2s)")
    return "Analyse Spectrale"
}