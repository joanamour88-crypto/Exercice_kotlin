package org.example
import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("Je travaille $i...")
                delay(500) // Fonction suspendue annulable
            }
        } catch (e: CancellationException) {
            println("J'ai été annulé ! Message: ${e.message}")
        } finally {
            // Le bloc finally est toujours exécuté, même en cas d'annulation.
            // C'est l'endroit idéal pour nettoyer les ressources.
            println("Nettoyage des ressources...")
        }
    }
    delay(1500) // Laisse la coroutine travailler un peu
    println("Le temps est écoulé, j'annule le travail !")
    job.cancel() // Annule la coroutine
    job.join()   // Attend que la coroutine se termine (même après annulation)
    println("Terminé.")
}