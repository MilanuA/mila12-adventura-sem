package cz.vse.adventura.logika.dialogue;

import cz.vse.adventura.logika.Postava;
import cz.vse.adventura.utils.Barvy;

/**
 * Singleton třída, která spravuje probíhající dialog mezi hráčem a postavou
 *
 * @author Alex Milanů
 * @version pro školní rok 2024/2025
 */
public class DialogueManager
{
    private static DialogueManager instance;

    private boolean isDialogueAktivni;
    private int radek;
    private Postava mluviciPostava;

    private DialogueManager() {
        isDialogueAktivni = false;
    }

    /**
     * Vrací jedinou instanci třídy DialogueManager
     * @return instance DialogueManager
     */
    public static synchronized DialogueManager getInstance() {
        if (instance == null)
        {
            instance = new DialogueManager();
        }

        return instance;
    }

    /**
     * Zahájí dialog s danou postavou
     * @param postava Postava, se kterou se vede rozhovor
     */
    public void zacitDialog(Postava postava){
        isDialogueAktivni = true;
        mluviciPostava = postava;
        radek = -1;
    }

    /**
     * Vrací informaci, zda právě probíhá nějaký dialog
     * @return true pokud je aktivní dialog, jinak false
     */
    public boolean getIsDialogueAktivni() {
        return isDialogueAktivni;
    }

    /**
     * Vrací další řádek dialogu aktuální postavy
     * Pokud dialog skončí, zobrazí zprávu a deaktivuje dialog
     * @return formátovaný text dalšího dialogového řádku nebo konečnou zprávu
     */
    public String dalsiRadek() {
        radek++;
        String dialog = mluviciPostava.getDalsiRadek(radek);

        if (dialog == null) {
            ZastavitDialog();
            mluviciPostava.setPromluvilSPostavou(true);
            return "Rozhovor s postavou skončil. " +  Barvy.ITALIC + "(nyní se můžeš znovu hýbat)" + Barvy.RESET;
        }

        return "[" + mluviciPostava.getNazev() +  "]: " + dialog + Barvy.ITALIC +  " (použij příkaz další)" + Barvy.RESET;
    }

    /**
     * Ukončí aktuální dialog
     */
    private void ZastavitDialog(){
        isDialogueAktivni = false;
    }
}

