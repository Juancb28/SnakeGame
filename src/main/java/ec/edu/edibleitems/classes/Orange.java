package ec.edu.edibleitems.classes;

import java.io.InputStream;
import java.util.Objects;

import ec.edu.edibleitems.abstractClasses.Fruit;

public class Orange extends Fruit {

    public Orange() {
        positions.add(placeFood());
    }

    @Override
    public InputStream getPathImage() {
        return Objects.requireNonNull(getClass().getResourceAsStream("/images/orange.png"));
    }

    @Override
    public void generateFruit() {
        if (getLevelGame() >= 10) {
            super.generateFruit();
        }
    }
}
