package E3;

class ConferenciaBuilder extends EventoBuilder {
    public void construirNombre(String nombre) {
        evento.setNombre(nombre);
    }

    public void construirFecha(String fecha) {
        evento.setFecha(fecha);
    }

    public void construirUbicacion(String ubicacion) {
        evento.setUbicacion(ubicacion);
    }
}