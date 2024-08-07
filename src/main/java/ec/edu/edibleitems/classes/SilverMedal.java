package ec.edu.edibleitems.classes;
import java.io.InputStream;
import java.util.Objects;
import ec.edu.edibleitems.abstractClasses.Fruit;

public class SilverMedal extends Fruit {
    public SilverMedal() {
        positions.add(placeFood());
    }

    @Override
    public InputStream getPathImage() {
        return Objects.requireNonNull(getClass().getResourceAsStream("/images/medallaPlata.png"));
    }

}
