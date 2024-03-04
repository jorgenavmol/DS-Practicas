package E1;

public class FactoriaMontana implements FactoriaCarreraYBicicleta {

    @Override
    public Bicicleta CrearBicicleta(int id){
        return new BicicletaMontana(id);
    }

    @Override
    public Carrera CrearCarrera(){
        return new CarreraMontana();
    }
}
