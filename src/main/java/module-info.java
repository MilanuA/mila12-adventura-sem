module cz.vse.adventura {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.web;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;


    opens cz.vse.adventura.main to javafx.fxml;
    opens cz.vse.adventura.logika.jsonNacitani to com.fasterxml.jackson.databind;
    opens cz.vse.adventura.logika to com.fasterxml.jackson.databind;
    opens cz.vse.adventura.controller to javafx.fxml;

    exports cz.vse.adventura.main;
    exports cz.vse.adventura.logika to com.fasterxml.jackson.databind;
}
