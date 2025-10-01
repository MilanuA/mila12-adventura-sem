module cz.vse.adventura {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires javafx.graphics;


    exports cz.vse.adventura.main;
    opens cz.vse.adventura.main to javafx.fxml;

    opens cz.vse.adventura.logika.jsonNacitani to com.fasterxml.jackson.databind;
    opens cz.vse.adventura.logika to com.fasterxml.jackson.databind;

    exports cz.vse.adventura.logika to com.fasterxml.jackson.databind;
}