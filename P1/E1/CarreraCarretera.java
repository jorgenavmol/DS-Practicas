package E1;

public class CarreraCarretera extends Carrera {

    CarreraCarretera(){
        super();
    }

    @Override
    public void aniadeBicicleta (Bicicleta bici){
        bicicletas.add(bici);
    }
}
