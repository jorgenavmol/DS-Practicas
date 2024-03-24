package E1;

public class Main {
    public static void main(String[] args) {
        
        int N = 0;

        if (args.length > 1) {
            System.out.println("El numero de argumentos tiene que ser 1 (Numero de bicicletas en las carreras).");
        } 

        else {
            N = Integer.parseInt(args[0]);
        }

        FactoriaCarretera factoriaCarretera = new FactoriaCarretera();
        FactoriaMontana factoriaMontana = new FactoriaMontana();
        
        Carrera carreraCarr = factoriaCarretera.CrearCarrera();
        Carrera carreraMont = factoriaMontana.CrearCarrera();

        for(int i = 0; i < N; i++){
            Bicicleta bici = factoriaCarretera.CrearBicicleta(i);
            carreraCarr.aniadeBicicleta(bici);
        }

        for(int i = N; i < 2*N; i++){
            Bicicleta bici = factoriaMontana.CrearBicicleta(i);
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
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            
        // Detener las carreras después de 60 segundos
        carreraMontana.interrupt();
        carreraCarretera.interrupt();

    }
}

