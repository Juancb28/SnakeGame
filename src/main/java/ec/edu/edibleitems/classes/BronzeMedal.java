package ec.edu.edibleitems.classes;

import java.io.InputStream;
import java.util.Objects;

import ec.edu.edibleitems.abstractClasses.SeeImage;

public class BronzeMedal extends SeeImage {
    
    public BronzeMedal() {
        positions.add(placeFood());
    }

    @Override
    public InputStream getPathImage() {
        return Objects.requireNonNull(getClass().getResourceAsStream("/images/medallaBronce.png"));
    }


    
}
