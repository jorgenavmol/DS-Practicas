package E3;

abstract class EventoBuilder {
    protected Evento evento = new Evento();

    public Evento getEvento() {
        return evento;
    }

    public abstract void construirNombre();
    public abstract void construirFecha();
    public abstract void construirUbicacion();
}