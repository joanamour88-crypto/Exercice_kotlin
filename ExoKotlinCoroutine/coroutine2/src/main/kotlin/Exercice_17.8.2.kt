package org.example
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

data class EtatDrone(val altitude: Int, val status: String){

}

fun main() = runBlocking {
    val Etatdudrone = MutableStateFlow(EtatDrone(0,"Au sol"))

    val changement_Etat = launch {
        EtatDrone(50,"En vol")
        delay(3000)
        EtatDrone(0,"Livré")
    }

    val affichage_Etat = launch {
        Etatdudrone.collect { n -> println(">>> Moniteur : Altitude=${n.altitude}m | Statut=${n.status}") }
    }

    delay(5000)

    if(Etatdudrone.value.altitude === 50){
        println("--- Pilote : Décollage effectué ---")
    }else if(Etatdudrone.value.altitude === 0 && Etatdudrone.value.status === "Livré"){
        println("--- Pilote : Colis déposé ---")
    }

    changement_Etat.cancelAndJoin()
    affichage_Etat.cancelAndJoin()
    println("--- Mission terminée ---")
}
