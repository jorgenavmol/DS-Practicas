package E4;

interface Filtro {
    double maxRPM = 5000;
    double minRPM = 0;
    
    abstract double ejecutar(double revoluciones, EstadoMotor estado);
}
