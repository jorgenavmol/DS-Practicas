package E3;

class ConferenciaBuilder extends EventoBuilder {
    public void construirNombre() {
        evento.setNombre("Conferencia");
    }

    public void construirFecha() {
        evento.setFecha("4/10/2003");
    }

    public void construirUbicacion() {
        evento.setUbicacion("En casa de Jose");
    }
}