package ec.edu.epn.snakegame;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class Main extends Application {
    static final Integer SNAKEBODY = 35, SCREENCANVASWIDTH = 800, CANVASHEIGHT = 570,
            SCREENHEIGHT = 600;
    private Image image;

    LinkedList<int[]> snakeWay = new LinkedList<>();

    private final KeyDirections direction = KeyDirections.UP;

    @Override
    public void start(@SuppressWarnings("exports") Stage gameScreen) throws IOException {

        Group gameScreenComponents = new Group(), initialScreenComponents = new Group();
        Scene gameScreenScene = new Scene(gameScreenComponents, SCREENCANVASWIDTH, SCREENHEIGHT),
                intialScreenScene = new Scene(initialScreenComponents, SCREENCANVASWIDTH, SCREENHEIGHT,
                        Color.GREENYELLOW);

        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/SNAKE_GAME.png")));

        // Set icon to app
        gameScreen.getIcons().add(image);

        // Set name to app
        gameScreen.setTitle("SnakeGame®");

        // Set stage to a specific size
        gameScreen.setResizable(false);
        gameScreen.setWidth(800);
        gameScreen.setHeight(600);
        gameScreen.centerOnScreen();
        gameScreen.show();

        initialScreenGame(gameScreen, initialScreenComponents, gameScreenComponents, intialScreenScene,
                gameScreenScene);

    }

    private void initialScreenGame(Stage gameScreen, Group initialScreenComponents, Group gameScreenComponents,
            Scene intialScreenScene,
            Scene gameScreenScene) {
        Font gameFont = Font.loadFont(getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf"), 43);

        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/apple.png")));
        ImageView ivApple = new ImageView(image);
        ivApple.setLayoutX(10);
        ivApple.setLayoutY(10);
        ivApple.setX(5);
        ivApple.setY(5);

        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/SNAKE_GAME.png")));
        ImageView ivIcon = new ImageView(image);
        ivIcon.setFitHeight(100);
        ivIcon.setFitWidth(100);
        ivIcon.setX(17);
        ivIcon.setY(17);

        initialScreenComponents.getChildren().add(ivApple);
        initialScreenComponents.getChildren().add(ivIcon);

        gameScreen.setScene(intialScreenScene);

        Text text1 = new Text("SNAKE"), text = new Text("GAME"), playButton = new Text("---->PLAY<----");
        ;

        text1.setX(300);
        text1.setY(150);
        text1.setFont(gameFont);
        text1.setFill(Color.BLACK);

        text.setX(315);
        text.setY(225);
        text.setFont(gameFont);
        text.setFill(Color.BLACK);

        Rectangle rectangle = new Rectangle(), rectangle1 = new Rectangle(), rectangle2 = new Rectangle(),
                rectangle3 = new Rectangle(),
                rectangle4 = new Rectangle(), rectangle5 = new Rectangle(), rectangle6 = new Rectangle(),
                rectangle7 = new Rectangle();

        rectangle.setX(0);
        rectangle.setY(570);
        rectangle.setWidth(800);
        rectangle.setHeight(30);
        rectangle.setFill(Color.WHITE);

        rectangle1.setX(310);
        rectangle1.setY(49);
        rectangle1.setWidth(250);
        rectangle1.setHeight(25);
        rectangle1.setFill(Color.LIGHTSKYBLUE);

        rectangle2.setX(535);
        rectangle2.setY(49);
        rectangle2.setWidth(25);
        rectangle2.setHeight(230);
        rectangle2.setFill(Color.LIGHTSKYBLUE);

        rectangle3.setX(215);
        rectangle3.setY(279);
        rectangle3.setWidth(345);
        rectangle3.setHeight(25);
        rectangle3.setFill(Color.LIGHTSKYBLUE);

        rectangle4.setX(215);
        rectangle4.setY(279);
        rectangle4.setWidth(25);
        rectangle4.setHeight(125);
        rectangle4.setFill(Color.LIGHTSKYBLUE);

        rectangle5.setX(215);
        rectangle5.setY(404);
        rectangle5.setWidth(300);
        rectangle5.setHeight(25);
        rectangle5.setFill(Color.LIGHTSKYBLUE);

        rectangle6.setX(509);
        rectangle6.setY(411);
        rectangle6.setWidth(10);
        rectangle6.setHeight(10);
        rectangle6.setFill(Color.BLACK);

        rectangle7.setX(570);
        rectangle7.setY(404);
        rectangle7.setWidth(25);
        rectangle7.setHeight(25);
        rectangle7.setFill(Color.BROWN);

        playButton.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf"), 17));
        playButton.setFill(Color.BLACK);
        playButton.setStrokeWidth(1);
        playButton.setX(280);
        playButton.setY(365);
        initialScreenComponents.getChildren().add(playButton);

        Label instructions = new Label("Presione Enter para Jugar");
        instructions.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf"), 15));
        instructions.setTextFill(Color.BLACK);
        instructions.setLayoutX(200);
        instructions.setLayoutY(450);
        intialScreenScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                gameScreenSnake(gameScreen, gameScreenComponents, gameScreenScene);
            }
        });

        addScreenComponents(initialScreenComponents, instructions, rectangle, rectangle1, rectangle2, rectangle3,
                rectangle6, rectangle5, rectangle4, rectangle7, text, text1);

    }

    private void addScreenComponents(Group initialScreenComponents, Node... nodes) {
        initialScreenComponents.getChildren().addAll(nodes);
    }

    private void gameScreenSnake(Stage gameScreen, Group gameScreenComponents, Scene gameScreenScene) {
        gameScreen.setScene(gameScreenScene);
        Canvas gameZone = new Canvas();
        GraphicsContext gc = gameZone.getGraphicsContext2D();
        // Canvas
        gameZone.setWidth(SCREENCANVASWIDTH);
        gameZone.setHeight(CANVASHEIGHT);

        // Game zone with green yellow
        gc.setFill(Color.GREENYELLOW);
        gc.fillRect(0, 0, 800, 500);
        // X: posicion, Y: posicion, Xt: Tamanyo posicion, Yt: tamanyo posicion

        /**
         * todo: limits' game zone for height is 465 and width 750, only use snake with
         * 35x35
         */

        // playing(); // Move snake
        // Check if snake has collided with limits or its own body
        // drawSnake(gc); // // Draw the new one

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