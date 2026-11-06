package org.example
import kotlinx.coroutines.*

fun main() = runBlocking {
    println("--- Application Météo ---")
    println("Action : L'utilisateur clique sur le bouton 'Actualiser'")
    // On lance une coroutine pour exécuter la tâche en arrière-plan
    // Cela évite de "figer" l'interface (le thread principal)
    val attente = launch {
        println("Mise à jour de l'écran avec : " + meteo())
    }
    // 2 DO
    println("L'interface reste réactive : l'utilisateur peut toujours cliquer ailleurs.")
    println("... Chargement des données météo en cours ...")
    // On attend que la coroutine soit terminée avant de fermer l'application
    // 2 DO
    attente.join()
    println("--- Fin de l'application ---")
}

suspend fun meteo(): String {
    delay(1000L)
    return "37°C ! c'est la canicule !"
}