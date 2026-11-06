package org.example
import kotlinx.coroutines.*

fun main() = runBlocking {
    println("--- Début de la maintenance station ---")
    val scope = CoroutineScope(Dispatchers.Default)

    val rep1 = scope.launch {
        try{
            repeat(10){
                i ->
                println("Réparation panneau solaire : $i/10")
                delay(1000L)
            }
        } catch(e: Exception){
            println(">>> ALERTE : Annulation d'urgence ! <<<")
        }
    }
    delay(2500L)
    scope.cancel()
    rep1.join()
    println("--- Mission terminée ---")
}
