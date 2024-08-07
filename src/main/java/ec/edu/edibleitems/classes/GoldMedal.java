package ec.edu.edibleitems.classes;
import java.io.InputStream;
import java.util.Objects;
import ec.edu.edibleitems.abstractClasses.SeeImage;

public class GoldMedal extends SeeImage{
    
       @Override
    public InputStream getPathImage() {
        return Objects.requireNonNull(getClass().getResourceAsStream("/images/medallaOro.png"));
    }


}
