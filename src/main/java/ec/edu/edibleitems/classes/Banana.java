package ec.edu.edibleitems.classes;

import java.io.InputStream;
import java.util.Objects;

import ec.edu.edibleitems.abstractClasses.Fruit;

public class Banana extends Fruit {

    public Banana() {
        positions.add(placeFood());
    }

    @Override
    public InputStream getPathImage() {
        return Objects.requireNonNull(getClass().getResourceAsStream("/images/banana.png"));
    }

    @Override
    public void generateFruit() {
        if (getLevelGame() >= 10) {
            super.generateFruit();
        }
    }
}
