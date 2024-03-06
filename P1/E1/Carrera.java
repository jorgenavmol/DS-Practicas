package E1;
import java.util.*;

public abstract class Carrera{

    protected ArrayList<Bicicleta> bicicletas;

    Carrera(){
        bicicletas = new ArrayList<>();
    }

    public abstract void aniadeBicicleta(Bicicleta bici);

}