package ec.edu.edibleitems.abstractClasses;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public abstract class SeeImage {

    // Attributes
    private int[] food = new int[2];
    public ArrayList<int[]> positions = new ArrayList<>();
    private int levelGame;

    // Getters & Setters
    //El método devuelve un ArrayList de arreglos de enteros 
    public ArrayList<int[]> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<int[]> positions) {
        this.positions = positions;
    }

    public int[] getFood() {
        return food;
    }

    public void setFood(int[] food) {
        this.food = food;
    }

    public int getLevelGame() {
        return this.levelGame;
    }

    public void setLevelGame(int levelGame) {
        this.levelGame = levelGame;
    }

    // Methods
    public int[] placeFood() {
        food[0] = new Random().nextInt(22) + 2;
        food[1] = new Random().nextInt(22) + 2;
        return food;
    }

    public InputStream getPathImage() {
        return Objects.requireNonNull(getClass().getResourceAsStream(""));
    }

    public void generateFruit() {
        positions.add(placeFood());
    }

}
