import cz.vse.adventura.logika.*;
import cz.vse.adventura.logika.prikazy.PrikazDej;
import cz.vse.adventura.logika.veci.Vec;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PrikazDejTest {

    private static final String NAZEV_VECI = "jablko";

    private HerniPlan plan;
    private PrikazDej prikazDej;
    private Postava postava;

    @BeforeEach
    void setUp() {
        plan = new HerniPlan(new Batoh(10));
        prikazDej = new PrikazDej(plan);

        postava = new Postava("hlidac", List.of("Ahoj"), List.of("Pokracovani dialogu."), NAZEV_VECI, NAZEV_VECI);
        plan.getAktualniProstor().pridejPostavu(postava);

        Vec vec = new Vec(NAZEV_VECI, true);
        plan.getBatoh().pridejVec(vec);

        Map<String, Vec> dostupneVeci = new HashMap<>();
        dostupneVeci.put(NAZEV_VECI, vec);
        plan.setDostupneVeci(dostupneVeci);
    }

    @Test
    void testDejVecPostave() {
        String vysledek = prikazDej.provedPrikaz(NAZEV_VECI, "hlidac");
        assertTrue(vysledek.contains("si vzal"), "Postava by měla přijmout věc.");
        assertTrue(vysledek.contains("dal ti"), "Postava by měla dát odměnu.");
    }


    @Test
    void testDejVecNeexistujiciPostave() {
        String vysledek = prikazDej.provedPrikaz(NAZEV_VECI, "nepritel");
        assertTrue(vysledek.contains("Postava 'nepritel' tady není."), "Postava by neměla být nalezena.");
    }

    @Test
    void testDejVecKteraNeniVBatohu() {
        plan.getBatoh().odeberVec(NAZEV_VECI);
        String vysledek = prikazDej.provedPrikaz(NAZEV_VECI, "hlidac");
        assertTrue(vysledek.contains("Tuhle věc nemáš v batohu."), "Nemělo by jít dát věc, která není v batohu.");
    }

    @Test
    void testDejBezDvouParametru() {
        String vysledek = prikazDej.provedPrikaz(NAZEV_VECI);
        assertTrue(vysledek.contains("dej <věc> <postava>"), "Mělo by upozornit na chybějící parametry.");
    }
}
