package org.example
import kotlin.reflect.KProperty
import kotlin.properties.Delegates

class CapteurMoteur() {

    class LogDelegates<T>(private var valeur: T) {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
            return valeur
        }
        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
            valeur = value
        }
    }

    var temperature: Double by Delegates.observable(0.0) { _, _, newtemperature ->
        when{
            newtemperature < -20 && newtemperature > 150 -> {
                println("[ALERTE] Température invalide détectée : " + newtemperature +" (Plage autorisée: -20 à 150)")
            }
            else -> {
                println("[CAPTEUR] Nouvelle température enregistrée : " + newtemperature)
            }
        }
    }
}

fun main(){
    val capteur = CapteurMoteur()
    capteur.temperature = 50.0   // Valide -> Notification enregistrement
    capteur.temperature = 200.0  // Invalide -> Notification d'alerte (mais la valeur est modifiée)
}
