package ec.edu.edibleitems.classes;

import java.io.InputStream;
import java.util.Objects;

import ec.edu.edibleitems.abstractClasses.Fruit;

public class RottenApple extends Fruit {

    public RottenApple(){
        positions.add(getFood());
    }

    @Override
    public InputStream getPathImage() {
        return Objects.requireNonNull(getClass().getResourceAsStream("/images/rottenApple.png"));
    }

    @Override
    public void generateFruit() {
            positions.add(getFood());
    }
}
