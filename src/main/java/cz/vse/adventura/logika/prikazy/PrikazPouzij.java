package cz.vse.adventura.logika.prikazy;

import cz.vse.adventura.logika.HerniPlan;
import cz.vse.adventura.logika.Prostor;
import cz.vse.adventura.logika.veci.PouzitelnaVec;
import cz.vse.adventura.logika.veci.Vec;

/**
 *  Třída PrikazPouzij implementuje pro hru příkaz použij věc
 *
 *@author    Alex Milanů
 *@version    pro školní rok 2024/2025
 *
 */

public class PrikazPouzij implements IPrikaz {
    private static final String NAZEV = "použij";
    private HerniPlan plan;


    public PrikazPouzij(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length != 1) {
            return "Nevím, co mám použít.";
        }

        String nazevVeci = parametry[0].toLowerCase();
        Vec vec = plan.getBatoh().getVec(nazevVeci);

        if (vec == null) {
            return "Věc není v batohu!";
        }
        else if (!vec.isJePouzitelna())
        {
            return "Věc není použitelná!";
        }

        PouzitelnaVec pouzitelnaVec = (PouzitelnaVec) vec;
        return pouzitelnaVec.pouzij(plan);
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
