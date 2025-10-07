package cz.vse.adventura.utils;

/**
 * Třída {@code Barvy} obsahuje kódy pro barevné a formátované výpisy do konzole i grafické rozhraní (JavaFX)
 * <p>Podle aktuálního režimu (nastaveného metodou {@link #povolitAnsi(boolean)})
 *  vrací buď ANSI kódy (pro konzoli), nebo značky pro GUI</p>
 * @author Alex Milanů
 * @version pro školní rok 2024/2025
 */
public class Barvy {

    private static boolean pouzitAnsi = true;

    public static String RESET;
    public static String RED;
    public static String GREEN;
    public static String YELLOW;
    public static String BLUE;
    public static String BOLD;
    public static String ITALIC;

    static {
        aktualizovat();
    }

    /**
     * Přepne, zda se mají používat ANSI kódy (true = konzole, false = GUI)
     */
    public static void povolitAnsi(boolean povolit) {
        pouzitAnsi = povolit;
        aktualizovat();
    }

    /**
     * Přepočítá hodnoty barev podle aktuálního režimu
     */
    private static void aktualizovat() {
        if (pouzitAnsi) {
            RESET  = "\u001B[0m";
            RED    = "\u001B[31m";
            GREEN  = "\u001B[32m";
            YELLOW = "\u001B[33m";
            BLUE   = "\u001B[34m";
            BOLD   = "\u001B[1m";
            ITALIC = "\u001B[3m";
        } else {
            RESET  = "";
            RED    = "";
            GREEN  = "";
            YELLOW = "";
            BLUE   = "";
            BOLD   = "";
            ITALIC = "";
        }
    }
}
