package ec.edu.edibleitems.classes;

import java.io.InputStream;
import java.util.Objects;

import ec.edu.edibleitems.interfaces.Fruit;

public class Banana extends Fruit {

    public Banana() {

    }

    @Override
    public InputStream getPathImage() {
        return Objects.requireNonNull(getClass().getResourceAsStream("/images/banana.png"));
    }
}