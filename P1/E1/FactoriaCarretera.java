package E1;

public class FactoriaCarretera implements FactoriaCarreraYBicicleta {

    public Bicicleta CrearBicicleta(){
        return new BicicletaCarretera();
    }

    public Carrera CrearCarrera(){
        return new CarreraCarretera();
    }
}
