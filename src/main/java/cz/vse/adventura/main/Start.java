/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package cz.vse.adventura.main;
import cz.vse.adventura.logika.Hra;
import cz.vse.adventura.uiText.TextoveRozhrani;
import cz.vse.adventura.utils.Barvy;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*******************************************************************************
 * Třída  Start je hlavní třídou projektu
 */
public class Start extends Application
{
    /*
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args)
    {
        if(args.length > 0 && args[0].equals("text")){
            Hra hra = new Hra();
            TextoveRozhrani ui = new TextoveRozhrani(hra);
            ui.hraj();

            Platform.exit();
        }
        else{
            Barvy.povolitAnsi(false);
            launch();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("scene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Semestrální úloha - 4IT115 - mila12");
        stage.setScene(scene);
        stage.show();
    }
}
