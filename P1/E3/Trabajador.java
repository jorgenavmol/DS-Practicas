package E3;

public class Trabajador implements Empleado{

    private String nombre;

    public Trabajador(String new_nombre){
        this.nombre = new_nombre;
    }

    @Override
    public String getNombre(){
        return ("Trabajador: " + nombre);
    }
    
}
