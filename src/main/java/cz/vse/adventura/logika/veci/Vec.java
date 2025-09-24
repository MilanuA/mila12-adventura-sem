package cz.vse.adventura.logika.veci;


/**
 *  Base třída pro věci
 *
 *@author     Alex Milanů
 *@version    pro školní rok 2024/2025
 *
 */
public class Vec
{
    protected String nazev;
    protected boolean prenositelna;
    protected Integer hmotnost;


    protected boolean jePouzitelna;

    public Vec(String nazev, boolean prenositelna) {
        this.nazev = nazev;
        this.prenositelna = prenositelna;

        if(prenositelna)
            this.hmotnost = 0;
        else
            this.hmotnost = null;
    }

    public Vec(String nazev,
               boolean prenositelna,
               Integer hmotnost,
               boolean jePouzitelna) {
        this.nazev = nazev;
        this.prenositelna = prenositelna;
        this.hmotnost = hmotnost;
        this.jePouzitelna = jePouzitelna;
    }

    public String getNazev() {
        return nazev;
    }

    public boolean isPrenositelna() {
        return prenositelna;
    }

    public boolean isJePouzitelna() {
        return jePouzitelna;
    }

    public Integer getHmotnost() {
        return hmotnost;
    }
}
