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
        }

        CarreraCarretera carreraCarr = new CarreraCarretera();
        CarreraMontana carreraMont = new CarreraMontana();

        ArrayList<BicicletaCarretera> bicisCarretera = new ArrayList<>(N);

        for(int i = 0; i < N; i++){
            BicicletaCarretera bici = new BicicletaCarretera(i);
            bicisCarretera.add(bici);
            carreraCarr.aniadeBicicleta(bici);
        }

        ArrayList<BicicletaMontana> bicisMontana = new ArrayList<>(N);

        for(int i = N; i < 2*N; i++){
            BicicletaMontana bici = new BicicletaMontana(i);
            bicisMontana.add(bici);
            carreraMont.aniadeBicicleta(bici);
        }

        carreraMont.SetNombre("Carrera de montaña");
        carreraCarr.SetNombre("Carrera de carretera");

        Thread carreraMontana = new Thread(carreraMont);
        Thread carreraCarretera = new Thread(carreraCarr);

        
        carreraCarretera.start();
        carreraMontana.start();

        try {
            // Hacer que el programa principal espere 60 segundos
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            
        // Detener las carreras después de 60 segundos
        carreraMontana.interrupt();
        carreraCarretera.interrupt();

    }
}

