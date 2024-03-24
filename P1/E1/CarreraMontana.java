package E1;

import java.util.Random;

public class CarreraMontana extends Carrera {

    CarreraMontana(){
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
                    ((BicicletaMontana)bicicleta).pedalear();
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
        double retiradas = N*0.2;
        Random random = new Random();

        for(int i = 0; i < retiradas; i++){
            int rand = random.nextInt(bicicletas.size());
            //Para que se retine un mínimo de 1 bicicleta, N debe ser 5 o más
            System.out.println("Se retira la bicicleta de montaña: " + bicicletas.get(rand).id);
            bicicletas.remove(rand);
        }
    }
}
