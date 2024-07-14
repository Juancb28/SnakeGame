package ec.edu.edibleitems.classes;

import java.io.InputStream;
import java.util.Objects;

import ec.edu.edibleitems.abstractClasses.Fruit;

public class Orange extends Fruit {

    // Attributes
    private int maxUse;

    // Constructor
    public Orange() {
        positions.add(placeFood());
        setMaxUse(3);
    }

    // Getters & Setters
    public int getMaxUse() {
        return maxUse;
    }

    public void setMaxUse(int maxUse) {
        this.maxUse = maxUse;
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
