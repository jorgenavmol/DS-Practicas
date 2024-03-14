package E4;

public class RepercutirRozamiento implements Filtro{
    double rozamiento = -10;

    public double ejecutar(double revoluciones, EstadoMotor estado){
        if(revoluciones + rozamiento >= maxRPM) return maxRPM;
        else if (revoluciones + rozamiento <= minRPM) return minRPM;
        else return revoluciones + rozamiento;
    }
}
