package ec.edu.epn.snakegame;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;
import ec.edu.view.ScreenGameView;

public class Main extends Application {

    ScreenGameView sg = new ScreenGameView();
    private Image image;

    @Override
    public void start(@SuppressWarnings("exports") Stage gameScreen) throws IOException {

        Group initialScreenComponents = new Group();
        Scene intialScreenScene = new Scene(initialScreenComponents, sg.getScreenCanvasWidth(), sg.getScreenHeight(),
                Color.GREENYELLOW);

        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/SNAKE_GAME.png")));
        gameScreen.getIcons().add(image);
        gameScreen.setTitle("SnakeGame®");
        gameScreen.setResizable(false);
        gameScreen.setWidth(800);
        gameScreen.setHeight(600);
        gameScreen.centerOnScreen();
        gameScreen.show();
        sg.initialScreenGame(gameScreen, initialScreenComponents, intialScreenScene);
    }

    public static void main(String[] args) {
        launch();
    }
}