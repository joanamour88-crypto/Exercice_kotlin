package org.exemple.physique

import org.example.physique.dureeSurTerre
import org.example.physique.longueurSurTerre
import org.example.physique.vitesseRepereTerre
import kotlin.test.Test
import kotlin.test.assertEquals

class RelativiteTest {
    @Test
    fun testDureeSurTerre() {
        val resultat = dureeSurTerre(60.0, 299000.0)
        assertEquals(825.7444,resultat, 0.0001)
    }

    @Test
    fun testLongueurSurTerre() {
        val resultat = longueurSurTerre(100.0, 299000.0)
        assertEquals(7.2661,resultat, 0.0001)
    }

    @Test
    fun testVitesseRepereTerre() {
        val v1 = 150000.0
        val v2 = 150000.0
        val resultat = vitesseRepereTerre(v1, v2)
        assert(resultat < 299792.458)
        assertEquals(239933.5359,resultat, 0.0001)
    }
}