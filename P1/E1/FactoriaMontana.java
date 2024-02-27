package E1;

public class FactoriaMontana implements FactoriaCarreraYBicicleta {

    public Bicicleta CrearBicicleta(){
        return new BicicletaMontana();
    }

    public Carrera CrearCarrera(){
        return new CarreraMontana();
    }
}
