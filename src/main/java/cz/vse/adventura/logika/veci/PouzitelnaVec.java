package cz.vse.adventura.logika.veci;

import cz.vse.adventura.logika.Batoh;
import cz.vse.adventura.logika.HerniPlan;
import cz.vse.adventura.logika.Prostor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *  Base třída pro věci, které se dají použít ve hře
 *
 *@author     Alex Milanů
 *@version    pro školní rok 2024/2025
 *
 */
public abstract class PouzitelnaVec extends Vec {

    public PouzitelnaVec(String nazev, boolean prenositelna, Integer hmotnost) {
        super(nazev, prenositelna, hmotnost, true);
    }

    /**
     * Pokusí se přidat seznam věcí do batohu hráče
     * Pokud se věc nevejde nebo není přenositelná, zůstane v aktuálním prostoru
     * Vrací zprávu o úspěchu nebo seznam nepřidaných věcí
     *
     * @param predmety seznam věcí k přidání
     * @return zpráva o výsledku pokusu přidání věcí
     */
    protected String pridejPredmety(HerniPlan plan, List<String> predmety) {
        Batoh batoh = plan.getBatoh();
        batoh.odeberVec(nazev);

        Prostor prostor = plan.getAktualniProstor();
        List<String> nepruchoziVeci = new ArrayList<>();
        Map<String, Vec> dostupneVeci = plan.getDostupneVeci();

        for (String nazev : predmety) {
            Vec vec = dostupneVeci.get(nazev);
            if (vec == null) {
                nepruchoziVeci.add(nazev + " (neexistuje)");
                continue;
            }

            try {
                batoh.pridejVec(vec);
            } catch (Exception e) {
                nepruchoziVeci.add(nazev);
                prostor.pridejVec(vec);
            }
        }


        if (nepruchoziVeci.isEmpty()) {
            return "Všechny věci byly úspěšně přidány do batohu.";
        } else {
            return "Následující věci nebyly přidány do batohu a zůstaly v místnosti: " +
                    String.join(", ", nepruchoziVeci) + ".";
        }
    }

    /**
     * Abstraktní metoda, která definuje chování po použití věci
     * Tuto metodu musí implementovat každá konkrétní podtřída
     */
    public abstract String pouzij(HerniPlan plan);

    /**
     * Určuje, zda lze danou věc v aktuální situaci použít
     *
     * @return {@code true}, pokud lze věc použít, jinak {@code false}
     */
    public abstract boolean muzeBytPouzita(HerniPlan plan);
}

