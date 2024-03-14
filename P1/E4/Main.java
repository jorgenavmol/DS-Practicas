package E4;

public class Main {
    public static void main ( String [] args ){
        Objetivo salpicadero = new Objetivo();
        GestorFiltros gestor = new GestorFiltros(salpicadero);

        gestor.anadirFiltro(new CalcularVelocidad());
        gestor.anadirFiltro(new RepercutirRozamiento());

        gestor.cambio(salpicadero.revoluciones, EstadoMotor.ACELERANDO);
        gestor.cambio(salpicadero.revoluciones, EstadoMotor.ACELERANDO);

        System.out.println("Revoluciones: " + salpicadero.revoluciones);

    }
    
}
