package E3;

class EventoDirector {
    private EventoBuilder builder;

    public EventoDirector(EventoBuilder builder) {
        this.builder = builder;
    }

    public void construirEvento(InvitadosStrategy invitadosStrategy, CateringStrategy cateringStrategy) {
        builder.construirNombre();
        builder.construirFecha();
        builder.construirUbicacion();
        builder.construirInvitados(invitadosStrategy);
        builder.construirCatering(cateringStrategy);
    }

    public Evento getEvento() {
        return builder.getEvento();
    }
}