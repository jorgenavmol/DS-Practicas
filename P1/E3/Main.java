package E3;


public class Main {
    public static void main(String[] args) {
        // Crear un constructor para una conferencia
        EventoBuilder builderConferencia = new ConferenciaBuilder();
        Organizador director = new Organizador("Juan", builderConferencia);
        director.construirEvento();
        Evento conferencia = director.getEvento();

        // Crear un constructor para una boda
        EventoBuilder builderBoda = new BodaBuilder();
        director = new Organizador("Pepe", builderBoda);
        director.construirEvento();
        Evento boda = director.getEvento();

        // Mostrar los eventos construidos
        conferencia.mostrarEvento();
        boda.mostrarEvento();
    }
}