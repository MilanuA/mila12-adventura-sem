package cz.vse.adventura.logika.prikazy;
import cz.vse.adventura.logika.HerniPlan;
import cz.vse.adventura.logika.veci.Vec;

/**
 *  Třída PrikazPoloz implementuje pro hru příkaz polož
 *
 * @author Alex Milanů
 * @version pro školní rok 2024/2025
 */
public class PrikazPoloz implements IPrikaz {

    private static final String NAZEV = "poloz";

    private HerniPlan plan;

    public PrikazPoloz(HerniPlan plan) {
        this.plan = plan;
    }


    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length != 1) {
            return "Nevím, co mám polozit. Napiš název věci";
        }

        String nazevVeci = parametry[0];

        try{
            Vec vec = plan.seberVec(nazevVeci);
            return "Věc " + vec.getNazev() + " byla vložena do prostoru";
        }
        catch (Exception e) {
            return "Něco se pokazilo: " + e.getMessage();
        }
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
}
