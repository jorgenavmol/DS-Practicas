package E1;

public class FactoriaCarretera implements FactoriaCarreraYBicicleta {

    @Override
    public Bicicleta CrearBicicleta(){
        return new BicicletaCarretera();
    }

    @Override
    public Carrera CrearCarrera(){
        return new CarreraCarretera();
    }
}
