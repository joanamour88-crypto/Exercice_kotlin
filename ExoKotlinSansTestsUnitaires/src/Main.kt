import Utils.*
import java.time.LocalDateTime
import javax.swing.JSeparator
import javax.xml.stream.FactoryConfigurationError
import kotlin.compareTo
import kotlin.math.pow
import kotlin.math.round

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
//////////////////////////////////// Exercice 10.6.1 ////////////////////////////////////////
fun String?.formaterImmatriculation(): String {
    if(!this.isNullOrBlank()){
        return this.trim().uppercase()
    } else {
        return "Problème dans le contenu"
    }
}
//////////////////////////////////// Exercice 10.6.2 ////////////////////////////////////////
class conteneur(val longueurMetres: Double, val largeurMetres: Double, val hauteurMetres: Double){}
fun conteneur.volume(): Double{
    return this.longueurMetres * this.largeurMetres * this.hauteurMetres
}

//////////////////////////////////// Exercice 10.6.3 ////////////////////////////////////////
interface CorpsCeleste{
    val nom: String
    val magnitude: Double

}

class Planete1(val distanceSoleil: Double, val dateDecouverte: String, override val nom: String, override val magnitude: Double): CorpsCeleste{}

fun CorpsCeleste.estBrillant(): Boolean{
    if(magnitude < 1.5){
        return true
    } else {
        return false
    }
}

fun Planete1.toPlaneteJson(): String{
    return "'nom' : '$nom', 'magnitude' : $magnitude, 'distance' : $distanceSoleil, 'dateDecouverte' : '$dateDecouverte'"
}

fun Planete1.estProche(): Boolean{
    if(distanceSoleil < 150){
        return true
    } else {
        return false
    }
}

//////////////////////////////////// Exercice 11.6.1 ////////////////////////////////////////
object CentreControleMaritime {
    private var nbalerte: Int = 0

    fun emettreAlerte(message: String){
        println(message)
        nbalerte += 1
    }

    fun afficherBilan(){
        println("Bilan : Le centre a diffusé un total de $nbalerte alerte(s)")
    }
}

//////////////////////////////////// Exercice 11.6.2 ////////////////////////////////////////
data class SondeSpaciale(val nom: String, val orbite: String, val autonomieMois: Int){
    companion object{
        fun depuisChaine(ligneConfig: String):SondeSpaciale{
            val parties = ligneConfig.split(":")
            return SondeSpaciale(parties[0], parties[1], parties[2].toInt())
        }
    }
}

//////////////////////////////////// Exercice 11.6.3 ////////////////////////////////////////
sealed class TypeEtoile(val nom: String, val couleur: String){
    object O : TypeEtoile("SuperGéante Bleu", "Bleu"){
        fun decrireTemperature() = println("Caractéristique thermique : Température extrême : > 30 000°C")
    }
    object G : TypeEtoile("Naine Jaune", "Jaune"){
        fun decrireTemperature() = println("Caractéristique thermique : Température modérée : ~ 5 500°C")
    }
    object M : TypeEtoile("Naine Rouge", "Rouge"){
        fun decrireTemperature() = println("Caractéristique thermique : Température basse : < 3 700°C")
    }
}
//////////////////////////////////// Exercice 13.6.1 ////////////////////////////////////////
fun calculer(x: Double, f: (Double) -> Double): Double {
    return f(x)
}

//////////////////////////////////// Exercice 13.6.3 ////////////////////////////////////////
fun repeter(fois: Int, action: (Int) -> Unit){
    for(i in 1 .. fois)
        action(i)
}

//////////////////////////////////// Exercice 13.6.4 ////////////////////////////////////////
fun integrer(a: Double, b: Double, n: Int, f: (Double) -> Double): Double{
    var somme: Double = 0.0
    for(i in 1..n){
        val x = a + (i - 0.5) * (b - a) / n
        somme += f(x)
    }
    return (b - a) / n  * somme
}

//////////////////////////////////// Exercice 13.6.5 ////////////////////////////////////////
fun calculerVan(flux_futur: Double, taux: Double, annees: Int, VANMarge_secu: (Double) -> Double): Double{
    return VANMarge_secu((flux_futur) / (1+taux).pow(annees))
}

//////////////////////////////////// Exercice 13.6.6 ////////////////////////////////////////
fun appliquerDeuxFois(x: Double, f: (Double) -> Double): Double{
    return f(f(x))
}

fun composer(x: Double, f: (Double) -> Double, g: (Double) -> Double): Double{
    return g(f(x))
}

//////////////////////////////////// Exercice 14.9.1 ////////////////////////////////////////

fun transformer(list : List<Int>, Operation: (Int) -> Int): List<Int>{
    var NewList: List<Int> = listOf()
    for (nombre in list) {
        NewList += Operation(nombre)
    }
    return NewList
}

//////////////////////////////////// Exercice 14.9.2 ////////////////////////////////////////

fun List<Int>.transformer2( Operation: (Int) -> Int): List<Int>{
    var NewList: List<Int> = listOf()
    for (nombre in this) {
        NewList += Operation(nombre)
    }
    return NewList
}
//-----↓ deuxieme méthode ↓-----//
fun List<Int>.transformer21(Operation: (Int) -> Int) = this.map{Operation(it)}

//////////////////////////////////// Exercice 14.9.3 ////////////////////////////////////////

data class Observation(
    val objet: String,
    val temperatureK: Int,
    val typeSpectral: String,
    val estValide: Boolean
)

val fluxDonnees = listOf(
    Observation("Etoile-A", 5500, "G", true),
    Observation("Etoile-B", 3000, "M", false), // Donnée invalide
    Observation("Etoile-C", 12000, "B", true),
    Observation("Etoile-D", 4500, "K", true),
    Observation("Etoile-E", 25000, "O", true)
)

//////////////////////////////////// Exercice 14.9.4 ////////////////////////////////////////

data class Livre1(val titre: String, val estEmprunte: Boolean)


val bibliotheque = listOf(
    Livre1("Le Petit Prince", true),
    Livre1("1984", false),
    Livre1("La guerre des mouches", true),
    Livre1("Fondation", false)
)

//////////////////////////////////// Exercice 14.9.6 ////////////////////////////////////////

data class Exoplanete(val nom: String, val distanceAl: Int, val estHabitable: Boolean)


val catalogue = listOf(
    Exoplanete("Proxima Centauri b", 4, true),
    Exoplanete("Kepler-452b", 1400, true),
    Exoplanete("WASP-17b", 1300, false),
    Exoplanete("TRAPPIST-1e", 39, true),
    Exoplanete("HD 189733b", 64, false)
)

//////////////////////////////////// Exercice 14.9.7 ////////////////////////////////////////

data class CorpsCeleste2(val nom: String, val distanceUA: Double)

val catalogueSpatial = listOf(
    CorpsCeleste2("Mercure", 0.39),
    CorpsCeleste2("Venus", 0.72),
    CorpsCeleste2("Terre", 1.0),
    CorpsCeleste2("Mars", 1.52),
    CorpsCeleste2("Jupiter", 5.2),
    CorpsCeleste2("Saturne", 9.5),
    CorpsCeleste2("Uranus", 19.2),
    CorpsCeleste2("Neptune", 30.1),
    CorpsCeleste2("Pluton", 39.5),
    CorpsCeleste2("Eris", 67.7),
    CorpsCeleste2("Sedna", 480.0)
)

const val UA_EN_MILLIONS_KM = 149.6

//////////////////////////////////// Exercice 14.9.8 ////////////////////////////////////////

data class Candidat(val nom: String, val prenom: String, val moygen: Double, val pro: Double)
val lesCandidats = listOf(
    Candidat("Dupont", "Pierre", 11.0, 12.0), // OK, moyenne générale : 11, pro : 12
    Candidat("Durant", "Jean", 8.5, 11.0), // rattrapage OK
    Candidat("Jaouen", "Yann", 7.0, 8.0), // recalé
    Candidat("Le Flem", "Paul", 7.5, 15.0), // recalé
    Candidat("Ropartz", "Guy", 15.0, 17.0), // OK
    Candidat("Cras", "Jean", 9.0, 14.0), // rattrapage OK
    Candidat("Ravel", "Marcel", 9.5, 10.0), // recalé, moyenne<10
)
/*fun List<Candidat>.getListCandidatsRepeches():List<Candidat>{
    val candidatrepech: List<Candidat> = (this.filter{8< it.moygen && it.moygen < 10 &&  10< it.pro && it.pro <= 20 })
    return  candidatrepech
}*/
///this.filter{8< it.moygen && it.moygen < 10 &&  10< it.pro && it.pro <= 20 }.forEach { println("${it.nom}, ${it.moygen} - ${it.pro}")

fun List<Candidat>.getListCandidatsRepeches() = lesCandidats
        .filter {8 < it.moygen && it.moygen < 10 && it.pro > 10}
        .map {it.nom + " " + it.prenom + ", " + it.moygen + " - " + it.pro + "\n" }
        .joinToString(separator = "")

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

    //////////////////////////////////// Exercice 8.19.1 ////////////////////////////////////////
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

    bibliotheque.afficherTout()

    //////////////////////////////////// Exercice 9.8.2 ////////////////////////////////////////
    val unAstre = Astre.Planete(12742.0, 1, "Terre", "Planete")
    afficherMessage(unAstre)

    //////////////////////////////////// Exercice 10.6.1 ////////////////////////////////////////
    val mot: String? = "laboubou"
    val mot2: String? = "la bou bou"
    val mot3: String? = ""
    println(mot?.formaterImmatriculation())
    println(mot2?.formaterImmatriculation())
    println(mot3?.formaterImmatriculation())

    //////////////////////////////////// Exercice 10.6.2 ////////////////////////////////////////
    val res = conteneur(10.0, 2.0, 2.0)
    println(res.volume())

    //////////////////////////////////// Exercice 10.6.3 ////////////////////////////////////////
    val plat = Planete1(149.6, "2000-01-01T00:00", "Terre", -3.99)
    println(plat.nom.uppercase() + " :")
    println("brillant ? " + plat.estBrillant())
    println("JSON : {" + plat.toPlaneteJson() + "}")
    println("Proche du Soleil ? " + plat.estProche())

    //////////////////////////////////// Exercice 11.6.1 ////////////////////////////////////////
    CentreControleMaritime.emettreAlerte("[ALERTE N°1] TEMPÊTE DE FORCE 9 SUR LA ZONE IROISE")
    CentreControleMaritime.emettreAlerte("[ALERTE N°2] BROUILLARD DENSE DANS L'ESTUAIRE")
    CentreControleMaritime.afficherBilan()

    //////////////////////////////////// Exercice 11.6.2 ////////////////////////////////////////
    val config = "Voyager 1:Héliocentrique:600"
    val sonde = SondeSpaciale.depuisChaine(config)
    println("Sonde initialisée avec succès : " + sonde)
    println("Nom  : " + sonde.nom + " | Orbite : " + sonde.orbite + " | Autonomie : " + sonde.autonomieMois + " mois")

    //////////////////////////////////// Exercice 11.6.3 ////////////////////////////////////////
    val etoileObservee: TypeEtoile = TypeEtoile.M
    println("Classification : ${etoileObservee.nom}")
    println("Couleur dominante : ${etoileObservee.couleur}")
    print("Caractéristique thermique : ")
    // Grâce au mot-clé "sealed", le "when" est exhaustif et n'a pas besoin de "else"
    when (etoileObservee) {
        is TypeEtoile.O -> etoileObservee.decrireTemperature()
        is TypeEtoile.G -> etoileObservee.decrireTemperature()
        is TypeEtoile.M -> etoileObservee.decrireTemperature()
    }

    //////////////////////////////////// Exercice 13.6.1 ////////////////////////////////////////
    // 1. Une lambda qui prend un Int et retourne son double
    val doubler: (Int) -> Int = { x -> x * 2 }
    println(doubler(5))     // attendu : 10

    // 2. Une lambda qui prend deux Int et retourne leur somme
    // TODO : écrire la lambda ici
    val additionner: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
    println(additionner(3, 4)) // attendu : 7

    // 3. Une lambda sans paramètre qui retourne "Bonjour"
    // TODO : écrire la lambda ici
    val saluer = {"Bonjour"}
    println(saluer())      // attendu : Bonjour

    //////////////////////////////////// Exercice 13.6.2 ////////////////////////////////////////
    println(calculer(3.5) {x -> x * x})
    println(calculer(2.0) {x -> x * x * x})
    println(calculer(4.0) {x -> 1/x})
    println(calculer(7.2) {x -> -x})
    println(calculer(-6.5) {x -> Math.abs(x)})
    println(calculer(-6.5) {x -> if (x < 0.0) x * -1 else x })

    //////////////////////////////////// Exercice 13.6.3 ////////////////////////////////////////
    println(repeter(5) {i -> println("Tour n°$i") })

    //////////////////////////////////// Exercice 13.6.4 ////////////////////////////////////////
    val integrer: Double = integrer(0.0, 1.0, 1) {x -> x}
    val integrer2: Double = integrer(0.0, 1.0, 1000) {x -> x * x}
    println("Intégrale de x sur [0,1] : $integrer")
    println("Intégrale de x2 sur [0,1] : $integrer2")

    //////////////////////////////////// Exercice 13.6.5 ////////////////////////////////////////
    println("VAN brute, sans transformation : " + calculerVan(1000.0, 0.07, 10) { x -> x })
    println("VAN avec marge de sécurité de 5% : " + calculerVan(1000.0, 0.07, 10) { x -> x * 0.95})
    println("VAN  brute arrondie à l'entier le plus proche : " + round(calculerVan(1000.0, 0.07, 10) { x -> x}))
    println("VAN brute, estimée en Dollars (1EUR=1.08 USD), avec risque de change de 2% : " + 1.08 *calculerVan(1000.0, 0.07, 10) { x -> x * 0.98})

    //////////////////////////////////// Exercice 13.6.6 ////////////////////////////////////////
    println("f(f(5)) avec f(x)=x+10 :" + appliquerDeuxFois(5.0) { x -> x + 10 })
    println("f(f(3)) avec f(x)=x*2 :" + appliquerDeuxFois(3.0) { x -> x * 2 })
    println("h(4) =" + composer(4.0, { x -> x + 1 }, {x -> x * 2}))

    //////////////////////////////////// Exercice 14.9.1 ////////////////////////////////////////
    val list: List<Int> = listOf(1,2,3,4)
    println("Voici la premiere liste : " + list + "\nVoici la nouvelle liste : " + transformer(list) {x -> x + x} )

    //////////////////////////////////// Exercice 14.9.2 ////////////////////////////////////////

    println("Voici la premiere liste : " + list + "\nVoici la nouvelle liste : " + list.transformer2 {x -> x + x} )
    println("Voici la premiere liste : " + list + "\nVoici la nouvelle liste : " + list.transformer21 {x -> x + x} )

    //////////////////////////////////// Exercice 14.9.3 ////////////////////////////////////////

    val tri = fluxDonnees.filter{ it.temperatureK > 5000}
    println(tri)
    println("Analyse standard : " + tri[0].objet + "(" + tri[0].typeSpectral + ") en cours." )
    println("Priorité Haute : " + tri[1].objet + "(" + tri[1].typeSpectral + ") détectée." )
    println("Priorité Haute : " + tri[2].objet + "(" + tri[2].typeSpectral + ") détectée." )

    //////////////////////////////////// Exercice 14.9.4 ////////////////////////////////////////

    val emprunter = bibliotheque.sortedBy{it.titre.length}

    val livre = bibliotheque.filter{it.estEmprunte == true}
    livre.forEach{println(it.titre)}

    println(emprunter)

    //////////////////////////////////// Exercice 14.9.5 ////////////////////////////////////////

    val indices = (0..4)
    indices.forEach {x -> println(1 / Math.pow(2.0, x.toDouble()))}

    //////////////////////////////////// Exercice 14.9.6 ////////////////////////////////////////

    //catalogue.filter{it.estHabitable}.forEach{println("Catalogue des mondes habitables :"+it.nom+"("+it.distanceAl+" Al)")}
    val cat = catalogue.filter{it.estHabitable}.sortedBy{it.distanceAl}.map{it.nom + " (" + it.distanceAl + " Al)"}.joinToString(separator = " / ", prefix = " Catalogue des mondes habitables : ", postfix = ".")
    println(cat)

    //////////////////////////////////// Exercice 14.9.7 ////////////////////////////////////////

    catalogueSpatial.filter{it.distanceUA>30.0}.forEach{println("Objet lointain : ${it.nom} à ${(it.distanceUA * UA_EN_MILLIONS_KM).toInt()} millions de km.")}*/

    //////////////////////////////////// Exercice 14.9.8 ////////////////////////////////////////
    println("Liste des candidats repéchés :\n${lesCandidats.getListCandidatsRepeches()}")
}