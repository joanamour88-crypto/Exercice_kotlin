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
data class MaterielInformatique(val designation: String, val assembleur: String) {
    override fun toString(): String = "(designation -> ${designation} et numero de serie -> ${assembleur})"
}
data class CaisseBoisson(val appellation: String, val volumeLitres: Double) {
    override fun toString(): String = "appellation -> ${appellation} et volume par litres -> ${volumeLitres}"
}
class Conteneur<T>(val contenu: T, val poidsInitial: Double) {
    init{
        require(poidsInitial >= 0.0) {"le poids initial doit être supérieur à 0"}
    }

    var poids: Double = poidsInitial
        private set (valeur){
            if (valeur > 0){
                field = valeur
            }
        }

    fun ajouterPoids(poidsajoute: Double){
        if (poids >= 0){
            poids = poidsInitial + poidsajoute
        } else {
            throw Exception("Le poids doit être supérieur a zéro")
        }
    }
    override fun toString(): String = "Etat Initial : " + contenu.toString() + ", Poids total : ${poids} t"
}
//////////////////////////////////// Exercice 9.8.1 ////////////////////////////////////////
interface IDocument{
    val titre: String
    val auteur: String
    val editeur: String
    val dateParution: String

    fun afficherDetails(): String
}

class Bibliotheque {
    private val catalogue: ArrayList<IDocument> = ArrayList()

    fun ajouterDocument(doc: IDocument): Unit{
        catalogue.add(doc)
    }

    fun afficherTout(): Unit {
        for (doc in catalogue){
            println(doc.afficherDetails())
        }
    }
}

class Livre(
    override val titre: String,
    override val auteur: String,
    override val editeur: String,
    override val dateParution: String,
    val quatriemeDeCouverture: String,
    val nombreDePage: Int
    ) : IDocument {
    override fun afficherDetails(): String {
        return "Ce livre a pour titre : $titre, écrit par $auteur, éditer par $editeur et est parue en $dateParution. Voici le résumer: $quatriemeDeCouverture et il y a $nombreDePage pages"
    }
}
class Photo(
    override val titre: String,
    override val auteur: String,
    override val editeur: String,
    override val dateParution: String,
    val resolutionHorizontale: Int,
    val resolutionverticales: Int,
    val estCouleur: Boolean
    ) : IDocument{
    override fun afficherDetails(): String {
        return "Cette photo a pour titre : $titre, photographier par $auteur, éditer par $editeur et est publié en $dateParution. La photo a pour resolution horizontale : $resolutionHorizontale et resolution verticale: $resolutionverticales. en couleur: $estCouleur"
    }
}
//////////////////////////////////// Exercice 9.8.1 Test ////////////////////////////////////////
/*sealed class IDocument(val titre: String, val auteur: String, val editeur: String, val dateParution: String){
    class livre(
        titre: String,
        auteur: String,
        editeur: String,
        dateParution: String,
        val quatriemeDeCouverture: String,
        val nombrePages: Int
    ) : IDocument(titre, editeur, editeur, dateParution)
    class photo(

    )
}*/

//////////////////////////////////// Exercice 9.8.2 ////////////////////////////////////////
sealed class Astre(val nom: String, val type: String){

    class Etoile(
        val couleur: String,
        val temperatureKelvin: Int,
        nom: String, type: String,
    ) : Astre(nom, type)

    class Planete(
        val diametreKm: Double,
        val nombreSatellites: Int,
        nom: String, type: String,
    ) : Astre(nom, type)

    class Comete(
        val periodeAnnees: Double,
        nom: String, type: String,
    ) : Astre(nom, type)

    class SatelliteNaturel(
        val planeteHote: String,
        nom: String, type: String,
    ) : Astre(nom, type)
}
fun afficherMessage(a:Astre) {
    when(a) {
        is Astre.Etoile -> println("Cette étoile se nomme ${a.nom} et est de type ${a.type}. \nElle est de couleur ${a.couleur} et a une température de ${a.temperatureKelvin}")
        is Astre.Planete -> println("Cette planete se nomme ${a.nom} et est de type ${a.type}. \nElle est de diametre ${a.diametreKm} et possede ${a.nombreSatellites} satellites naturel")
        is Astre.Comete -> println("Cette comete se nomme ${a.nom} et est de type ${a.type}. \nElle a vécu pendant ${a.periodeAnnees}")
        is Astre.SatelliteNaturel -> println("Ce satellite se nomme ${a.nom} et est de type ${a.type}. \nIl tourne autour de ${a.planeteHote}")
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
       PAPAYOU PAPAYOU PAÄYOU LELEEEEEE
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

    //////////////////////////////////// Exercice 8.17.5 ////////////////////////////////////////
    val a = Fraction(1,2) // dénomitateur à 1 par défaut
    val b = Fraction(1,3)
    println("a+b (surcharge de la méthode associée plus) : ${a+b}") // a+b retourne un objet Fraction, et appel méthode toString implicite
    println("a-b (surcharge de la méthode associée plus) : ${a-b}")
    println("a*b (surcharge de la méthode associée plus) : ${a*b}")
    println("a/b (surcharge de la méthode associée plus) : ${-a}")

    //////////////////////////////////// Exercice 8.17.6 ////////////////////////////////////////
    val Matinfo = MaterielInformatique("PC ProDesk", "HP")
    val CaisBois = CaisseBoisson("Cidre Breton", 30.0)
    val cont = Conteneur(Matinfo   , 0.0)
    cont.ajouterPoids(15.0)

    print(cont)

    //////////////////////////////////// Exercice 9.8.1 ////////////////////////////////////////
    val bibliotheque = Bibliotheque()

    val livre1 = Livre(
        "Le Petit Prince",
        "Antoine de Saint-Exupéry",
        "Gallimard",
        "1943",
        "Un conte poétique et philosophique ...",
        96
    )

    val photo1 = Photo(
        "Coucher de soleil",
        "Jean Dupont",
        "Studio Lumière",
        "2022",
        1920,
        1080,
        true
    )

    bibliotheque.ajouterDocument(photo1)
    bibliotheque.ajouterDocument(livre1)

    bibliotheque.afficherTout()*/

    //////////////////////////////////// Exercice 9.8.2 ////////////////////////////////////////
    val unAstre = Astre.Planete(12742.0, 1, "Terre", "Planete")
    afficherMessage(unAstre)
}