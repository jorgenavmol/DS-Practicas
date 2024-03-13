package E3;


public class Main {
    public static void main(String[] args) {
        // Crear un constructor para una conferencia
        EventoBuilder builderConferencia = new ConferenciaBuilder();
        EventoDirector director = new EventoDirector(builderConferencia);
        director.construirEvento(new ListaInvitadosGenerica(), new CateringBasico());
        Evento conferencia = director.getEvento();

        // Crear un constructor para una boda
        EventoBuilder builderBoda = new BodaBuilder();
        director = new EventoDirector(builderBoda);
        director.construirEvento(new ListaInvitadosVIP(), new CateringPremium());
        Evento boda = director.getEvento();

        // Mostrar los eventos construidos
        conferencia.mostrarEvento();
        boda.mostrarEvento();
    }
}