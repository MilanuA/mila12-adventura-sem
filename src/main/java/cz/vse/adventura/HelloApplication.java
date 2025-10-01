package cz.vse.adventura;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("scene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Semestrální úloha - 4IT115 - mila12");
        stage.setScene(scene);
        stage.show();
    }
}
