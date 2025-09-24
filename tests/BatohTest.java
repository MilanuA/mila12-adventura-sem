import cz.vse.adventura.logika.Batoh;
import cz.vse.adventura.logika.veci.Vec;
import cz.vse.adventura.logika.veci.pouzitelneVeci.Sekera;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BatohTest {

    private Batoh batoh;
    private Vec jablko;
    private Sekera sekera;
    private Vec tezkyKamen;

    @BeforeEach
    void setUp() {
        batoh = new Batoh(10);
        jablko = new Vec("jablko", true, 1, false);
        sekera = new Sekera("sekera",  true, 2);
        tezkyKamen = new Vec("kamen", false, 20, false);
    }

    @Test
    void testPridejVecUspech() {
        batoh.pridejVec(jablko);
        assertEquals(jablko, batoh.getVec("jablko"));
    }

    @Test
    void testPridejVecNevlezlSe() {
        assertThrows(IllegalStateException.class, () -> batoh.pridejVec(tezkyKamen));
    }

    @Test
    void testPridejVecNepřenositelná() {
        Vec neprenositelna = new Vec("socha", false, 1,false) ;
        assertThrows(IllegalStateException.class, () -> batoh.pridejVec(neprenositelna));
    }

    @Test
    void testPridejVecUzExistuje() {
        batoh.pridejVec(jablko);
        assertThrows(IllegalStateException.class, () -> batoh.pridejVec(jablko));
    }

    @Test
    void testOdeberVecPodleNazvu() {
        batoh.pridejVec(jablko);
        Vec odebrana = batoh.odeberVec("jablko");
        assertEquals(jablko, odebrana);
        assertNull(batoh.getVec("jablko"));
    }

    @Test
    void testOdeberVecPodleInstance() {
        batoh.pridejVec(jablko);
        Vec odebrana = batoh.odeberVec(jablko);
        assertEquals(jablko, odebrana);
        assertNull(batoh.getVec("jablko"));
    }

    @Test
    void testOdeberNeexistujiciVec() {
        assertThrows(IllegalStateException.class, () -> batoh.odeberVec("neexistujici"));
    }

    @Test
    void testPopisBatohuPrazdny() {
        assertEquals("[Batoh]: prázdný | 0 kg", batoh.popisBatohu());
    }

    @Test
    void testPopisBatohuSVecmi() {
        batoh.pridejVec(jablko);
        batoh.pridejVec(sekera);
        String popis = batoh.popisBatohu();
        assertTrue(popis.contains("jablko (1 kg)"));
        assertTrue(popis.contains("sekera"));
        assertTrue(popis.contains("3 kg"));
    }

    @Test
    void testKonstruktorSPreddefinovanymiVecmi() {
        Batoh predemNaplneny = new Batoh(15, Set.of(jablko, sekera));
        assertNotNull(predemNaplneny.getVec("jablko"));
        assertNotNull(predemNaplneny.getVec("sekera"));
    }

    @Test
    void testHmotnostNePresahneLimit() {
        batoh.pridejVec(jablko);
        batoh.pridejVec(sekera);
        assertThrows(IllegalStateException.class, () -> batoh.pridejVec(new Vec("zlatá cihla", false, 8, false)));
    }
}
