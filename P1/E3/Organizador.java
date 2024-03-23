package E3;

class Organizador implements Empleado{

    private EventoBuilder builder;
    private String nombreOrg;

    public Organizador(String nombreOrg, EventoBuilder builder) {
        this.nombreOrg = nombreOrg;
        this.builder = builder;
    }

    public void construirEvento(String nombre, String fecha, String ubicacion) {
        builder.construirNombre(nombre);
        builder.construirFecha(fecha);
        builder.construirUbicacion(ubicacion);
    }

    public Evento getEvento() {
        return builder.getEvento();
    }

    public String getNombre(){
        return ("Organizador: " + nombreOrg);
    }
}