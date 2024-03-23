package E3;

abstract class EventoBuilder {
    protected Evento evento = new Evento();

    public Evento getEvento() {
        return evento;
    }

    public abstract void construirNombre(String nombre);
    public abstract void construirFecha(String fecha);
    public abstract void construirUbicacion(String ubicacion);
}