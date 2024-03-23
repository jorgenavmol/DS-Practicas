package E3;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        EventoBuilder builder;
        Organizador directorOrg;
        String nombreEvento, fechaEvento, ubicacionEvento;
        ArrayList<Jefe> jefes = new ArrayList<Jefe>();
        ArrayList<Organizador> organizadores = new ArrayList<Organizador>();

        // System.out.printf("¿Quién es el organizador del evento?: ");
        // String nombreOrganizador = scanner.nextLine();

        //Obtenemos el numero de jefes.
        System.out.print("Numero de jefes deseados: ");
        int numJefes = scanner.nextInt();
        scanner.nextLine();

        for(int i=0; i<numJefes; i++){

            System.out.print("Nombre del jefe numero " + (i+1) + ": ");
            String nombreJefe = scanner.nextLine();
            jefes.add(new Jefe(nombreJefe));

            System.out.print("Numero de empleados a cargo de " + nombreJefe + ": ");
            int numEmpleados = scanner.nextInt();
            scanner.nextLine();

            for(int j=0; j<numEmpleados; j++){
                System.out.print("Nombre del empleado numero " + (j+1) + ": ");
                String nombreTrabajador = scanner.nextLine();
                System.out.printf("¿Qué tipo de empleado es " + nombreTrabajador + "? (Organizador=1, Jefe=2, Trabajador=3): ");
                int tipoEmpleado = scanner.nextInt();
                scanner.nextLine();
                switch (tipoEmpleado) {
                    case 1:
                        Organizador organizador = new Organizador(nombreTrabajador);
                        organizadores.add(organizador);
                        jefes.get(i).aniadeEmpleado(organizador);
                        break;
                    
                    case 2:
                        Jefe jefe = new Jefe(nombreTrabajador);
                        jefes.get(i).aniadeEmpleado(jefe);
                        break;
                    
                    case 3:
                        Trabajador trabajador = new Trabajador(nombreTrabajador);
                        jefes.get(i).aniadeEmpleado(trabajador);
                        break;
                
                    default:
                        System.out.print("No se ha introducido un tipo válido. No se añade.");
                        break;
                }
            }
        }
        
        if (organizadores.size() == 0){
            System.out.printf("No se ha añadido ningun organizador, añada uno por favor: ");
            String nombreOrganizador = scanner.nextLine();
            Organizador organizador = new Organizador(nombreOrganizador);
            System.out.printf("¿De qué jefe es empleado este organizador?: \n");
            for (int i=1; i<=jefes.size(); i++){
                System.out.print(i + "- " + jefes.get(i-1).getNombre() + "\n");
            }
            int indice = scanner.nextInt() - 1;
            scanner.nextLine();
            jefes.get(indice).aniadeEmpleado(organizador);
            organizadores.add(organizador);
        }

        System.out.printf("¿Cual de los siguientes empleados desea que organice el evento?: \n");
        for (int i=1; i<=organizadores.size(); i++){
            System.out.print(i + "- " + organizadores.get(i-1).getNombre() + "\n");
        }
        int organizadorElegido = scanner.nextInt()-1;
        scanner.nextLine();
        
        System.out.printf("¿Qué tipo de evento desea crear? (Conferencia=1, Boda=2): ");
        int tipoEvento = scanner.nextInt();
        scanner.nextLine();
        
        //Creamos un constructor dependiendo del tipo de evento.
        if (tipoEvento == 1) {
            builder = new ConferenciaBuilder();
            directorOrg = organizadores.get(organizadorElegido);
            directorOrg.setBuilder(builder);

            System.out.printf("Nombre del evento: ");
            nombreEvento = "Conferencia "+scanner.nextLine();

            System.out.printf("Fecha del evento: ");
            fechaEvento = scanner.nextLine();

            System.out.printf("Ubicación del evento: ");
            ubicacionEvento = scanner.nextLine();

            //Construimos el evento.
            directorOrg.construirEvento(nombreEvento, fechaEvento, ubicacionEvento);

            Evento conferencia = directorOrg.getEvento();
            System.out.println("\n-----------------------------------------");
            System.out.println(directorOrg.getNombre());
            conferencia.mostrarEvento();
            System.out.println("-----------------------------------------\n");
        }
        
        else if (tipoEvento == 2) {
            builder = new BodaBuilder();
            directorOrg = organizadores.get(organizadorElegido);
            directorOrg.setBuilder(builder);

            System.out.printf("Nombre del evento: ");
            nombreEvento = "Boda "+scanner.nextLine();

            System.out.printf("Fecha del evento: ");
            fechaEvento = scanner.nextLine();

            System.out.printf("Ubicación del evento: ");
            ubicacionEvento = scanner.nextLine();

            //Construimos el evento.
            directorOrg.construirEvento(nombreEvento, fechaEvento, ubicacionEvento);

            Evento boda = directorOrg.getEvento();
            System.out.println("\n-----------------------------------------");
            System.out.println(directorOrg.getNombre());
            boda.mostrarEvento();
            System.out.println("-----------------------------------------\n");
        }
        
        else {
            System.out.println("Tipo de evento no reconocido");
            scanner.close();
            return;
        }

        //Mostramos los jefes y sus empleados.
        for(int i=0; i<jefes.size(); i++){
            System.out.println("Empleados a cargo de " + jefes.get(i).getNombre() + ":");
            ArrayList<Empleado> cargo = jefes.get(i).getEquipo();
            
            for(int j=0; j<cargo.size(); j++){
                System.out.println(cargo.get(j).getNombre());
            }
        }

        scanner.close();
    }
}