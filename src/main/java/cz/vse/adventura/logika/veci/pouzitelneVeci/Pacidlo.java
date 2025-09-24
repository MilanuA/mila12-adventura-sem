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
public class Pacidlo extends PouzitelnaVec {
    public Pacidlo(String nazev, boolean prenositelna, Integer hmotnost) {
        super(nazev, prenositelna, hmotnost);
    }

    @Override
    public String pouzij(HerniPlan plan) {

        if(!muzeBytPouzita(plan)) return "Páčidlo zde nelze použít!";

        List<String> predmety = List.of("prsten", "mapa");

        String pridanoText = pridejPredmety(plan, predmety);
        return "Použil jsi páčidlo na truhlu. V truhle se nacházel <prsten> a <mapa>.\n" + pridanoText ;
    }

    @Override
    public boolean muzeBytPouzita(HerniPlan plan) {
        return Objects.equals(plan.getAktualniProstor().getNazev(), "zřícenina");
    }
}
