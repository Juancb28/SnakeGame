package ec.edu.edibleitems.classes;

import java.io.InputStream;
import java.util.Objects;

import ec.edu.edibleitems.abstractClasses.SeeImage;

public class RottenApple extends SeeImage {

    public RottenApple(){
        positions.add(placeFood());
    }

    @Override
    public InputStream getPathImage() {
        return Objects.requireNonNull(getClass().getResourceAsStream("/images/rottenApple.png"));
    }

    @Override
    public void generateFruit() {
        if (getLevelGame() >= 5) {
            super.generateFruit();
        }
    }

}
