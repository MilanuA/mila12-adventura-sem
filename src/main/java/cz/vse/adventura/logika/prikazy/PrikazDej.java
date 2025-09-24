package cz.vse.adventura.logika.prikazy;
import cz.vse.adventura.logika.*;
import cz.vse.adventura.logika.veci.Vec;

import java.util.Map;

/**
 *  Třída PrikazDej implementuje pro hru příkaz dej
 *
 * @author Alex Milanů
 * @version pro školní rok 2024/2025
 */
public class PrikazDej implements IPrikaz {
    private static final String NAZEV = "dej";
    private HerniPlan plan;

    public PrikazDej(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length != 2) {
            return "Použij příkaz ve tvaru: dej <věc> <postava>";
        }

        String nazevVeci = parametry[0];
        String nazevPostavy = parametry[1];

        Prostor prostor = plan.getAktualniProstor();
        Batoh batoh = plan.getBatoh();
        Map<String, Vec> dostupneVeci = plan.getDostupneVeci();

        Postava postava = prostor.getPostavu(nazevPostavy);
        if (postava == null) {
            return "Postava '" + nazevPostavy + "' tady není.";
        }

        Vec vec = batoh.getVec(nazevVeci);
        if (vec == null) {
            return "Tuhle věc nemáš v batohu.";
        }

        batoh.odeberVec(nazevVeci);
        return postava.prijmiVec(vec, batoh, dostupneVeci);
    }


    @Override
    public String getNazev() {
        return NAZEV;
    }

}
