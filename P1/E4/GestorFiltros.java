package E4;

public class GestorFiltros {
    CadenaFiltros cadenaFiltros;

    public GestorFiltros(Objetivo objetivo){
        cadenaFiltros = new CadenaFiltros();
        cadenaFiltros.setObjetivo(objetivo);
    }

    public void anadirFiltro (Filtro filtro){
        cadenaFiltros.anadirFiltro(filtro);
    }

    public void cambio (double revoluciones, EstadoMotor estado){
        cadenaFiltros.ejecutar(revoluciones, estado);
    }
}
