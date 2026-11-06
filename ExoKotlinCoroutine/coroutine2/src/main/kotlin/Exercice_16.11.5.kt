package org.example
import kotlinx.coroutines.*

fun main() = runBlocking {
    val scope = CoroutineScope(Dispatchers.Default)

    val job = scope.launch {
        try {
            println("--- Centre de contrôle : Début de la séquence ---")
            var compteur: Int = 1
            while(isActive && compteur <= 5){
                println("Étape ${compteur} : Vérification des systèmes...")
                delay(1000L)
                compteur++
            }
        }catch(e: Exception){
            println(">>> Capteur : Anomalie détectée ! Annulation du lancement ! <<<")
        } finally{
            println("--- Fin de mission ---")
        }
    }
    delay(2500L)

}
