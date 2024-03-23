package E3;

class Organizador extends Empleado{
    private EventoBuilder builder;

    public Organizador(String nombre, EventoBuilder builder) {
        super(nombre);
        this.builder = builder;
    }

    public void construirEvento() {
        builder.construirNombre();
        builder.construirFecha();
        builder.construirUbicacion();
    }

    public Evento getEvento() {
        return builder.getEvento();
    }
}