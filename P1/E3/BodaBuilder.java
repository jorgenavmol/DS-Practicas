package E3;

class BodaBuilder extends EventoBuilder {
    
    public void construirNombre() {
        evento.setNombre("Boda");
    }

    public void construirFecha() {
        evento.setFecha("16/12/2003");
    }

    public void construirUbicacion() {
        evento.setUbicacion("En mi casa");
    }

    public void construirInvitados(InvitadosStrategy strategy) {
        evento.setInvitados(strategy.getInvitados());
    }

    public void construirCatering(CateringStrategy strategy) {
        evento.setCatering(strategy.getCatering());
    }
}