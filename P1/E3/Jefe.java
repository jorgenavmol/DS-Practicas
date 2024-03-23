package E3;
import java.util.ArrayList;

public class Jefe implements Empleado{

    private String nombre;
    ArrayList<Empleado> empleados = new ArrayList<Empleado>(); //LISTA DE EMPLEADOS A SU CARGO.
    
    public Jefe(String new_nombre){
        this.nombre = new_nombre;
    }

    public void aniadeEmpleado(Empleado empleado){
        empleados.add(empleado);
    }

    public void eliminaEmpleado(Empleado empleado){
        empleados.remove(empleado);
    }

    @Override
    public String getNombre(){
        return ("Jefe - " + nombre);
    }

    public ArrayList<Empleado> getEquipo(){
        return empleados;
    }
    
}
