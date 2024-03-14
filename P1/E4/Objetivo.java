package E4;

public class Objetivo {
    double revoluciones;
    double velocidad;
    double distancia;

    public Objetivo(){
        revoluciones = 0;
        velocidad =  0;
        distancia = 0;
    };

    void ejecutar(double revoluciones, EstadoMotor estado){
        this.revoluciones = revoluciones;
    }
}
