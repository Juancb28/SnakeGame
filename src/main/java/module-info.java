module ec.edu.epn.snakegame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    requires javafx.graphics;
    requires java.desktop;
    requires javafx.media;
    requires java.sql;

    opens ec.edu.epn.snakegame to javafx.fxml;

    exports ec.edu.epn.snakegame;
}