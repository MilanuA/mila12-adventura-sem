import cz.vse.adventura.logika.*;
import cz.vse.adventura.logika.prikazy.*;
import cz.vse.adventura.logika.veci.PouzitelnaVec;
import cz.vse.adventura.logika.veci.Vec;
import cz.vse.adventura.logika.veci.pouzitelneVeci.Prut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrikazyKliceTest {

    private HerniPlan plan;
    private Batoh batoh;

    private static final String LEDOVY = "ledový klíč";
    private static final String DREVENY = "dřevěný klíč";
    private static final String ZLATY = "zlatý klíč";

    @BeforeEach
    void setUp() {
        batoh = new Batoh(10);
        plan = new HerniPlan(batoh);
        plan.setAktualniProstor(new Prostor("hrad", "Popis hradu")); // nastavíme hrad pro odemknutí
    }

    @Test
    void testZkontrolujKliceVsechny() {
        batoh.pridejVec(new Vec(LEDOVY, true));
        batoh.pridejVec(new Vec(DREVENY, true));
        batoh.pridejVec(new Vec(ZLATY, true));

        PrikazZkontrolujKlice prikaz = new PrikazZkontrolujKlice(plan);
        String vysledek = prikaz.provedPrikaz();
        assertTrue(vysledek.contains("Máš všechny tři klíče"));
    }

    @Test
    void testZkontrolujKliceChybiNektere() {
        batoh.pridejVec(new Vec(LEDOVY, true));

        PrikazZkontrolujKlice prikaz = new PrikazZkontrolujKlice(plan);
        String vysledek = prikaz.provedPrikaz();
        assertTrue(vysledek.contains("Chybí ti následující klíče"));
        assertTrue(vysledek.contains(DREVENY));
        assertTrue(vysledek.contains(ZLATY));
    }

    @Test
    void testOdemkniBezKlicu() {
        PrikazOdemkni prikaz = new PrikazOdemkni(plan);
        String vysledek = prikaz.provedPrikaz();
        assertTrue(vysledek.contains("Nemáš všechny klíče"));
    }

    @Test
    void testOdemkniMimoHrad() {
        plan.setAktualniProstor(new Prostor("les", "Popis lesa"));
        PrikazOdemkni prikaz = new PrikazOdemkni(plan);
        String vysledek = prikaz.provedPrikaz();
        assertTrue(vysledek.contains("Příkaz lze použít jen na Spišském hradu!"));
    }

    @Test
    void testOdemkniSeVsemiKlici() {
        batoh.pridejVec(new Vec(LEDOVY, true));
        batoh.pridejVec(new Vec(DREVENY, true));
        batoh.pridejVec(new Vec(ZLATY, true));

        PrikazOdemkni prikaz = new PrikazOdemkni(plan);
        String vysledek = prikaz.provedPrikaz();
        assertTrue(vysledek.toLowerCase().contains("gratuluji"));
    }

    @Test
    void testPouzijVecNepouzitelna() {
        Vec vec = new Vec("kámen", true);
        batoh.pridejVec(vec);

        PrikazPouzij prikaz = new PrikazPouzij(plan);
        String vysledek = prikaz.provedPrikaz("kámen");
        assertTrue(vysledek.contains("není použitelná"));
    }

    @Test
    void testPouzijVecPouzitelnaSpatneMisto() {
        PouzitelnaVec prut = new Prut("prut", true, 1);
        batoh.pridejVec(prut);

        PrikazPouzij prikaz = new PrikazPouzij(plan);
        String vysledek = prikaz.provedPrikaz("prut");
        assertEquals("Prut zde nelze použít!", vysledek);
    }

    @Test
    void testPouzijVecPouzitelnaSpravneMisto() {
        PouzitelnaVec prut = new Prut("prut", true, 1);

        plan.setAktualniProstor(new Prostor("dunaj", "popis"));
        batoh.pridejVec(prut);

        PrikazPouzij prikaz = new PrikazPouzij(plan);
        String vysledek = prikaz.provedPrikaz("prut");
        assertTrue(vysledek.contains("Použil jsi prut"));
    }

    @Test
    void testPouzijVecNeniVBatohu() {
        PrikazPouzij prikaz = new PrikazPouzij(plan);
        String vysledek = prikaz.provedPrikaz("lucerna");
        assertTrue(vysledek.contains("není v batohu"));
    }

    @Test
    void testPouzijBezParametru() {
        PrikazPouzij prikaz = new PrikazPouzij(plan);
        String vysledek = prikaz.provedPrikaz();
        assertTrue(vysledek.contains("Nevím, co mám použít"));
    }

    @Test
    void testSeberVecNepouzitelna() {
        Vec vec = new Vec("kámen", true);
        plan.getAktualniProstor().pridejVec(vec);

        PrikazSeber prikaz = new PrikazSeber(plan);
        String vysledek = prikaz.provedPrikaz("kámen");
        assertTrue(vysledek.contains("byla vložena do batohu"));
    }

    @Test
    void testSeberVecPouzitelna() {
        PouzitelnaVec prut = new Prut("prut", true, 1);
        plan.getAktualniProstor().pridejVec(prut);

        PrikazSeber prikaz = new PrikazSeber(plan);
        String vysledek = prikaz.provedPrikaz("prut");
        assertTrue(vysledek.contains("a lze ji použít"));
    }

    @Test
    void testSeberBezParametru() {
        PrikazSeber prikaz = new PrikazSeber(plan);
        String vysledek = prikaz.provedPrikaz();
        assertTrue(vysledek.contains("Napiš název věci"));
    }
}
