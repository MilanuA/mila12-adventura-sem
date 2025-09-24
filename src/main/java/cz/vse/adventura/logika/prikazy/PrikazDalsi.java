package cz.vse.adventura.logika.prikazy;
import cz.vse.adventura.logika.dialogue.DialogueManager;

/**
 *  Třída PrikazDalsi implementuje pro hru příkaz další
 *
 * @author Alex Milanů
 * @version pro školní rok 2024/2025
 */
public class PrikazDalsi implements IPrikaz {
    private static final String NAZEV = "další";

    public PrikazDalsi() {

    }

    @Override
    public String provedPrikaz(String... parametry) {
        return DialogueManager.getInstance().dalsiRadek();
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }

}
