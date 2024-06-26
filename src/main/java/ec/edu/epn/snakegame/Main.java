package ec.edu.epn.snakegame;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class Main extends Application {
    static final Integer SNAKEBODY = 35, SCREENCANVASWIDTH = 800, CANVASHEIGHT = 570,
            SCREENHEIGHT = 600;

    LinkedList<int[]> snakeWay = new LinkedList<>();

    private final KeyDirections direction = KeyDirections.UP;

    @Override
    public void start(Stage gameScreen) throws IOException {

        Group gameScreenComponents = new Group();
        Scene gameScreenScene = new Scene(gameScreenComponents, SCREENCANVASWIDTH, SCREENHEIGHT);

        //Set stage to a specific size
        gameScreen.setScene(gameScreenScene);
        gameScreen.setResizable(false);
        gameScreen.setWidth(800);
        gameScreen.setHeight(600);
        gameScreen.setX(540);
        gameScreen.setY(240);
        gameScreen.show();

        //Set icon to app
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/SNAKE_GAME.png")));
        gameScreen.getIcons().add(icon);


        Canvas gameZone = new Canvas();
        GraphicsContext gc = gameZone.getGraphicsContext2D();

        gameZone.setWidth(SCREENCANVASWIDTH);
        gameZone.setHeight(CANVASHEIGHT);

        ImageView iv = new ImageView();
        iv.setImage(icon);
        iv.setX(0);
        iv.setY(0);
        iv.setFitHeight(50);
        iv.setFitWidth(50);
        gameScreenComponents.getChildren().add(iv);

        // Game zone with green yellow
        gc.setFill(Color.GREENYELLOW);
        gc.fillRect(0, 0, 800, 500);
        // X: posicion, Y: posicion, Xt: Tamanyo posicion, Yt: tamanyo posicion

        /**
         * todo: limits' game zone for height is 465 and width 750, only use snake with 35x35
         */

        //playing(); // Move snake
        // Check if snake has collided with limits or its own body
        drawSnake(gc); // // Draw the new one

        gameScreenComponents.getChildren().add(gameZone);

    }

    public void playing() {
    }

    public void drawSnake(GraphicsContext gc) {

    }


    public static void main(String[] args) {
        launch();
    }
}