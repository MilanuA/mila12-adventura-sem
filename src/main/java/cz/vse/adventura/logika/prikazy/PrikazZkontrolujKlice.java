package cz.vse.adventura.logika.prikazy;
import cz.vse.adventura.logika.Batoh;
import cz.vse.adventura.logika.HerniPlan;

import java.util.ArrayList;
import java.util.List;

/**
 *  Třída PrikazZkontrolujKlice implementuje pro hru příkaz zkontroluj klíče
 *
 * @author Alex Milanů
 * @version pro školní rok 2024/2025
 */
public class PrikazZkontrolujKlice implements IPrikaz {
    private static final String NAZEV = "zkontroluj";
    private HerniPlan plan;

    public PrikazZkontrolujKlice(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        Batoh batoh = plan.getBatoh();

        boolean maLedovy = batoh.getVec("ledový klíč") != null;
        boolean maDreveny = batoh.getVec("dřevěný klíč") != null;
        boolean maZlaty = batoh.getVec("zlatý klíč") != null;

        if (maLedovy && maDreveny && maZlaty) {
            return "Máš všechny tři klíče! Nyní můžeš odemknout bránu na Spišském hradě.";
        }

        List<String> chybejici = new ArrayList<>();
        if (!maLedovy) {
            chybejici.add("ledový klíč");
        }
        if (!maDreveny) {
            chybejici.add("dřevěný klíč");
        }
        if (!maZlaty) {
            chybejici.add("zlatý klíč");
        }

        return "Chybí ti následující klíče: " + String.join(", ", chybejici);
    }


    @Override
    public String getNazev() {
        return NAZEV;
    }

}
