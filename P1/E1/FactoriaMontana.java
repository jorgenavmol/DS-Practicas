package E1;

public class FactoriaMontana implements FactoriaCarreraYBicicleta {

    @Override
    public Bicicleta CrearBicicleta(){
        return new BicicletaMontana();
    }

    @Override
    public Carrera CrearCarrera(){
        return new CarreraMontana();
    }
}
