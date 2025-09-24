package cz.vse.adventura.logika;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import cz.vse.adventura.logika.veci.Vec;

import java.util.List;
import java.util.Map;

/**
 * Třída {@code Postava} reprezentuje postavu ve hře, se kterou může hráč interagovat
 * Postava má dialogy, může požadovat určitou věc a na oplátku dát hráči odměnu
 * @author Alex Milanů
 * @version pro školní rok 2024/2025
 */
public class Postava {
    private String nazev;
    private List<String> dialog;
    private List<String> pokracovaniDialogu;
    private String pozadovanaVec;
    private String odmena;
    private boolean promluvilSPostavou;

    @JsonCreator
    public Postava(
            @JsonProperty("nazev") String nazev,
            @JsonProperty("dialog") List<String> dialog,
            @JsonProperty("pokracovaniDialogu") List<String> pokracovaniDialogu,
            @JsonProperty("odmena") String odmena,
            @JsonProperty("pozadovanaVec") String pozadovanaVec) {
        this.nazev = nazev;
        this.dialog = dialog;
        this.pokracovaniDialogu = pokracovaniDialogu;
        this.odmena = odmena;
        this.pozadovanaVec = pozadovanaVec;
    }

    /**
     * Vrací název postavy.
     *
     * @return název postavy
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * Nastavuje název postavy.
     *
     * @param nazev nový název postavy
     */
    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    /**
     * Vrací řádek dialogu podle indexu. Zohledňuje, zda již hráč s postavou mluvil.
     *
     * @param index index řádku dialogu
     * @return odpovídající řádek nebo {@code null}, pokud index není platný
     */
    public String getDalsiRadek(int index) {
        List<String> aktivniDialog = promluvilSPostavou ? pokracovaniDialogu : dialog;

        if (index >= 0 && index < aktivniDialog.size()) {
            return aktivniDialog.get(index);
        }

        return null;
    }

    /**
     * Nastavuje, zda hráč již s postavou mluvil.
     *
     * @param promluvilSPostavou {@code true}, pokud hráč již mluvil, jinak {@code false}
     */
    public void setPromluvilSPostavou(boolean promluvilSPostavou) {
        this.promluvilSPostavou = promluvilSPostavou;
    }

    /**
     * Zpracovává předání věci postavě. Pokud jde o správnou požadovanou věc,
     * postava ji přijme a případně hráči dá odměnu.
     *
     * @param vec           věc, kterou hráč předává
     * @param batoh         batoh hráče, kam může být vložena odměna
     * @param dostupneVeci  mapa všech věcí ve hře podle názvu
     * @return zpráva pro hráče o výsledku interakce
     */
    public String prijmiVec(Vec vec, Batoh batoh, Map<String, Vec> dostupneVeci) {
        if (pozadovanaVec == null || !pozadovanaVec.equalsIgnoreCase(vec.getNazev())) {
            return getNazev() + " tuhle věc nechce.";
        }

        if (odmena != null && !odmena.isEmpty()) {
            Vec odmenenaVec = dostupneVeci.get(odmena);

            if (odmenenaVec != null) {
                try {
                    batoh.pridejVec(odmenenaVec);
                    return getNazev() + " si vzal " + vec.getNazev() + " a dal ti " + odmena + ".";
                } catch (IllegalStateException e) {
                    return "Věc byla odebrána, ale odměnu nelze vložit do batohu: " + e.getMessage();
                }
            }
        }

        return getNazev() + " si vzal " + vec.getNazev() + ".";
    }
}
