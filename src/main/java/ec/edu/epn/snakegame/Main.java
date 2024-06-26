package ec.edu.epn.snakegame;

import javafx.animation.KeyFrame;
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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.Timeline;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

public class Main extends Application {

    // static variables
    static final Integer SNAKEBODY = 20, SCREENCANVASWIDTH = 800, SCREENHEIGHT = 600;
    private Font font = new Font("Arial", 50);

    // Attributes
    private Image image;
    private ImageView ivApple;

    private LinkedList<int[]> snakeWay = new LinkedList<>();
    private KeyDirections direction = KeyDirections.RIGHT;
    private int[] food = new int[2];
    private Boolean running = true;

    @Override
    public void start(@SuppressWarnings("exports") Stage gameScreen) throws IOException {

        Group initialScreenComponents = new Group();
        Scene intialScreenScene = new Scene(initialScreenComponents, SCREENCANVASWIDTH, SCREENHEIGHT,
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

        initialScreenGame(gameScreen, initialScreenComponents, intialScreenScene);

    }

    private void initialScreenGame(Stage gameScreen, Group initialScreenComponents,
            Scene intialScreenScene) {
        Font gameFont = Font.loadFont(getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf"), 43);

        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/apple.png")));
        ivApple = new ImageView(image);
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
                gameScreenSnake(gameScreen);
            }
        });

        addInitialScreenComponents(initialScreenComponents, instructions, rectangle, rectangle1, rectangle2, rectangle3,
                rectangle6, rectangle5, rectangle4, rectangle7, text, text1);

    }

    private void addInitialScreenComponents(Group initialScreenComponents, Node... nodes) {
        initialScreenComponents.getChildren().addAll(nodes);
    }

    private void gameScreenSnake(Stage gameScreen) {
        Canvas gameZone = new Canvas(SCREENCANVASWIDTH, SCREENHEIGHT);
        GraphicsContext gc = gameZone.getGraphicsContext2D();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), e -> run(gc)));
        timeline.setCycleCount(Timeline.INDEFINITE);

        gameZone.relocate(0, 0);

        StackPane root = new StackPane(gameZone);
        Scene gameScreenScene = new Scene(root, SCREENCANVASWIDTH, SCREENHEIGHT);

        gameScreenScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP && direction != KeyDirections.DOWN)
                direction = KeyDirections.UP;
            else if (event.getCode() == KeyCode.DOWN && direction != KeyDirections.UP)
                direction = KeyDirections.DOWN;
            else if (event.getCode() == KeyCode.LEFT && direction != KeyDirections.RIGHT)
                direction = KeyDirections.LEFT;
            else if (event.getCode() == KeyCode.RIGHT && direction != KeyDirections.LEFT)
                direction = KeyDirections.RIGHT;
        });

        gameScreen.setScene(gameScreenScene);

        startGame();
        timeline.play();
    }

    private void startGame() {
        snakeWay.clear();
        snakeWay.add(new int[] { 22 / 2, 22 / 2 });
        placeFood();
    }

    private void run(GraphicsContext gc) {
        if (!running) {
            gc.setFill(Color.RED);
            gc.setFont(font);
            gc.fillText("Game Over", SCREENCANVASWIDTH / 2 - 100, SCREENHEIGHT / 2 - 50);
            return;
        }

        moveSnake();
        checkCollision();
        draw(gc);
    }

    private void moveSnake() {
        int[] head = snakeWay.getFirst();
        int newX = head[0];
        int newY = head[1];

        switch (direction) {
            case UP -> newY--;
            case DOWN -> newY++;
            case LEFT -> newX--;
            case RIGHT -> newX++;
        }

        int[] newHead = { newX, newY };
        snakeWay.addFirst(newHead);

        if (newHead[0] == food[0] && newHead[1] == food[1]) {
            placeFood();
        } else {
            snakeWay.removeLast();
        }
    }

    private void placeFood() {
        Random rand = new Random();
        food[0] = rand.nextInt(22);
        food[1] = rand.nextInt(22);
    }

    private void checkCollision() {
        int[] head = snakeWay.getFirst();
        if (head[0] < 0 || head[0] >= 41 || head[1] < 0 || head[1] >= 26) {
            running = false;
        }
        for (int i = 1; i < snakeWay.size(); i++) {
            if (head[0] == snakeWay.get(i)[0] && head[1] == snakeWay.get(i)[1]) {
                running = false;
            }
        }
    }

    private void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, SCREENCANVASWIDTH, SCREENHEIGHT);

        gc.setFill(Color.GREENYELLOW);
        gc.fillRect(0, 0, SCREENCANVASWIDTH, SCREENHEIGHT);

        gc.setFill(Color.LIGHTSKYBLUE);
        gc.setStroke(Color.BEIGE);
        for (int[] part : snakeWay) {
            gc.fillRect(part[0] * SNAKEBODY, part[1] * SNAKEBODY, SNAKEBODY, SNAKEBODY);
        }

        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/applePixel.png")));

        gc.drawImage(image, food[0] * SNAKEBODY, food[1] * SNAKEBODY, SNAKEBODY + 10, SNAKEBODY + 10);

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 520, 800, 80);
    }

    public static void main(String[] args) {
        launch();
    }
}