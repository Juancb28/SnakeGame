package ec.edu.edibleitems.classes;

import java.io.InputStream;
import java.util.Objects;

import ec.edu.edibleitems.abstractClasses.Fruit;

public class bronzeMedal extends Fruit {
    
    public bronzeMedal() {
        positions.add(placeFood());
    }

    @Override
    public InputStream getPathImage() {
        return Objects.requireNonNull(getClass().getResourceAsStream("/images/medallaBronce.png"));
    }


    
}
