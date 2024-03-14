package E4;

public enum EstadoMotor {
    ACELERANDO("Acelerar"),
    FRENANDO("Frenar"),
    APAGADO("Apagar"),
    ENCENDIDO("Encender");

    private final String etiqueta;

    EstadoMotor(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }
}
