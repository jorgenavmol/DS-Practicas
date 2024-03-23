package E4;

import java.util.ArrayList;

public class CadenaFiltros {
    double incrementoVelocidad = 0;
    ArrayList<Filtro> filtros = new ArrayList<>();
    Objetivo objetivo;

    public void anadirFiltro(Filtro filtro){
        filtros.add(filtro);
    }

    public void ejecutar(double revoluciones, EstadoMotor estado){
        double suma = revoluciones;
        for (Filtro filtro : filtros){
            suma = filtro.ejecutar(suma, estado);
        }
        this.objetivo.ejecutar(suma, estado);
    }

    public void setObjetivo(Objetivo objetivo){
        this.objetivo = objetivo;
    }

    Objetivo getObjetivo(){
        return objetivo;
    }
    
}
