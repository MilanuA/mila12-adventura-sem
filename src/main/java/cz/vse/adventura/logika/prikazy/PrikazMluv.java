package cz.vse.adventura.logika.prikazy;
import cz.vse.adventura.logika.HerniPlan;
import cz.vse.adventura.logika.Postava;
import cz.vse.adventura.logika.dialogue.DialogueManager;

/**
 *  Třída PrikazMluv implementuje pro hru příkaz mluv
 *
 * @author Alex Milanů
 * @version pro školní rok 2024/2025
 */
public class PrikazMluv implements IPrikaz {
    private static final String NAZEV = "mluv";
    private HerniPlan plan;

    public PrikazMluv(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length != 1) {
            return "Nevím, s kým mám mluvit. Napiš prosím jméno postavy.";
        }

        String nazevPostavy = parametry[0].toLowerCase();

        Postava postava = plan.getAktualniProstor().getPostavu(nazevPostavy);

        if (postava == null) {
            return "Taková postava tu není.";
        }

        try {
            DialogueManager.getInstance().zacitDialog(postava);
            return "Rozhovor s postavou " + postava.getNazev() + " zahájen.\n\n" + DialogueManager.getInstance().dalsiRadek();
        } catch (Exception e) {
            return "Něco se pokazilo: " + e.getMessage();
        }
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }

}
