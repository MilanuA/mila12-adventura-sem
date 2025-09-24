package cz.vse.adventura.logika.prikazy;
import cz.vse.adventura.logika.Batoh;
import cz.vse.adventura.logika.HerniPlan;
import cz.vse.adventura.utils.Barvy;

import java.util.Objects;

/**
 *  Třída PrikazOdemkni implementuje pro hru příkaz odemkni
 *
 * @author Alex Milanů
 * @version pro školní rok 2024/2025
 */
public class PrikazOdemkni implements IPrikaz {

    private static final String NAZEV = "odemkni";

    private final HerniPlan plan;

    public PrikazOdemkni(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {

        if(!Objects.equals(plan.getAktualniProstor().getNazev(), "hrad"))
            return "Příkaz lze použít jen na Spišském hradu!";

        Batoh batoh = plan.getBatoh();

        boolean maLedovy = batoh.getVec("ledový klíč") != null;
        boolean maDreveny = batoh.getVec("dřevěný klíč") != null;
        boolean maZlaty = batoh.getVec("zlatý klíč") != null;

        if(maLedovy && maDreveny && maZlaty)
            return EndGame();
        else
            return "Nemáš všechny klíče. Vrať se, až je budeš mít všechny.";
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

    private String EndGame() {
        String zprava = Barvy.BOLD + Barvy.GREEN +
                "\n====================================\n" +
                "  GRATULUJI! Odemkl jsi bránu a vyhrál hru!\n" +
                "====================================\n" +
                Barvy.RESET;

        System.out.println(zprava);
        System.exit(0);
        return "";
    }
}
