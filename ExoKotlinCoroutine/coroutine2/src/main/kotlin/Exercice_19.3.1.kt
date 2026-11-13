package org.example

interface SourceDonnees {
    fun recupererDonnees(): String
}

class SourceSqlite : SourceDonnees {
    override fun recupererDonnees(): String {return "Données tableau de bord issues de la base SQLite (Mode Hors-ligne)"}
}

class SourceApiRest : SourceDonnees {
    override fun recupererDonnees(): String {return "Données tableau de bord récupérées via l’API REST (Serveur distant)"}
}

class GestionnaireTableauDeBord {
    lateinit var sourceDonnees: SourceDonnees
    fun afficherTableauDeBord(){
        if(::sourceDonnees.isInitialized){
            println("... chargement ...")
            println("Affichage ${sourceDonnees.recupererDonnees()}")
        }else {
            println("Attention : Aucune source de données n'a été configurée !")
        }
    }
}

fun main(){
    val tableau = GestionnaireTableauDeBord()
    tableau.afficherTableauDeBord()
    val Sqlite = SourceSqlite()

    val ApiRest = SourceApiRest()
    tableau.afficherTableauDeBord()
}