module ec.edu.epn.snakegame {
    requires javafx.controls;
    requires javafx.fxml;
<<<<<<< HEAD
    requires java.logging;

=======
    requires javafx.graphics;
    requires java.desktop;
    requires javafx.media;
    requires java.sql;
>>>>>>> 2e95ee028249eab5512d55d6502842aace48c516

    opens ec.edu.epn.snakegame to javafx.fxml;

    exports ec.edu.epn.snakegame;
}