package E1;

public class CarreraCarretera extends Carrera {

    CarreraCarretera(){
        super();
    }

    @Override
    public void aniadeBicicleta (Bicicleta bici){
        bicicletas.add(bici);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                for (Bicicleta bicicleta : bicicletas) {
                    ((BicicletaCarretera)bicicleta).pedalear();
                }
                Thread.sleep(1000); // Simular una iteración de la carrera cada segundo
            }
        } catch (InterruptedException e) {
            System.out.println(nombre + " interrumpida.");
        }
     
    }
}
