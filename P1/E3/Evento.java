package E3;

import java.util.ArrayList;

class Evento {
    private String nombre;
    private String fecha;
    private String ubicacion;
    private ArrayList<String> invitados;
    private String catering;

    //CONSTRUCTOR VACIO DE EVENTO.
    public Evento(){}

    public Evento(String nombre, String fecha, String ubicacion, ArrayList<String> invitados, String catering) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.invitados = invitados;
        this.catering = catering;
    }

    //GETTERS Y SETTERS

    void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    void setFecha(String fecha){
        this.fecha = fecha;
    }

    void setUbicacion(String ubicacion){
        this.ubicacion = ubicacion;
    }

    void setInvitados(ArrayList<String> invitados){
        this.invitados = invitados;
    }

    void setCatering(String catering){
        this.catering = catering;
    }

    void mostrarEvento(){
        System.out.println("Nombre del evento: " + nombre);
        System.out.println("Fecha del evento: " + fecha);
        System.out.println("Ubicacion del evento: " + ubicacion);
        System.out.println("Lista de invitados: ");
        for (int i = 0; i < invitados.size(); i++){
            System.out.println("Invitado " + i + ": " + invitados.get(i));
        }
        System.out.println("Catering del evento: " + catering);
    }
}