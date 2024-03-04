package E1;

public class FactoriaCarretera implements FactoriaCarreraYBicicleta {

    @Override
    public Bicicleta CrearBicicleta(int id){
        return new BicicletaCarretera(id);
    }

    @Override
    public Carrera CrearCarrera(){
        return new CarreraCarretera();
    }
}
