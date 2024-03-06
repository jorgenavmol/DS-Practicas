package E1;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        
        int N = 0;

        if (args.length > 1) {
            System.out.println("El numero de argumentos tiene que ser 1 (Numero de bicicletas en las carreras).");
        } 

        else {
            N = Integer.parseInt(args[0]);

            System.out.println(N);
        }

        CarreraCarretera carreraCarr = new CarreraCarretera();
        CarreraMontana carreraMont = new CarreraMontana();

        ArrayList<Bicicleta> bicisCarretera = new ArrayList<>(N);

        for(int i = 0; i < N; i++){
            BicicletaCarretera bici = new BicicletaCarretera(i);
            bicisCarretera.add(bici);
            carreraCarr.aniadeBicicleta(bici);
        }

        System.out.println("Bici carretera: " + bicisCarretera.get(2).id);

        ArrayList<Bicicleta> bicisMontana = new ArrayList<>(N);

        for(int i = N; i < 2*N; i++){
            BicicletaMontana bici = new BicicletaMontana(i);
            bicisMontana.add(bici);
            carreraMont.aniadeBicicleta(bici);
        }

        System.out.println("Bici Montaña: " + bicisMontana.get(2).id);


    }
}