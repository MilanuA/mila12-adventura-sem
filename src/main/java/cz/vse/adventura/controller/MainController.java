package cz.vse.adventura.controller;

import cz.vse.adventura.logika.Hra;
import cz.vse.adventura.logika.IHra;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class MainController {

    @FXML
    private TextArea output;
    @FXML
    private TextField inputField;

    @FXML
    private MenuItem restartGameMenuItem;
    @FXML
    private Button sendBtn;

    private IHra hra = new Hra();

    @FXML
    public void initialize() {
        restartGameMenuItem.setOnAction(e -> restartGame());

        output.appendText(hra.vratUvitani() + "\n\n");
        Platform.runLater(() -> inputField.requestFocus());
    }

    @FXML
    private void sendInput(ActionEvent actionEvent) {
        String prikaz = inputField.getText();

        output.appendText("> " + prikaz + "\n");

        String vysledek = hra.zpracujPrikaz(prikaz);

        output.appendText(vysledek + "\n\n");

        inputField.clear();

        if(!hra.konecHry()) return;

        output.appendText(hra.vratEpilog());
        inputField.clear();
        inputField.setDisable(true);
        sendBtn.setDisable(true);
    }

    @FXML
    private void showHelp() {
        Stage helpStage = new Stage();
        helpStage.setTitle("Nápověda");

        WebView webView = new WebView();

        String url = Objects.requireNonNull(getClass().getResource("/cz/vse/adventura/main/help.html")).toExternalForm();

        webView.getEngine().load(url);

        helpStage.setScene(new Scene(webView, 600, 400));
        helpStage.show();
    }

    private void restartGame() {
        try {
            Stage stage = (Stage) restartGameMenuItem.getParentPopup().getOwnerWindow();

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/cz/vse/adventura/main/scene.fxml")
            );
            Scene newScene = new Scene(loader.load(), 600, 400);

            stage.setScene(newScene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void quitGame(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Vážně si přejete ukončit hru?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }

}
