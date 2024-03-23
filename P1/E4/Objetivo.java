package E4;

public class Objetivo {
    double revoluciones;
    double velocidad;
    double distancia;
    double pi = Math.PI;
    double radio = 0.15;
    double constante = 0.06;

    public Objetivo(){
        this.revoluciones = 0;
        this.velocidad =  0;
        this.distancia = 0;
    };

    void ejecutar(double revoluciones, EstadoMotor estado){
        this.revoluciones = revoluciones;
        this.velocidad = 2 * pi * radio * revoluciones * constante;
    }

    double getRevoluciones(){
        return revoluciones;
    }

    double getVelocidad(){
        return velocidad;
    }

    double getDistancia(){
        return distancia;
    }
}
