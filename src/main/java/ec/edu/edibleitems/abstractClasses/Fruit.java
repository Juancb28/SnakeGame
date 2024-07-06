package ec.edu.edibleitems.abstractClasses;

import java.io.InputStream;
import java.util.Objects;
import java.util.Random;

public abstract class Fruit {

    private int[] food = new int[2];

    public int getPosition() {
        return 0;
    }

    public int getQuantity() {
        return 0;
    }

    public void placeFood() {
        food[0] = new Random().nextInt(22) + 2;
        food[1] = new Random().nextInt(22) + 2;
    }

    public int[] getFood() {
        return food;
    }

    public void setFood(int[] food) {
        this.food = food;
    }

    public InputStream getPathImage() {
        return Objects.requireNonNull(getClass().getResourceAsStream(""));
    }


}
