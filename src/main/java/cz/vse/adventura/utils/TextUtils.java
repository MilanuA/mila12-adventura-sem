package cz.vse.adventura.utils;

import cz.vse.adventura.logika.Postava;
import cz.vse.adventura.logika.Prostor;
import cz.vse.adventura.logika.veci.Vec;

import java.util.Collection;
import java.util.StringJoiner;

/**
 * Třída {@code TextUtils} obsahuje pomocné metody pro formátování textových výpisů
 * @author Alex Milanů
 * @version pro školní rok 2024/2025
 */
public class TextUtils
{
    /**
     * Vrací barevně formátovaný popis kolekce prvků (např. prostorů, věcí, postav) ve hře
     *
     * @param nazev      popisný název skupiny (např. "Východy", "Věci")
     * @param collection kolekce prvků, které se mají zobrazit
     * @return formátovaný řetězec s názvy prvků nebo informací o prázdnosti
     */
    public static String popisElementu(String nazev, Collection<?> collection) {
        if (collection.isEmpty()) {
            return Barvy.BOLD + Barvy.BLUE + nazev + ":" + Barvy.RESET + " žádné";
        }

        StringJoiner joiner = new StringJoiner(", ");
        for (Object element : collection) {
            String jmeno = null;

            if (element instanceof Prostor) {
                jmeno = ((Prostor) element).getNazev();
            } else if (element instanceof Vec) {
                jmeno = ((Vec) element).getNazev();
            } else if (element instanceof Postava) {
                jmeno = ((Postava) element).getNazev();
            }

            if (jmeno != null) {
                joiner.add(capitalize(jmeno));
            }
        }

        return Barvy.BOLD + Barvy.BLUE + nazev + ":" + Barvy.RESET + " " + joiner.toString();
    }

    /**
     * Pomocná metoda, která převádí první písmeno textu na velké
     *
     * @param text vstupní text
     * @return text s prvními písmenem velkým, pokud byl neprázdný
     */
    private static String capitalize(String text) {
        if (text == null || text.isEmpty()) return text;
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}
