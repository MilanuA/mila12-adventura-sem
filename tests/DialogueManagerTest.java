
import cz.vse.adventura.logika.Postava;
import cz.vse.adventura.logika.dialogue.DialogueManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DialogueManagerTest {

    private DialogueManager dialogueManager;
    private Postava testPostava;

    @BeforeEach
    void setUp() {
        dialogueManager = DialogueManager.getInstance();
        testPostava = new Postava("TestovacíPostava",
                List.of("Ahoj!", "Jak se máš?", "To je vše."),
                List.of("Už jsme spolu mluvili."),
                null,
                null
        );
        dialogueManager.zacitDialog(testPostava);
    }

    @Test
    void testZacitDialog() {
        assertTrue(dialogueManager.getIsDialogueAktivni(), "Dialog by měl být aktivní po spuštění.");
    }

    @Test
    void testDalsiRadekVraciSpravnyText() {
        String prvni = dialogueManager.dalsiRadek();
        assertTrue(prvni.contains("Ahoj!"), "První řádek dialogu by měl obsahovat první větu postavy.");

        String druhy = dialogueManager.dalsiRadek();
        assertTrue(druhy.contains("Jak se máš?"), "Druhý řádek dialogu by měl být druhá věta.");

        String treti = dialogueManager.dalsiRadek();
        assertTrue(treti.contains("To je vše."), "Třetí řádek dialogu by měl být poslední věta.");

        String konec = dialogueManager.dalsiRadek();
        assertTrue(konec.contains("Rozhovor s postavou skončil"), "Po posledním řádku by měl být dialog ukončen.");
        assertFalse(dialogueManager.getIsDialogueAktivni(), "Dialog by měl být deaktivován po poslední větě.");
    }

    @Test
    void testZnovuZacitDialogPouzijePokracovani() {

        dialogueManager.dalsiRadek();
        dialogueManager.dalsiRadek();
        dialogueManager.dalsiRadek();
        dialogueManager.dalsiRadek();

        dialogueManager.zacitDialog(testPostava);

        String pokracovani = dialogueManager.dalsiRadek();

        assertTrue(pokracovani.contains("Už jsme spolu mluvili."),
                "Po prvním dialogu by měl další rozhovor použít pokračovací text.");
    }

}
