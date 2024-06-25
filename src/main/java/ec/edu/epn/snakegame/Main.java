package ec.edu.epn.snakegame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class Main extends Application {
    static final Integer SNAKEBODY = 35, SCREENCANVASWIDTH = 800, CANVASHEIGHT = 570,
            SCREENHEIGHT = 600;

    LinkedList<int[]> snakeWay = new LinkedList<>();

    private KeyDirections direction = KeyDirections.UP;

    @Override
    public void start(Stage stage) throws IOException {


        Stage gameScreen = new Stage();
        Group gameScreenComponents = new Group();
        Scene gameScreenScene = new Scene(gameScreenComponents, SCREENCANVASWIDTH, SCREENHEIGHT);

        //Set icon to app
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/SNAKE.png")));
        stage.getIcons().add(icon);

        //Set stage to a specific size
        gameScreen.setScene(gameScreenScene);
        gameScreen.setResizable(false);
        gameScreen.setWidth(800);
        gameScreen.setHeight(600);
        gameScreen.setX(540);
        gameScreen.setY(240);
        gameScreen.show();

        Canvas gameZone = new Canvas();
        GraphicsContext gc = gameZone.getGraphicsContext2D();

        gameZone.setWidth(SCREENCANVASWIDTH);
        gameZone.setHeight(CANVASHEIGHT);

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

        /*
         *         gc.setFill(Color.RED);
         *         gc.fillRect(snakeWay.get(0)[0], snakeWay.get(0)[1], snakeBody, snakeBody);
         *         gc.setFill(Color.RED);
         *         gc.fillRect(snakeWay.get(1)[0], snakeWay.get(1)[1], snakeBody, snakeBody);
         *         gc.setFill(Color.RED);
         *         gc.fillRect(snakeWay.get(2)[0], snakeWay.get(2)[1], snakeBody, snakeBody);
         *         gc.setFill(Color.RED);
         *         gc.fillRect(snakeWay.get(3)[0], snakeWay.get(3)[1], snakeBody, snakeBody);
         */


        gameScreenComponents.getChildren().add(gameZone);

    }

    public void playing() {
        int[] head = snakeWay.getFirst();
        int newX = head[0];
        int newY = head[1];

        switch (direction) {
            case KeyDirections.UP -> newY--;
            case KeyDirections.DOWN -> newY++;
            case KeyDirections.LEFT -> newX--;
            case KeyDirections.RIGHT -> newX++;
        }
    }

    public void drawSnake(GraphicsContext gc) {
        /*
        snakeWay.add(new int[]{ 0, 0});
        snakeWay.add(new int[]{ snakeBody, 0});
        snakeWay.add(new int[]{ snakeBody * 2, 0});
        snakeWay.add(new int[]{ snakeBody * 3, 0});
         */


    }


    public static void main(String[] args) {
        launch();
    }
}