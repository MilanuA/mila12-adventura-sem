package cz.vse.adventura.logika.veci;

import cz.vse.adventura.logika.jsonNacitani.VecDTO;
import cz.vse.adventura.logika.veci.pouzitelneVeci.Pacidlo;
import cz.vse.adventura.logika.veci.pouzitelneVeci.Prut;
import cz.vse.adventura.logika.veci.pouzitelneVeci.Sekera;
import cz.vse.adventura.logika.veci.pouzitelneVeci.Zrcadlo;

/**
 * Tovární třída pro vytváření instancí třídy Vec na základě dat z VecDTO
 * Rozhoduje, zda vytvořit běžnou nebo použitelnou věc dle jejího názvu a vlastností
 *
 *@author     Alex Milanů
 *@version    pro školní rok 2024/2025
 *
 */
public class VecFactory {

    /**
     * Vytváří instanci třídy {@code Vec} nebo její podtřídy na základě zadaných dat.
     * <p>
     * Pokud je věc označena jako použitelná, vytvoří se instance příslušné podtřídy
     * podle názvu. Pokud není použitelná, vrací se instance základní třídy {@code Vec}.
     *
     * @return Instance třídy {@code Vec} nebo její podtřídy
     */

    public static Vec vytvorVec(VecDTO data) {
        if (!data.pouzitelna()) {
            return new Vec(data.nazev(), data.prenositelna(), data.hmotnost(), false);
        }

        return switch (data.nazev()) {
            case "sekera" -> new Sekera(data.nazev(), data.prenositelna(), data.hmotnost());
            case "zrcadlo" -> new Zrcadlo(data.nazev(), data.prenositelna(), data.hmotnost());
            case "páčidlo" -> new Pacidlo(data.nazev(), data.prenositelna(), data.hmotnost());
            case "prut" -> new Prut(data.nazev(), data.prenositelna(), data.hmotnost());
            default -> new Vec(data.nazev(), data.prenositelna(), data.hmotnost(), false);
        };
    }
}

