package E3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        EventoBuilder builder;
        Organizador directorOrg;
        String nombreEvento, fechaEvento, ubicacionEvento;

        System.out.printf("¿Quién es el organizador del evento?: ");
        String nombreOrganizador = scanner.nextLine();

        System.out.printf("¿Qué tipo de evento desea crear? (Conferencia=1, Boda=2): ");
        int tipoEvento = scanner.nextInt();
        scanner.nextLine();
        
        //Creamos un constructor dependiendo del tipo de evento.
        if (tipoEvento == 1) {
            builder = new ConferenciaBuilder();
            directorOrg = new Organizador(nombreOrganizador, builder);

            System.out.printf("Nombre del evento: ");
            nombreEvento = scanner.nextLine();

            System.out.printf("Fecha del evento: ");
            fechaEvento = scanner.nextLine();

            System.out.printf("Ubicación del evento: ");
            ubicacionEvento = scanner.nextLine();

            //Construimos el evento.
            directorOrg.construirEvento(nombreEvento, fechaEvento, ubicacionEvento);

            Evento conferencia = directorOrg.getEvento();
            System.out.println("Nombre del Organizador: " + directorOrg.getNombre());
            conferencia.mostrarEvento();
        }
        
        else if (tipoEvento == 2) {
            builder = new BodaBuilder();
            directorOrg = new Organizador(nombreOrganizador, builder);

            System.out.printf("Nombre del evento: ");
            nombreEvento = scanner.nextLine();

            System.out.printf("Fecha del evento: ");
            fechaEvento = scanner.nextLine();

            System.out.printf("Ubicación del evento: ");
            ubicacionEvento = scanner.nextLine();

            //Construimos el evento.
            directorOrg.construirEvento(nombreEvento, fechaEvento, ubicacionEvento);

            Evento boda = directorOrg.getEvento();
            System.out.println("Nombre del Organizador: " + directorOrg.getNombre());
            boda.mostrarEvento();
        }
        
        else {
            System.out.println("Tipo de evento no reconocido");
            scanner.close();
            return;
        }

        scanner.close();
    }
}