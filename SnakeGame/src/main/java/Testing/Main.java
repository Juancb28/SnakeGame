package Testing;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.Random;

public class Main extends Application {
    private static final int WIDTH = 22;
    private static final int HEIGHT = 22;
    private static final int TILE_SIZE = 20;
    private static final int CANVAS_WIDTH = WIDTH * TILE_SIZE;
    private static final int CANVAS_HEIGHT = HEIGHT * TILE_SIZE;

    private enum Direction {UP, DOWN, LEFT, RIGHT}

    private Direction direction = Direction.RIGHT;
    private LinkedList<int[]> snake = new LinkedList<>();
    private int[] food = new int[2];
    private boolean running = true;

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), e -> run(gc)));
        timeline.setCycleCount(Timeline.INDEFINITE);

        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root, CANVAS_WIDTH, CANVAS_HEIGHT);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP && direction != Direction.DOWN) direction = Direction.UP;
            else if (event.getCode() == KeyCode.DOWN && direction != Direction.UP) direction = Direction.DOWN;
            else if (event.getCode() == KeyCode.LEFT && direction != Direction.RIGHT) direction = Direction.LEFT;
            else if (event.getCode() == KeyCode.RIGHT && direction != Direction.LEFT) direction = Direction.RIGHT;
        });

        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        startGame();
        timeline.play();
    }

    private void startGame() {
        snake.clear();
        snake.add(new int[]{WIDTH / 2, HEIGHT / 2});
        placeFood();
    }

    private void placeFood() {
        Random rand = new Random();
        food[0] = rand.nextInt(WIDTH);
        food[1] = rand.nextInt(HEIGHT);
    }

    private void run(GraphicsContext gc) {
        if (!running) {
            gc.setFill(Color.RED);
            gc.fillText("Game Over", CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2);
            return;
        }

        moveSnake();
        checkCollision();
        draw(gc);
    }

    private void moveSnake() {
        int[] head = snake.getFirst();
        int newX = head[0];
        int newY = head[1];

        switch (direction) {
            case UP -> newY--;
            case DOWN -> newY++;
            case LEFT -> newX--;
            case RIGHT -> newX++;
        }

        int[] newHead = {newX, newY};
        snake.addFirst(newHead);

        if (newHead[0] == food[0] && newHead[1] == food[1]) {
            placeFood();
        } else {
            snake.removeLast();
        }
    }

    private void checkCollision() {
        int[] head = snake.getFirst();
        if (head[0] < 0 || head[0] >= WIDTH || head[1] < 0 || head[1] >= HEIGHT) {
            running = false;
        }

        for (int i = 1; i < snake.size(); i++) {
            if (head[0] == snake.get(i)[0] && head[1] == snake.get(i)[1]) {
                running = false;
            }
        }
    }

    private void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

        gc.setFill(Color.GREEN);
        for (int[] part : snake) {
            gc.fillRect(part[0] * TILE_SIZE, part[1] * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }

        gc.setFill(Color.RED);
        gc.fillRect(food[0] * TILE_SIZE, food[1] * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    public static void main(String[] args) {
        launch(args);
    }
}