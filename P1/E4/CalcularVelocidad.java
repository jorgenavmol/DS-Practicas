package E4;

public class CalcularVelocidad implements Filtro{


    public double ejecutar(double revoluciones, EstadoMotor estado){
        double incrementoVelocidad = 0;
        switch(estado) {
            case ACELERANDO:
                incrementoVelocidad = 100.0;
                break;
            case FRENANDO:
                incrementoVelocidad = -100;
                break;
            case APAGADO:
                incrementoVelocidad = 0; revoluciones = 0;
                break;
            case ENCENDIDO:
                incrementoVelocidad = 0; revoluciones = 900;
                break;
        }
        
        return revoluciones + incrementoVelocidad;
    }
    
}
