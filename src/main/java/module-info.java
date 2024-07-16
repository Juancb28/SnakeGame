module ec.edu.epn.snakegame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires gdx.controllers;
    requires Jamepad;
    

    opens ec.edu.epn.snakegame to javafx.fxml;

    exports ec.edu.epn.snakegame;
}