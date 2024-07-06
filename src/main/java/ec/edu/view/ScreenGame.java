package ec.edu.view;

import java.util.LinkedList;
import java.util.Objects;
// import java.util.Random;
import ec.edu.KeyDirections;
import ec.edu.edibleitems.classes.Apple;
import ec.edu.player.PlayerGame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.Chronometer;

public class ScreenGame {

    // private int[] food = new int[2];
    private Chronometer chronometer;
    private Boolean running = true;
    private Image image;
    private ImageView ivApple;
    private final Integer SNAKEBODY = 20, SCREENCANVASWIDTH = 800, SCREENHEIGHT = 600;
    private Font font = new Font("Arial", 50);
    private KeyDirections direction = KeyDirections.RIGHT;
    private LinkedList<int[]> snakeWay = new LinkedList<>();
    private int level;
    private int appleEaten;
    private int score;
    private int aux;
    private int pressedTimes;
    private int snakeVelocity;
    private Timeline timeline;
    private String name;
    private PlayerGame player;
    private Apple apple = new Apple();;

    private Color[] colorHeadSnake = new Color[] {
            Color.web("#228B22"), // Verde Bosque
            Color.web("#32CD32"), // Verde Lima
            Color.web("#008000"), // Verde
            Color.web("#3CB371"), // Verde Medio Mar
            Color.web("#2E8B57"), // Verde Marino Medio
            Color.web("#006400"), // Verde Oscuro
            Color.web("#90EE90"), // Verde Claro
            Color.web("#00FF7F"), // Verde Primavera
            Color.web("#7FFF00"), // Verde Amarillo
            Color.web("#00FA9A") // Verde Medio
    };

    private Color[] colorsBackground = new Color[] {
            Color.web("#F0F8FF"), // Azul Alice
            Color.web("#F5F5F5"), // Blanco Fantasma
            Color.web("#E6E6FA"), // Lavanda
            Color.web("#FFFAF0"), // Flor Blanca
            Color.web("#F0FFF0"), // Verde Menta
            Color.web("#FFF0F5"), // Rosa Lavanda
            Color.web("#FAF0E6"), // Lino
            Color.web("#F5FFFA"), // Menta Crema
            Color.web("#FFF5EE"), // Melón
            Color.web("#FFF8DC") // Maíz Antiguo
    };

    private Color[] colorSnake = new Color[] {
            Color.web("#6B8E23"), // Verde Oliva Oscuro
            Color.web("#556B2F"), // Verde Oscuro
            Color.web("#8FBC8F"), // Verde Claro
            Color.web("#228B22"), // Verde Bosque
            Color.web("#32CD32"), // Verde Lima
            Color.web("#008000"), // Verde
            Color.web("#3CB371"), // Verde Medio Mar
            Color.web("#2E8B57"), // Verde Marino Medio
            Color.web("#006400"), // Verde Oscuro
            Color.web("#90EE90") // Verde Claro
    };

    public ScreenGame() {
        setLevel(1);
        setAppleEaten(0);
        setAux(0);
        setPressedTimes(0);
        setScore(0);
        setSnakeVelocity(200);
        player = new PlayerGame(null, getScore());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSnakeVelocity() {
        return snakeVelocity;
    }

    public void setSnakeVelocity(int snakeVelocity) {
        if (snakeVelocity > 0) {
            this.snakeVelocity = snakeVelocity;
        }
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPressedTimes() {
        return this.pressedTimes;
    }

    public void setPressedTimes(int pressedTimes) {
        this.pressedTimes = pressedTimes;
    }

    public int getAux() {
        return this.aux;
    }

    public void setAux(int aux) {
        this.aux = aux;
    }

    public int getAppleEaten() {
        return this.appleEaten;
    }

    public void setAppleEaten(int appleEaten) {
        this.appleEaten = appleEaten;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScreenCanvasWidth() {
        return SCREENCANVASWIDTH;
    }

    public int getScreenHeight() {
        return SCREENHEIGHT;
    }

    public void initialScreenGame(Stage gameScreen, Group initialScreenComponents,
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
                // gameScreenSnake(gameScreen);
                menu(gameScreen);
            }
        });

        addInitialScreenComponents(initialScreenComponents, instructions, rectangle, rectangle1, rectangle2, rectangle3,
                rectangle6, rectangle5, rectangle4, rectangle7, text, text1);

    }

    private void addInitialScreenComponents(Group initialScreenComponents, Node... nodes) {
        initialScreenComponents.getChildren().addAll(nodes);
    }

    public void menu(Stage gameScreen) {
        Font gameFont = Font.loadFont(getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf"), 25);
        Group root = new Group();
        Scene menu = new Scene(root, SCREENCANVASWIDTH, SCREENHEIGHT, Color.GREENYELLOW);
        gameScreen.setScene(menu);
        Rectangle rectangle = new Rectangle(), rectangle1 = new Rectangle(), rectangle2 = new Rectangle(),
                rectangle3 = new Rectangle();

        Button newGameButton = new Button(), newBestScoreButton = new Button(), newExitGameButton = new Button();
        Text text1 = new Text("Menu");
        text1.setX(350);
        text1.setY(100);
        text1.setFont(gameFont);
        text1.setFill(Color.BLACK);

        rectangle.setX(100);
        rectangle.setY(25);
        rectangle.setWidth(600);
        rectangle.setHeight(30);
        rectangle.setFill(Color.TOMATO);

        rectangle1.setX(100);
        rectangle1.setY(25);
        rectangle1.setWidth(30);
        rectangle1.setHeight(375);
        rectangle1.setFill(Color.TOMATO);

        rectangle2.setX(100);
        rectangle2.setY(395);
        rectangle2.setWidth(600);
        rectangle2.setHeight(150);
        rectangle2.setFill(Color.TOMATO);

        rectangle3.setX(670);
        rectangle3.setY(25);
        rectangle3.setWidth(30);
        rectangle3.setHeight(375);
        rectangle3.setFill(Color.TOMATO);

        font = new Font(20);
        BackgroundFill backgroundFill = new BackgroundFill(Color.TOMATO, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        newGameButton.setLayoutX(250);
        newGameButton.setLayoutY(125);

        newGameButton.setMinWidth(300);
        newGameButton.setMinHeight(40);
        newGameButton.setFont(gameFont);
        newGameButton.setText("New Game");
        newGameButton.setBackground(background);
        newGameButton.setOnAction(event -> enterName(gameScreen));

        newBestScoreButton.setLayoutX(250);
        newBestScoreButton.setLayoutY(200);

        newBestScoreButton.setMinWidth(300);
        newBestScoreButton.setMinHeight(40);
        newBestScoreButton.setFont(gameFont);
        newBestScoreButton.setText("Best Score");
        newBestScoreButton.setBackground(background);
        newBestScoreButton.setOnAction(event -> BestScore());

        newExitGameButton.setLayoutX(250);
        newExitGameButton.setLayoutY(275);
        newExitGameButton.setMinWidth(300);
        newExitGameButton.setMinHeight(40);
        newExitGameButton.setFont(gameFont);
        newExitGameButton.setText("Exit Game");
        newExitGameButton.setBackground(background);
        newExitGameButton.setOnAction(event -> exitGam());

        ImageView imageMenu = new ImageView(new Image(apple.showFruit())),
                imageMenu2 = new ImageView(new Image(apple.showFruit()));
        imageMenu.setFitHeight(75);
        imageMenu.setFitWidth(75);
        imageMenu.setX(280);
        imageMenu.setY(47);

        imageMenu2.setFitHeight(75);
        imageMenu2.setFitWidth(75);
        imageMenu2.setX(440);
        imageMenu2.setY(47);

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), imageMenu2);
        translateTransition.setFromX(0); // pos inicial
        translateTransition.setToX(25); // pos final
        translateTransition.setFromY(0); // pos inicial y
        translateTransition.setToY(0); // pos final u
        translateTransition.setCycleCount(TranslateTransition.INDEFINITE); // ciclo repetivo
        translateTransition.setAutoReverse(true);

        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(1), imageMenu);
        translateTransition1.setFromX(0);
        translateTransition1.setToX(-25);
        translateTransition1.setFromY(0);
        translateTransition1.setToY(0);
        translateTransition1.setCycleCount(TranslateTransition.INDEFINITE);
        translateTransition1.setAutoReverse(true);

        translateTransition.play();
        translateTransition1.play();

        root.getChildren().add(imageMenu2);
        gameScreen.setScene(menu);
        gameScreen.show();

        root.getChildren().add(imageMenu);
        root.getChildren().add(newGameButton);
        root.getChildren().add(newExitGameButton);
        root.getChildren().add(newBestScoreButton);
        root.getChildren().add(text1);
        addInitialScreenComponents(root, rectangle, rectangle1, rectangle2, rectangle3);

        // newGameButton.setEffect(new ColorInput(75, 85, 250, 40, Color.RED));
    }

    private void exitGam() {
        System.exit(0);
    }

    private void BestScore() {
        System.out.println("base de datos ");
    }

    private String enterName(Stage gameScreen) {
        Stage enterName = new Stage();
        enterName.getIcons().add(gameScreen.getIcons().getFirst());
        Group root = new Group();
        Scene scene = new Scene(root, 450, 150, Color.GREENYELLOW);
        Font gameFont = Font.loadFont(getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf"), 15);

        font = new Font(5);
        enterName.setResizable(false);
        enterName.show();
        enterName.setTitle("Logging");
        enterName.setScene(scene);
        enterName.centerOnScreen();

        Text text = new Text();
        text.setX(115);
        text.setY(25);
        text.setFont(gameFont);
        text.setText("Enter your name");

        TextField name = new TextField();
        name.setPromptText("Enter your name");
        name.setScaleX(1.8);
        name.setLayoutX(150);
        name.setLayoutY(40);
        name.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                player.setNamePlayer(name.getText());
                System.out.println("Entered Name: " + player.getNamePlayer());
                if (!name.getText().isEmpty() && !name.getText().isBlank()) {
                    enterName.close();
                    gameScreenSnake(gameScreen);
                }
            }
        });

        Button anonymous = new Button();
        anonymous.setLayoutX(100);
        anonymous.setLayoutY(80);
        anonymous.setMinWidth(150);
        anonymous.setMinHeight(40);
        anonymous.setFont(gameFont);
        anonymous.setText("Play anonymous");
        anonymous.setOnAction(event -> {
            player.setNamePlayer("anonymous");
            enterName.close();
            gameScreenSnake(gameScreen);
        });

        root.getChildren().add(anonymous);
        root.getChildren().add(name);
        root.getChildren().add(text);
        return getName();

    }

    private void gameScreenSnake(Stage gameScreen) {
        Canvas gameZone = new Canvas(SCREENCANVASWIDTH, SCREENHEIGHT);
        GraphicsContext gc = gameZone.getGraphicsContext2D();
        timeline = new Timeline(new KeyFrame(Duration.millis(getSnakeVelocity()), e -> run(gc, timeline)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        gameZone.relocate(0, 0);

        StackPane root = new StackPane(gameZone);
        Scene gameScreenScene = new Scene(root, SCREENCANVASWIDTH, SCREENHEIGHT);

        gameScreenScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP && direction != KeyDirections.DOWN) {
                direction = KeyDirections.UP;
                setPressedTimes(getPressedTimes() + 1);
            } else if (event.getCode() == KeyCode.DOWN && direction != KeyDirections.UP) {
                direction = KeyDirections.DOWN;
                setPressedTimes(getPressedTimes() + 1);
            } else if (event.getCode() == KeyCode.LEFT && direction != KeyDirections.RIGHT) {
                direction = KeyDirections.LEFT;
                setPressedTimes(getPressedTimes() + 1);
            } else if (event.getCode() == KeyCode.RIGHT && direction != KeyDirections.LEFT) {
                direction = KeyDirections.RIGHT;
                setPressedTimes(getPressedTimes() + 1);
            }
        });

        gameScreen.setScene(gameScreenScene);

        startGame();
        timeline.play();
    }

    private void startGame() {
        snakeWay.clear();
        snakeWay.add(new int[] { 22 / 2, 22 / 2 });
        snakeWay.add(new int[] { 22 / 2, (22 / 2) - 1 });
        apple.placeFood();
        chronometer = new Chronometer();
        chronometer.initChronometer();
    }

    private void run(GraphicsContext gc, Timeline timeline) {
        if (!running) {
            gc.setFill(Color.RED);
            gc.setFont(font);
            gc.fillText("Game Over", SCREENCANVASWIDTH / 2 - 100, SCREENHEIGHT / 2 - 50);
            return;
        }

        moveSnake(gc, timeline);
        checkCollision();
        draw(gc);
    }

    private void moveSnake(GraphicsContext gc, Timeline timeline) {
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

        if (newHead[0] == apple.getFood()[0] && newHead[1] == apple.getFood()[1]) {
            apple.placeFood();
            // todo: Al comer
            setAppleEaten(getAppleEaten() + 1);
            if (getAppleEaten() % 3 == 0) {
                setLevel(getLevel() + 1);
                if (getLevel() % 5 == 0 && getAux() < 10) {
                    setAux(getAux() + 1);
                    setSnakeVelocity(getSnakeVelocity() - 10);
                    increaseSpeed(gc, timeline, getSnakeVelocity());
                }
            }
            player.calculateScore(getPressedTimes());
            setScore(player.getScore());
            setPressedTimes(0); // Snake already ate apple, so reset value into 0
        } else {
            snakeWay.removeLast();
        }
    }

    public void increaseSpeed(GraphicsContext gc, Timeline timeline, double newSpeedMillis) {
        timeline.stop();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(newSpeedMillis), e -> run(gc, timeline));
        timeline.getKeyFrames().setAll(keyFrame);
        timeline.play();
    }

    private void checkCollision() {
        int[] head = snakeWay.getFirst();
        if (head[0] <= 0 || head[0] >= 39 || head[1] <= 0 || head[1] >= 26) {
            running = false;
        }
        for (int i = 1; i < snakeWay.size(); i++) {
            if (head[0] == snakeWay.get(i)[0] && head[1] == snakeWay.get(i)[1]) {
                running = false;
            }
        }

        if (!running)
            chronometer.stopChronometer();
    }

    private void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, SCREENCANVASWIDTH, SCREENHEIGHT);

        gc.setFill(colorsBackground[getAux()]);
        gc.fillRect(20, 0, SCREENCANVASWIDTH - 40, SCREENHEIGHT - 80);

        gc.drawImage(new Image(apple.showFruit()), apple.getFood()[0] * SNAKEBODY, apple.getFood()[1] * SNAKEBODY,
                SNAKEBODY + 7, SNAKEBODY + 7);

        gc.setFill(colorSnake[getAux()]);
        for (int[] part : snakeWay) {
            gc.fillRect(part[0] * SNAKEBODY, part[1] * SNAKEBODY, SNAKEBODY, SNAKEBODY);
        }

        gc.setFill(colorHeadSnake[getAux()]);
        gc.fillRect(snakeWay.get(0)[0] * SNAKEBODY, snakeWay.get(0)[1] * SNAKEBODY, SNAKEBODY, SNAKEBODY);

        gc.setFill(Color.BROWN);
        gc.fillRect(0, 0, 20, SCREENHEIGHT - 80);

        gc.setFill(Color.BROWN);
        gc.fillRect(780, 0, 20, SCREENHEIGHT - 80);

        gc.setFill(Color.BROWN);
        gc.fillRect(0, 520, SCREENCANVASWIDTH, 20);

        gc.setFill(Color.WHEAT);
        gc.fillRect(0, 540, SCREENCANVASWIDTH, 60);

        font = new Font(35);
        gc.setFill(Color.BLACK);
        gc.setFont(font);
        gc.fillText(chronometer.getChronometer(), SCREENCANVASWIDTH - 200, SCREENHEIGHT - 30);

        gc.setFill(Color.BLACK);
        gc.setFont(font);
        gc.fillText("LEVEL " + getLevel(), 10, SCREENHEIGHT - 30);

        gc.setFill(Color.BLACK);
        gc.setFont(font);
        gc.fillText("SCORE " + player.getScore(), 150, SCREENHEIGHT - 30);

        gc.setFill(Color.BLACK);
        gc.setFont(font);
        gc.fillText("PLAYER " + player.getNamePlayer(), 340, SCREENHEIGHT - 30);

    }

}
