package E4;

public class Objetivo {
    double revoluciones;
    double velocidad;
    double distancia_real;
    double distancia_actual;
    double pi = Math.PI;
    double radio = 0.15;
    double constante = 0.06;
    double seg = 3600;

    public Objetivo(){
        this.revoluciones = 0;
        this.velocidad =  0;
        this.distancia_real = 0;
        this.distancia_actual = 0;
    };

    void ejecutar(double revoluciones, EstadoMotor estado){
        this.revoluciones = revoluciones;
        this.velocidad = 2 * pi * radio * revoluciones * constante;
        if (estado == EstadoMotor.APAGADO){
            this.distancia_actual = 0;
        } 
        else if(estado == EstadoMotor.ACELERANDO){
            distancia_actual += velocidad/seg;
            distancia_real += velocidad/seg;
        } 
        else if (estado == EstadoMotor.FRENANDO && velocidad > 0){
            distancia_actual += velocidad/seg;
            distancia_real += velocidad/seg;
        }      
    }

    double getRevoluciones(){
        return revoluciones;
    }

    double getVelocidad(){
        return velocidad;
    }

    double getDistanciaReal(){
        return distancia_real;
    }

    double getDistanciaActual(){
        return distancia_actual;
    }
}
