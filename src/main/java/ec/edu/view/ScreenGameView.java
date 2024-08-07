package ec.edu.view;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import ec.edu.KeyDirections;
import ec.edu.BusinessLogic.HighScoresBL;
import ec.edu.DataAcces.DTO.HighScoresDTO;
import ec.edu.edibleitems.classes.Apple;
import ec.edu.edibleitems.classes.Orange;
import ec.edu.edibleitems.classes.RottenApple;
import ec.edu.edibleitems.classes.BronzeMedal;
import ec.edu.edibleitems.classes.GoldMedal;
import ec.edu.edibleitems.classes.SilverMedal;
import ec.edu.player.MusicPlayer;
import ec.edu.player.PlayerGame;
import javafx.animation.FadeTransition;
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

/**
 * Shows initial, menu and game screen. Also check rules,
 * create userInterface.
 */
public class ScreenGameView {

    // Attributes
    private HighScoresBL hBl;
    private Chronometer chronometer;
    private Boolean running = true;
    private Boolean isPaused = false;
    private Image image;
    private ImageView ivApple;
    private final Integer SNAKEBODY = 20, SCREENCANVASWIDTH = 800, SCREENHEIGHT = 600;
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
    @SuppressWarnings("unused")
    private Font gameFont = Font.loadFont(getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf"), 25), font;
    private Apple apple = new Apple();
    private RottenApple rottenApple = new RottenApple();
    private Orange orange = new Orange();
    private GoldMedal goldMedal = new GoldMedal();
    private SilverMedal silverMedal = new SilverMedal();
    private BronzeMedal bronzeMedal = new BronzeMedal();
    private MusicPlayer mp;
    private HighScoresDTO playerDTO;
    private boolean isMusicPlaying;

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

    // Constructor
    public ScreenGameView() {
        setSettingsToGame();
        player = new PlayerGame(null, getScore());
        mp = new MusicPlayer();
        setMusicPlaying(false);
    }

    // Getters & Setters
    public boolean isMusicPlaying() {
        return isMusicPlaying;
    }

    public void setMusicPlaying(boolean isMusicPlaying) {
        this.isMusicPlaying = isMusicPlaying;
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
        if (snakeVelocity > 80) {
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

    public Boolean getRunning() {
        return running;
    }

    public void setRunning(Boolean running) {
        this.running = running;
    }

    public PlayerGame getPlayer() {
        return player;
    }

    public void setPlayer(PlayerGame player) {
        this.player = player;
    }
/**
This method sets the font, loads and displays images, 
creates and configures graphical components such as text and rectangles, 
and defines the behavior of keyboard events to initiate.
 * @param gameScreen
 * @param initialScreenComponents
 * @param intialScreenScene
 */
    public void initialScreenGame(Stage gameScreen, Group initialScreenComponents,
            Scene intialScreenScene) {

        gameFont = Font.loadFont(getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf"), 43);

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

        Label instructions = new Label("Press 'X' to play");
        instructions.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf"), 15));
        instructions.setTextFill(Color.BLACK);
        instructions.setLayoutX(200);
        instructions.setLayoutY(450);
        intialScreenScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                menu(gameScreen);
            }
        });

        addInitialScreenComponents(initialScreenComponents, instructions, rectangle, rectangle1, rectangle2, rectangle3,
                rectangle6, rectangle5, rectangle4, rectangle7, text, text1);

    }

    private void addInitialScreenComponents(Group initialScreenComponents, Node... nodes) {
        initialScreenComponents.getChildren().addAll(nodes);
    }
/**
 * This method sets the main menu scene on the provided {@link Stage},
 * including the graphical interface settings, navigation buttons, and background music.
 * Plays background music if it is not already playing.
 * Creates and configures the menu's visual elements, such as buttons and text.
 * Configures animations for certain graphical elements in the menu.
 * @param gameScreen
 */
    private void menu(Stage gameScreen) {

        if (!isMusicPlaying) {
            mp = new MusicPlayer();
            mp.initialize(null, null);
            mp.playMedia();
            setMusicPlaying(true);
        }

        gameFont = Font.loadFont(getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf"), 25);
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
        newBestScoreButton.setOnAction(event -> {
            try {
                BestScore(gameScreen);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        newExitGameButton.setLayoutX(250);
        newExitGameButton.setLayoutY(275);
        newExitGameButton.setMinWidth(300);
        newExitGameButton.setMinHeight(40);
        newExitGameButton.setFont(gameFont);
        newExitGameButton.setText("Exit Game");
        newExitGameButton.setBackground(background);
        newExitGameButton.setOnAction(event -> exitGam());

        ImageView imageMenu = new ImageView(new Image(apple.getPathImage())),
                imageMenu2 = new ImageView(new Image(apple.getPathImage()));
        imageMenu.setFitHeight(75);
        imageMenu.setFitWidth(75);
        imageMenu.setX(280);
        imageMenu.setY(47);

        imageMenu2.setFitHeight(75);
        imageMenu2.setFitWidth(75);
        imageMenu2.setX(440);
        imageMenu2.setY(47);

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), imageMenu2),
                translateTransition1 = new TranslateTransition(Duration.seconds(1), imageMenu);
        translateTransition.setFromX(0); // pos inicial
        translateTransition.setToX(25); // pos final
        translateTransition.setFromY(0); // pos inicial y
        translateTransition.setToY(0); // pos final u
        translateTransition.setCycleCount(TranslateTransition.INDEFINITE); // ciclo repetivo
        translateTransition.setAutoReverse(true);

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

        root.getChildren().addAll(imageMenu,newGameButton,newExitGameButton,newBestScoreButton,text1);
        addInitialScreenComponents(root, rectangle, rectangle1, rectangle2, rectangle3);

    }
/**
 * Close the application immediately
 */
    private void exitGam() {
        System.exit(0);
    }
    /**
     * Display the high scores screen. This method sets up and displays 
     * a scene that presents the high scores in the game.
     * It includes displaying the high scores along with animated medals for the top three places.
     * Loads and sorts the high scores from a data source.
     * Creates and configures graphical elements such as text and animated medals.
     * Adds player scores and names to the scene, limiting the display to the top five. 
     * Sets an action to return to the main menu when the Enter key is pressed.
     * @param gameScreen
     * @throws Exception
     */

    private void BestScore(Stage gameScreen) throws Exception {
        HighScoresBL hScoresBL = new HighScoresBL();
        int aux = 80;
        Group root = new Group();
        Scene scene = new Scene(root, 450, 150, Color.GREENYELLOW);
        gameScreen.setScene(scene);
        gameFont = Font.loadFont(getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf"), 20);
        Text text = new Text();
        text.setX(300);
        text.setY(35);
        text.setFont(gameFont);
        text.setText("Best Scores");
        text.setFill(Color.TOMATO);

        Text text2 = new Text();
        text2.setX(80);
        text2.setY(100);
        text2.setFont(gameFont);
        text2.setText("Name");
        text2.setFill(Color.TOMATO);

        Text text3 = new Text();
        text3.setX(260);
        text3.setY(100);
        text3.setFont(gameFont);
        text3.setText("Score");
        text3.setFill(Color.TOMATO);
        Text text4 = new Text();
        text4.setX(430);
        text4.setY(100);
        text4.setFont(gameFont);
        text4.setText("Survival Time");
        text4.setFill(Color.TOMATO);

        ImageView img = new ImageView(new Image(goldMedal.getPathImage()));
        img.setFitHeight(30);
        img.setFitWidth(30);
        img.setX(50);
        img.setY(115);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), img);
        fadeTransition.setFromValue(1.0); // Opacidad inicial (completamente visible)
        fadeTransition.setToValue(0.0); // Opacidad final (completamente invisible)
        fadeTransition.setCycleCount(FadeTransition.INDEFINITE); // Repetir indefinidamente
        fadeTransition.setAutoReverse(true); // Invertir la animación (desaparecer y luego aparecer)
        fadeTransition.play();

        ImageView imge2 = new ImageView(new Image(silverMedal.getPathImage()));
        imge2.setFitHeight(35);
        imge2.setFitWidth(38);
        imge2.setX(45);
        imge2.setY(155);
        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(2), imge2);
        fadeTransition1.setFromValue(1.0); // Opacidad inicial (completamente visible)
        fadeTransition1.setToValue(0.0); // Opacidad final (completamente invisible)
        fadeTransition1.setCycleCount(FadeTransition.INDEFINITE); // Repetir indefinidamente
        fadeTransition1.setAutoReverse(true); // Invertir la animación (desaparecer y luego aparecer)
        fadeTransition1.play();

        ImageView img3 = new ImageView(new Image(bronzeMedal.getPathImage()));
        img3.setFitHeight(30);
        img3.setFitWidth(30);
        img3.setX(50);
        img3.setY(190);
        FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(2), img3);
        fadeTransition2.setFromValue(1.0); // Opacidad inicial (completamente visible)
        fadeTransition2.setToValue(0.0); // Opacidad final (completamente invisible)
        fadeTransition2.setCycleCount(FadeTransition.INDEFINITE); // Repetir indefinidamente
        fadeTransition2.setAutoReverse(true); // Invertir la animación (desaparecer y luego aparecer)
        fadeTransition2.play();
        root.getChildren().addAll(text, text2, text3, text4, img, img3, imge2);

        for (int i = 0; i < (hScoresBL.getAll().size() < 5 ? hScoresBL.getAll().size() : 5); i++) {
            Text text1 = new Text();
            text1.setX(80);
            text1.setY(aux + 55);
            text1.setFont(gameFont);
            text1.setText(hScoresBL.getAll().get(i).getPlayer_name());
            text1.setFill(Color.BLACK);
            Text text5 = new Text();
            text5.setX(260);
            text5.setY(aux + 55);
            text5.setFont(gameFont);
            text5.setText("" + hScoresBL.getAll().get(i).getScore());
            text5.setFill(Color.BLACK);
            Text text6 = new Text();
            text6.setX(430);
            text6.setY(aux + 55);
            text6.setFont(gameFont);
            text6.setText(hScoresBL.getAll().get(i).getSurvived_time());
            text6.setFill(Color.BLACK);

            root.getChildren().addAll(text1, text5, text6);
            aux += 40;
        }
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                menu(gameScreen);
            }
        });

    }
/**
 * Displays a dialog box for the player to enter their name.
 * This method creates and displays a new window where the player can enter their name.
 * It gives the player two options:
 * Enter a name and press Enter to continue the game.
 * Select an option to play anonymously. The method configures the player 
 * and closes the input window, then proceeds to configure the game and display the game screen.
 * @param gameScreen
 * @return
 */
    private String enterName(Stage gameScreen) {
        Stage enterName = new Stage();
        enterName.getIcons().add(gameScreen.getIcons().getFirst());
        Group root = new Group();
        Scene scene = new Scene(root, 450, 150, Color.GREENYELLOW);
        gameFont = Font.loadFont(getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf"), 15);

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
                playerDTO = new HighScoresDTO(name.getText(), 0, "");
                // player.setNamePlayer(name.getText());
                if (!name.getText().isEmpty() && !name.getText().isBlank()) {
                    setPlayer(new PlayerGame(name.getText(), 0));
                    enterName.close();
                    setSettingsToGame();
                    gameScreenSnake(gameScreen);
                }
                name.setText(null);
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
            setPlayer(new PlayerGame("anonymous", 0));
            playerDTO = new HighScoresDTO(null, 0, null);
            enterName.close();
            setSettingsToGame();
            gameScreenSnake(gameScreen);
        });

        root.getChildren().add(anonymous);
        root.getChildren().add(name);
        root.getChildren().add(text);
        return getName();

    }

    /**
     * This method received by params an Stage which is modified
     * with an Scene. Start music and is waiting for a KeyFrame
     * that is pending on specific keywords.
     * 
     * @param gameScreen
     */
    private void gameScreenSnake(Stage gameScreen) {
        if (!isPaused) {
            setMusicPlaying(false);
            mp.stopMedia();
            mp.setSongNumber(2);
            mp.initialize(null, null);
            mp.playMedia();
        }
        Canvas gameZone = new Canvas(SCREENCANVASWIDTH, SCREENHEIGHT);
        GraphicsContext gc = gameZone.getGraphicsContext2D();
        StackPane root = new StackPane(gameZone);
        Scene gameScreenScene = new Scene(root, SCREENCANVASWIDTH, SCREENHEIGHT);
        timeline = new Timeline(
                new KeyFrame(Duration.millis(getSnakeVelocity()), e -> {
                    try {
                        run(gc, timeline, gameScreen, gameScreenScene);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        gameZone.relocate(0, 0);

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

        StartGame();
        timeline.play();

    }

    /**
     * StartGame Method by default is not paused, set primary
     * settings such as snakeWay ArrayList puts clear, then adds
     * head and body to snakeWay ArrayList and Chronometer
     * init also on MediaPlayer is reset in order to avoid duplicate
     * sounds.
     */
    private void StartGame() {
        if (!isPaused) {
            snakeWay.clear();
            snakeWay.add(new int[] { 22 / 2, 22 / 2 });
            snakeWay.add(new int[] { 22 / 2, (22 / 2) - 1 });
            chronometer = new Chronometer();
            chronometer.initChronometer();
            mp.resetMedia();
        }
    }

    /**
     * This method check if player is playing or died. Be player
     * is not playing, launches showMenuAfterGame method. Or sets
     * new levels onto Apple, RottenApple and Orange classes.
     * 
     * Also, allows pause game using ESCAPE keyword which implements
     * ActionListener.
     * 
     * Then recieved snake movements, checks collisions and finally
     * draw gameScreenGame components.
     * 
     * @param gc
     * @param timeline
     * @param gameScreen
     * @param gameScreenScene
     * @throws Exception
     */
    private void run(GraphicsContext gc, Timeline timeline, Stage gameScreen, Scene gameScreenScene) throws Exception {
        if (!getRunning()) {
            mp.stopMedia();

            gc.clearRect(20, 0, SCREENCANVASWIDTH - 40, SCREENHEIGHT - 80);
            gc.setFill(Color.RED);
            gc.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf"), 33));
            gc.fillText("Game Over", SCREENCANVASWIDTH / 2 - 140, SCREENHEIGHT / 2 - 50);

            gc.setFill(Color.RED);
            gc.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf"), 13));
            gc.fillText("Apples eaten: " + getAppleEaten(), SCREENCANVASWIDTH / 2 - 50, SCREENHEIGHT / 2 + 50);

            gc.setFill(Color.RED);
            gc.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf"), 13));
            gc.fillText("Level: " + getLevel(), SCREENCANVASWIDTH / 2 - 250, SCREENHEIGHT / 2 + 50);

            gc.drawImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/skull.png"))),
                    SCREENCANVASWIDTH / 2 - 300, SCREENHEIGHT / 2 + 35, SNAKEBODY, SNAKEBODY);

            gc.drawImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/skull.png"))),
                    SCREENCANVASWIDTH / 2 + 250, SCREENHEIGHT / 2 + 35, SNAKEBODY, SNAKEBODY);

            showMenuAfterGame(gameScreen, gc, gameScreenScene);

            isPaused = false;

            if (playerDTO.getPlayer_name() != null) {
                hBl = new HighScoresBL();
                playerDTO.setScore(player.getScore());

                playerDTO.setSurvived_time(chronometer.getChronometer());

                hBl.create(playerDTO);
            }
            timeline.stop();
            return;
        }

        gameScreenScene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                chronometer.stopChronometer();
                waitingMode(gameScreen, gc, gameScreenScene, timeline);
            }
        });

        apple.setLevelGame(getLevel());
        rottenApple.setLevelGame(getLevel());
        orange.setLevelGame(getLevel());
        moveSnake(gc, timeline, gameScreen, gameScreenScene);
        checkCollision();
        draw(gc);
    }

    /**
     * This method shows another screen that notify user that is
     * on waiting mode and can press a specific keyword to resume
     * game.
     * 
     * @param gameScreen
     * @param gc
     * @param gameScreenScene
     * @param timeline
     */
    private void waitingMode(Stage gameScreen, GraphicsContext gc, Scene gameScreenScene, Timeline timeline) {
        mp.pauseMedia();
        timeline.stop();
        gc.clearRect(20, 0, SCREENCANVASWIDTH - 40, SCREENHEIGHT - 80);
        gc.setFill(Color.RED);
        gc.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf"), 33));
        gc.fillText("Waiting...", SCREENCANVASWIDTH / 2 - 140, SCREENHEIGHT / 2 - 50);

        gc.setFill(Color.RED);
        gc.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf"), 13));
        gc.fillText(" --> Press 'X' to resume game <--", SCREENCANVASWIDTH / 2 - 250, SCREENHEIGHT / 2 + 50);

        gameScreenScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                chronometer.resumeChronometer();
                isPaused = true;
                gameScreenSnake(gameScreen);
                mp.playMedia();
            }
        });

    }

    /**
     * If user died, program shows to user a summary with
     * level, score and survived time gotten.
     * 
     * @param gameScreen
     * @param gc
     * @param gameScreenScene
     */
    private void showMenuAfterGame(Stage gameScreen, GraphicsContext gc, Scene gameScreenScene) {
        mp.stopMedia();
        mp.setSongNumber(1);
        mp.initialize(null, null);
        mp.playMedia();

        gc.setFill(Color.RED);
        gc.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf"), 15));
        gc.fillText("Presione 'X' para regresar al menú", SCREENCANVASWIDTH / 2 - 250, SCREENHEIGHT / 2 - 10);
        gameScreenScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                setSettingsToGame();
                menu(gameScreen);
            }
        });
    }

    /**
     * Receiving KeyDirection on gameScreenSnake, takes first item in
     * snake Way ArrayList and operate according to direction taken.
     * 
     * @param gc
     * @param timeline
     * @param gameScreen
     * @param gameScreenScene
     */
    private void moveSnake(GraphicsContext gc, Timeline timeline, Stage gameScreen, Scene gameScreenScene) {
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
            apple.getPositions().clear();
            apple.generateFruit();
            rottenApple.generateFruit();
            orange.generateFruit();
            setAppleEaten(getAppleEaten() + 1);
            if (getAppleEaten() % 3 == 0) {
                setLevel(getLevel() + 1); // Modify level and update levels from apple, rottenApple and banana classes
                orange.setMaxUse(3);
                setNewLevelToClasses();
                if (getLevel() % 5 == 0 && getAux() < 10) {
                    setAux(getAux() + 1);
                    setSnakeVelocity(getSnakeVelocity() - 10);
                    increaseDecreasedSpeed(gc, timeline, getSnakeVelocity(), gameScreen, gameScreenScene);
                }
            }
            player.calculateScore(getPressedTimes());
            setScore(player.getScore());
            setPressedTimes(0); // Snake already ate apple, so reset value into 0
        } else if (getLevel() >= 5) {
            if (newHead[0] == rottenApple.getFood()[0] && newHead[1] == rottenApple.getFood()[1]) {
                rottenApple.getPositions().clear();
                rottenApple.generateFruit();
                orange.generateFruit();
                apple.generateFruit();
                if (getLevel() % 5 == 0) {
                    setAux(getAux() - 1);
                }
                setLevel(getLevel() - 1);
                setNewLevelToClasses();
                snakeWay.removeLast();
                snakeWay.removeLast();
                setAppleEaten(getAppleEaten() - 1);
            } else if (getLevel() >= 10) {

                if ((newHead[0] == orange.getFood()[0] && newHead[1] == orange.getFood()[1])
                        && orange.getMaxUse() > 0) {
                    apple.generateFruit();
                    rottenApple.generateFruit();
                    orange.getPositions().clear();
                    orange.generateFruit();

                    setSnakeVelocity(getSnakeVelocity() + 10);
                    increaseDecreasedSpeed(gc, timeline, getSnakeVelocity(), gameScreen, gameScreenScene);

                    orange.setMaxUse(orange.getMaxUse() - 1);
                } else
                    snakeWay.removeLast();
            } else
                snakeWay.removeLast();
        } else {
            snakeWay.removeLast();
        }
    }

    /**
     * Set levels to Apple, RottenApple and Orange classes
     * to notify changes done after performed an action.
     */
    private void setNewLevelToClasses() {
        apple.setLevelGame(getLevel());
        rottenApple.setLevelGame(getLevel());
        orange.setLevelGame(getLevel());
    }

    /**
     * Allows set primary settings to avoid performance problems.
     */
    private void setSettingsToGame() {
        setRunning(true);
        setLevel(1);
        setAppleEaten(0);
        setAux(0);
        setPressedTimes(0);
        setScore(0);
        setSnakeVelocity(200);
    }

    /**
     * Depending of case, increase snake's velocity to 200ms maximum
     * or decrease until 80ms minimum.
     * 
     * @param gc
     * @param timeline
     * @param newSpeedMillis
     * @param gameScreen
     * @param gameScreenScene
     */
    public void increaseDecreasedSpeed(GraphicsContext gc, Timeline timeline, double newSpeedMillis, Stage gameScreen,
            Scene gameScreenScene) {
        KeyFrame keyFrame;
        timeline.stop();
        if (newSpeedMillis > 200) {
            keyFrame = new KeyFrame(Duration.millis(200),
                    e -> {
                        try {
                            run(gc, timeline, gameScreen, gameScreenScene);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    });
        } else {
            keyFrame = new KeyFrame(Duration.millis(newSpeedMillis),
                    e -> {
                        try {
                            run(gc, timeline, gameScreen, gameScreenScene);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    });
        }
        timeline.getKeyFrames().setAll(keyFrame);
        timeline.play();
    }

    /**
     * Check if the snake touches outlines or touches itself.
     */
    private void checkCollision() {
        int[] head = snakeWay.getFirst();
        if (head[0] <= 0 || head[0] >= 39 || head[1] <= 0 || head[1] >= 26) {
            setRunning(false);
        }
        for (int i = 1; i < snakeWay.size(); i++) {
            if (head[0] == snakeWay.get(i)[0] && head[1] == snakeWay.get(i)[1]) {
                setRunning(false);
            }
        }

        if (!getRunning())
            chronometer.stopChronometer();
    }

    /**
     * Draw all components that are passed as params.
     * @param gc
     */
    private void draw(GraphicsContext gc) {
        gameFont = Font.loadFont(getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf"), 13);
        gc.clearRect(0, 0, SCREENCANVASWIDTH, SCREENHEIGHT);

        gc.setFill(colorsBackground[getAux()]);
        gc.fillRect(20, 0, SCREENCANVASWIDTH - 40, SCREENHEIGHT - 80);

        gc.drawImage(new Image(apple.getPathImage()), apple.getPositions().get(0)[0] * SNAKEBODY,
                apple.getPositions().get(0)[1] * SNAKEBODY,
                SNAKEBODY + 7, SNAKEBODY + 7);

        if (getLevel() >= 5) {
            gc.drawImage(new Image(rottenApple.getPathImage()), rottenApple.getPositions().get(0)[0] * SNAKEBODY,
                    rottenApple.getPositions().get(0)[1] * SNAKEBODY,
                    SNAKEBODY, SNAKEBODY);
            if (getLevel() >= 10 && orange.getMaxUse() > 0) {
                gc.drawImage(new Image(orange.getPathImage()), orange.getPositions().get(0)[0] * SNAKEBODY,
                        orange.getPositions().get(0)[1] * SNAKEBODY, SNAKEBODY + 10, SNAKEBODY + 10);
            }
        }
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
        gc.setFont(gameFont);
        gc.fillText(chronometer.getChronometer(), SCREENCANVASWIDTH - 180, SCREENHEIGHT - 30);

        gc.setFill(Color.BLACK);
        gc.setFont(gameFont);
        gc.fillText("LEVEL " + getLevel(), 10, SCREENHEIGHT - 30);

        gc.setFill(Color.BLACK);
        gc.setFont(gameFont);
        gc.fillText("SCORE " + player.getScore(), 150, SCREENHEIGHT - 30);

        gc.setFill(Color.BLACK);
        gc.setFont(gameFont);
        gc.fillText("PLAYER " + player.getNamePlayer(), 340, SCREENHEIGHT - 30);

    }

}
