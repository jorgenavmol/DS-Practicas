package E1;

import java.util.Random;

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
        int contador = 0;
        try {
            while (!Thread.interrupted()) {
                contador++;
                if(contador == instanteRetirada){
                    this.retirada();
                }
                for (Bicicleta bicicleta : bicicletas) {
                    ((BicicletaCarretera)bicicleta).pedalear();
                }
                Thread.sleep(1000); // Simular una iteración de la carrera cada segundo
            }
        } catch (InterruptedException e) {
            System.out.println(nombre + " finalizada.");
        }
     
    }

    @Override
    public void retirada(){
        int N = bicicletas.size();
        double retiradas = N*0.1;
        Random random = new Random();

        for(int i = 0; i < retiradas; i++){
            int rand = random.nextInt(bicicletas.size());
            System.out.println("Se retira la bicicleta de carretera: " + bicicletas.get(rand).id);
            bicicletas.remove(rand);
        }
    }
}
