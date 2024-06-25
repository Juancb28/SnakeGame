module ec.edu.epn.snakegame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens ec.edu.epn.snakegame to javafx.fxml;
    exports ec.edu.epn.snakegame;
}