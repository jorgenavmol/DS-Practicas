package E1;

public class CarreraMontana extends Carrera {

    CarreraMontana(){
        super();
    }

    @Override
    public void aniadeBicicleta (Bicicleta bici){
        bicicletas.add(bici);
    }
}
