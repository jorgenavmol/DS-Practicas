package E3;

class Organizador implements Empleado{

    private EventoBuilder builder;
    private String nombreOrg;

    public Organizador(String nombreOrg) {
        this.nombreOrg = nombreOrg;
    }

    public void construirEvento(String nombre, String fecha, String ubicacion) {
        builder.construirNombre(nombre);
        builder.construirFecha(fecha);
        builder.construirUbicacion(ubicacion);
    }

    public Evento getEvento() {
        return builder.getEvento();
    }

    @Override
    public String getNombre(){
        return ("Organizador: " + nombreOrg);
    }

    public void setBuilder(EventoBuilder builder) {
        this.builder = builder;
    }
}