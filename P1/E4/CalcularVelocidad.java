package E4;

public class CalcularVelocidad implements Filtro{


    public double ejecutar(double revoluciones, EstadoMotor estado){
        double incrementoVelocidad = 0;
        switch(estado) {
            case ACELERANDO:
                incrementoVelocidad = 30.0;
                break;
            case FRENANDO:
                incrementoVelocidad = -30;
                break;
            case APAGADO:
                incrementoVelocidad = 0; revoluciones = 0;
                break;
            case ENCENDIDO:
                incrementoVelocidad = 0; revoluciones = 500;
                break;
        }
        
        return revoluciones + incrementoVelocidad;
    }
    
}
