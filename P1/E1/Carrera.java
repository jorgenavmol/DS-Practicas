package E1;
import java.util.*;

public abstract class Carrera implements Runnable{

    protected ArrayList<Bicicleta> bicicletas;
    public String nombre;

    int instanteRetirada = 4;

    Carrera(){
        bicicletas = new ArrayList<>();
    }

    public void SetNombre(String nombre){
        this.nombre = nombre;
    }

    public abstract void aniadeBicicleta(Bicicleta bici);

    public abstract void retirada();
}
    