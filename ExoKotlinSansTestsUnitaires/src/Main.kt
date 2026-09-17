import Utils.*

//////////////////////////////////// Exercice 8.17.2 /////////////////////////////////////////
class Planete(val nom: String, val masse: Double, val rayon: Double, val distanceSoleil: Double){
    init{
        require(masse > 0){"La masse doit être supérieur à 0,\n la planete problematique est $nom"}
        require(rayon > 0){"Le rayon doit être supérieur à 0,\n la planete problematique est $nom"}
        require(distanceSoleil > 0){"La distance doit être supérieur à 0,\n la planete problematique est $nom"}
    }
    override fun toString(): String {
        return "$nom -> Masse : $masse M terrestre(s), Rayon : $rayon km, Distance avec le Soleil : $distanceSoleil"
    }
}

//////////////////////////////////// Exercice 8.17.3 /////////////////////////////////////////
class Compositeur(val nom: String, var anneeNaissance: Int){
    var anneeDeces: Int = anneeNaissance
        get() = field
        set(valeur){
            if (valeur > anneeDeces)
                field = valeur
        }

    val ageAuDeces: Int
        get() = anneeDeces - anneeNaissance

    override fun toString(): String {
        return "$nom est né en $anneeNaissance et est mort en $anneeDeces à l'âge de $ageAuDeces"
    }
}

//////////////////////////////////// Exercice 8.17.4 /////////////////////////////////////////
class ObjetMessier(val numero: Int, val nom: String, typeInitial: String){
    init{
        require(numero in 1..110){"Erreur interceptée avec succès : Le numéro Messier $numero est invalide. Il doit être compris entre 1 et 110."}
    }

    var type: String = typeInitial
        get() = field.uppercase()

    var magnitudeApparente: Double = 0.0
        get() = field
        set(valeur){
            if (valeur > 30.0){
                field = 30.0
            } else {
                field = valeur
            }
        }

    val estVisibleAOeilNu: Boolean
    get() = magnitudeApparente < 6.0


    override fun toString(): String {
        return "M$numero (Galaxie $nom - Type : $type)" + "\n Magnitude : $magnitudeApparente -> Visible à l'oeil nu ? $estVisibleAOeilNu"
    }
}

//////////////////////////////////// Exercice 8.17.5 ////////////////////////////////////////
data class Fraction(val numerateur: Int, val denominateur: Int = 1) {

    init {
        require(denominateur != 0) { "Le dénominateur ne peut pas être nul." }
    }

    operator fun plus(autre: Fraction): Fraction { // surcharge de l’opérateur +
        return Fraction(numerateur * autre.denominateur + autre.numerateur * denominateur,
            denominateur * autre.denominateur)
    }

    operator fun minus(autre: Fraction): Fraction {
        return Fraction(numerateur * autre.denominateur - autre.numerateur * denominateur,
            denominateur * autre.denominateur)
    }

    operator fun times(autre: Fraction): Fraction {
        return Fraction((numerateur * autre.numerateur) , (autre.denominateur* denominateur))
    }

    /*operator fun div(autre: Fraction): Fraction {
        return Fraction(numerateur * autre.denominateur / autre.numerateur * denominateur,
            denominateur * autre.denominateur)
    }*/

    operator fun unaryMinus(): Fraction {
        return Fraction(-numerateur, denominateur)
    }

    override fun toString(): String = "$numerateur/$denominateur"
}

//////////////////////////////////// Exercice 8.19.1 ////////////////////////////////////////
data class MaterielInformatique(val designation: String, val numeroSerie: String) {

}
data class CaisseBoisson(val appellation: String, val volumeLitres: Double) {

}
class Conteneur<T>(val contenu: T, val poidsInitial: Double) {
    init{
        require(poidsInitial > 0) {"le poids initial doit être supérieur à 0"}
    }

    var poids: Double = poidsInitial
        get() = field
        private set (valeur){
            if (valeur < 0){
                field = 0.0
            }
        }

    fun ajouterPoids(poidsajoute: Double){
        if (poids > 0){
            poids = poidsInitial + poidsajoute
        } else {

        }
    }
}

fun main(){
    //////////////////////////////////// Exercice 3.14.2 ////////////////////////////////////////
    /*val pseudo: String? = null
    val affichage = pseudo ?: "Utilisateur Anonyme"
    println(affichage)

    //////////////////////////////////// Exercice 3.14.3 ////////////////////////////////////////
    print("Votre âge ?")
    val age1 = readlnOrNull()?.toIntOrNull() ?: 18
    println("Age retenu : $age1")

    //////////////////////////////////// Exercice 4.3.1 /////////////////////////////////////////
    print("Nombre ?")
    val nombre = readlnOrNull()?.toIntOrNull()
    if (nombre != null) {
        println(if (nombre < 0) -nombre else nombre)
    } else {
        println("Ce n'est pas un nombre valide")
    }

    //////////////////////////////////// Exercice 4.3.2 /////////////////////////////////////////
    print("Veuillez saisir votre âge :")
    val saisie: String? = readlnOrNull()
    val age2: Int? = saisie?.toIntOrNull()
    println(
        when {
            age2 == null -> "Erreur : vous n'avez pas saisie un nombre valide."
            age2 < 0 -> "L'âge ne peut pas être négatif."
            age2 in 0..12 -> "Vous êtes un enfant."
            age2 in 13..17 -> "Vous êtes un adolescent."
            age2 in 18..64 -> "Vous êtes un adulte."
            else -> "vous êtes un senior."
        }
    )

    //////////////////////////////////// Exercice 4.3.3 /////////////////////////////////////////
    print("Montant avant remise ?")
    val montant: Float? = readlnOrNull()?.toFloatOrNull()
    if (montant != null) {
        val remise = when {
            montant < 2000 -> 0
            montant <= 5000 -> 1
            else -> 2
        }
        println("La remise est de $remise %")
        val montantNet = montant - (montant * (remise / 100.0))
        println("Montant net après remise = $montantNet")
    }else {
        println("Erreur : veuillez saisir un montant valide.")
    }

    //////////////////////////////////// Exercice 4.5.1 /////////////////////////////////////////
    var somme = 0.0
    var compteur = 0
    var note: Double?
    do {
        print("Note ? (-1 pour sortir) ")
        note = readlnOrNull()?.toDoubleOrNull()
        if (note != null && note != -1.0) {
            somme += note
            compteur++
        }
    } while (note != -1.0)
    if (compteur > 0){
        println("Somme : $somme")
        println("Compteur : $compteur")
        println("Moyenne : ${somme / compteur}")
    }
    println("Au revoir !")

    //////////////////////////////////// Exercice 4.5.2 /////////////////////////////////////////
    println("Mot à répéter ?")
    val mot = readlnOrNull()
    print("Combien de répétitions ?")
    val n = readlnOrNull()?.toIntOrNull() ?: 1
    repeat(n){
        print("$mot ")
    }

    //////////////////////////////////// Exercice 6.8.1 /////////////////////////////////////////
    print("Valeur de la resistance (enOhm) (>=0) ?")
    val resistance = readlnOrNull()?.toDoubleOrNull()
    print("Valeur de l'intensité (en Ampère) (>=0) ?")
    val intensite = readlnOrNull()?.toDoubleOrNull()
    if (resistance != null && intensite != null) {
        if (resistance > 0 && intensite > 0) {
            val tension = calculerTension(resistance, intensite)
            println("Tension du Circuit (U) : $tension")
            println("Puissance (P) : ${calculerPuissance(tension, intensite)} Watt")
        } else {
            println("Erreur(s) de saisie : resistance ou intensité < 0.")
        }
    } else {
        println("Erreur(s) de saisie sur la resistance ou l'intensité.")
    }

    //////////////////////////////////// Exercice 8.17.2 /////////////////////////////////////////
    val terre = Planete("Terre",1.0,6371.0, 1.0 )
    val mars = Planete("Mars", 0.11, 3389.5, 1.52)
    val jupiter = Planete("Jupiter", 317.8, 69911.0, 5.2)
    val coruscant = Planete("Coruscant", 259.2, 596312.2, 1862.6)

    println(terre)
    println(mars)
    println(jupiter)
    println(coruscant)

    //////////////////////////////////// Exercice 8.17.3 /////////////////////////////////////////
    val mozart = Compositeur("Wolfgang Amadeus Mozart", 1756)
    mozart.anneeDeces = 1791
    println(mozart)

    //////////////////////////////////// Exercice 8.17.4 /////////////////////////////////////////
    val andromede = ObjetMessier(31, "Andromede", "galaxie")
    andromede.magnitudeApparente = 3.4
    println(andromede)
    */
    //////////////////////////////////// Exercice 8.17.5 ////////////////////////////////////////
    val a = Fraction(1,2) // dénomitateur à 1 par défaut
    val b = Fraction(1,3)
    println("a+b (surcharge de la méthode associée plus) : ${a+b}") // a+b retourne un objet Fraction, et appel méthode toString implicite
    println("a-b (surcharge de la méthode associée plus) : ${a-b}")
    println("a*b (surcharge de la méthode associée plus) : ${a*b}")
    println("a/b (surcharge de la méthode associée plus) : ${-a}")
}