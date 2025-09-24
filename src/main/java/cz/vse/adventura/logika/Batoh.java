package cz.vse.adventura.logika;

import cz.vse.adventura.logika.veci.PouzitelnaVec;
import cz.vse.adventura.logika.veci.Vec;
import cz.vse.adventura.utils.Barvy;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Třída {@code Batoh} reprezentuje hráčův inventář (batoh), ve kterém může nosit věci
 * Batoh má omezenou maximální hmotnost, kterou nelze překročit
 * Umožňuje přidávání, odebírání a popis věcí, které hráč aktuálně nese
 *
 * @author Alex Milanů
 * @version pro školní rok 2024/2025
 */
public class Batoh {

    private int maxHmotnost;
    private Map<String, Vec> veci = new HashMap<>();

    /**
     * Výchozí konstruktor bez omezení hmotnosti
     */
    public Batoh() {
    }

    /**
     * Konstruktor s nastavením maximální hmotnosti
     *
     * @param maxHmotnost maximální hmotnost batohu
     */
    public Batoh(int maxHmotnost) {
        this.maxHmotnost = maxHmotnost;
        this.veci = new HashMap<>();
    }

    /**
     * Konstruktor s předdefinovanou mapou věcí
     *
     * @param veci mapa věcí, které budou v batohu
     */
    public Batoh(Map<String, Vec> veci) {
        this.veci = veci;
    }

    /**
     * Konstruktor s nastavením maximální hmotnosti a množinou věcí
     *
     * @param maxHmotnost maximální hmotnost batohu
     * @param veci množina věcí k vložení
     */
    public Batoh(int maxHmotnost, Set<Vec> veci) {
        this.maxHmotnost = maxHmotnost;
        this.veci = new HashMap<>();

        for (Vec vec : veci) {
            this.veci.put(vec.getNazev(), vec);
        }
    }

    /**
     * Pokusí se přidat věc do batohu
     *
     * @param v věc k přidání
     * @return přidaná věc
     */
    public Vec pridejVec(Vec v) {
        String nazev = v.getNazev();

        if (!vejdeSe(v))
            throw new IllegalStateException("Věc " + nazev + " se do batohu nevejde");

        if (veci.containsKey(nazev))
            throw new IllegalStateException("Věc " + nazev + " je již v batohu přítomna");

        if (!v.isPrenositelna())
            throw new IllegalStateException("Věc " + nazev + " není přenositelná");

        veci.put(nazev, v);
        return v;
    }

    /**
     * Vrací věc podle jejího názvu
     *
     * @param nazev název věci
     * @return instance věci nebo {@code null}, pokud neexistuje
     */
    public Vec getVec(String nazev) {
        return veci.get(nazev);
    }

    /**
     * Odebere věc z batohu podle názvu
     *
     * @param nazev název věci
     * @return odebraná věc
     */
    public Vec odeberVec(String nazev) {
        if (!veci.containsKey(nazev))
            throw new IllegalStateException("Věc " + nazev + " v batohu není");

        Vec v = getVec(nazev);
        veci.remove(nazev);
        return v;
    }

    /**
     * Odebere konkrétní instanci věci z batohu
     *
     * @param v instance věci k odebrání
     * @return odebraná věc
     */
    public Vec odeberVec(Vec v) {
        if (!veci.containsValue(v))
            throw new IllegalStateException("Věc " + v.getNazev() + " v batohu není");

        veci.remove(v.getNazev());
        return v;
    }

    /**
     * Vrací textový popis aktuálního obsahu batohu
     * Zobrazuje názvy věcí a jejich hmotnost, barevně zvýrazní použitelné věci
     *
     * @return textový popis batohu
     */
    public String popisBatohu() {
        if (veci.isEmpty()) {
            return "[Batoh]: prázdný | 0 kg";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[Batoh]: ");

        for (Vec vec : veci.values()) {
            String nazev = vec.getNazev();
            String zobrazeni;

            if (vec instanceof PouzitelnaVec) {
                zobrazeni = Barvy.RED + nazev + Barvy.RESET;
            } else {
                zobrazeni = nazev;
            }

            sb.append(zobrazeni)
                    .append(" (")
                    .append(vec.getHmotnost())
                    .append(" kg), ");
        }

        sb.setLength(sb.length() - 2);
        sb.append(" | ").append(getAktualniHmotnost()).append(" kg");

        return sb.toString();
    }

    /**
     * Zjišťuje, zda se daná věc vejde do batohu
     *
     * @param v věc ke kontrole
     * @return {@code true}, pokud se věc vejde, jinak {@code false}
     */
    private boolean vejdeSe(Vec v) {
        return getAktualniHmotnost() + v.getHmotnost() <= this.maxHmotnost;
    }

    /**
     * Vrací aktuální hmotnost všech věcí v batohu
     *
     * @return celková hmotnost věcí v batohu
     */
    private int getAktualniHmotnost() {
        int sum = 0;

        for (Vec vec : veci.values()) {
            sum += vec.getHmotnost();
        }
        return sum;
    }
}
