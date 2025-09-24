package cz.vse.adventura.logika.veci.pouzitelneVeci;

import cz.vse.adventura.logika.HerniPlan;
import cz.vse.adventura.logika.veci.PouzitelnaVec;

import java.util.List;
import java.util.Objects;

/**
 * Třída slouží jako implementace použitelné věci
 *
 * @author Alex Milanů
 * @version pro školní rok 2024/2025
 */
public class Prut extends PouzitelnaVec {
    public Prut(String nazev, boolean prenositelna, Integer hmotnost) {
        super(nazev, prenositelna, hmotnost);
    }

    @Override
    public String pouzij(HerniPlan plan) {

        if(!muzeBytPouzita(plan)) return "Prut zde nelze použít!";

        List<String> predmety = List.of("ryba");

        String pridanoText = pridejPredmety(plan, predmety);

        return "Použil jsi prut a chytil si <rybu>.\n" + pridanoText;
    }

    @Override
    public boolean muzeBytPouzita(HerniPlan plan) {

        return Objects.equals(plan.getAktualniProstor().getNazev(), "dunaj");
    }
}
