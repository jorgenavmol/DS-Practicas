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

        ArrayList<BicicletaCarretera> bicisCarretera = new ArrayList<>(N);

        for(int i = 0; i < N; i++){
            BicicletaCarretera bici = new BicicletaCarretera(i);
            bicisCarretera.add(bici);
            carreraCarr.aniadeBicicleta(bici);
        }

        System.out.println("Bici carretera: " + bicisCarretera.get(2).id);

        ArrayList<BicicletaMontana> bicisMontana = new ArrayList<>(N);

        for(int i = N; i < 2*N; i++){
            BicicletaMontana bici = new BicicletaMontana(i);
            bicisMontana.add(bici);
            carreraMont.aniadeBicicleta(bici);
        }

        System.out.println("Bici Montaña: " + bicisMontana.get(2).id);

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

// import java.util.ArrayList;
// import java.util.List;

// public class SimulacionCarreras {

//     public static void main(String[] args) {
//         // Crear listas de bicicletas para las carreras
//         List<BicicletaMontana> bicicletasMontana = new ArrayList<>();
//         List<BicicletaCarretera> bicicletasCarretera = new ArrayList<>();

//         // Agregar bicicletas a las listas (puedes personalizar según tus necesidades)
//         bicicletasMontana.add(new BicicletaMontana("MTB1"));
//         bicicletasMontana.add(new BicicletaMontana("MTB2"));

//         bicicletasCarretera.add(new BicicletaCarretera("R1"));
//         bicicletasCarretera.add(new BicicletaCarretera("R2"));

//         // Crear hebras para las carreras
//         Thread carreraMontana = new Thread(new Carrera(bicicletasMontana, "Carrera de Montaña"));
//         Thread carreraCarretera = new Thread(new Carrera(bicicletasCarretera, "Carrera de Carretera"));

//         // Iniciar las carreras
//         carreraMontana.start();
//         carreraCarretera.start();

//         try {
//             // Hacer que el programa principal espere 60 segundos
//             Thread.sleep(60000);
//         } catch (InterruptedException e) {
//             e.printStackTrace();
//         }

//         // Detener las carreras después de 60 segundos
//         carreraMontana.interrupt();
//         carreraCarretera.interrupt();
//     }
// }

// // Clase base para bicicletas
// abstract class Bicicleta {
//     protected String nombre;

//     public Bicicleta(String nombre) {
//         this.nombre = nombre;
//     }

//     abstract void pedalear();
// }

// // Clases específicas para bicicletas de montaña y de carretera
// class BicicletaMontana extends Bicicleta {
//     public BicicletaMontana(String nombre) {
//         super(nombre);
//     }

//     @Override
//     void pedalear() {
//         System.out.println(nombre + " pedaleando en terreno de montaña.");
//     }
// }

// class BicicletaCarretera extends Bicicleta {
//     public BicicletaCarretera(String nombre) {
//         super(nombre);
//     }

//     @Override
//     void pedalear() {
//         System.out.println(nombre + " pedaleando en carretera.");
//     }
// }

// // Clase para simular una carrera
// class Carrera implements Runnable {
//     private List<? extends Bicicleta> bicicletas;
//     private String nombre;

//     public Carrera(List<? extends Bicicleta> bicicletas, String nombre) {
//         this.bicicletas = bicicletas;
//         this.nombre = nombre;
//     }

//     @Override
//     public void run() {
//         try {
//             while (!Thread.interrupted()) {
//                 for (Bicicleta bicicleta : bicicletas) {
//                     bicicleta.pedalear();
//                 }
//                 Thread.sleep(1000); // Simular una iteración de la carrera cada segundo
//             }
//         } catch (InterruptedException e) {
//             System.out.println(nombre + " interrumpida.");
//         }
//     }
// }
