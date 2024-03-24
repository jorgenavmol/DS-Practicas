package E3;

class Evento {
    private String nombre;
    private String fecha;
    private String ubicacion;
    //private String catering;

    //CONSTRUCTOR VACIO DE EVENTO.
    public Evento(){}

    public Evento(String nombre, String fecha, String ubicacion/*, String catering*/) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        //this.catering = catering;
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

    /*void setCatering(String catering){
        this.catering = catering;
    }*/

    void mostrarEvento(){
        System.out.println("Nombre del evento: " + nombre);
        System.out.println("Fecha del evento: " + fecha);
        System.out.println("Ubicacion del evento: " + ubicacion);
        //System.out.println("Catering del evento: " + catering);
    }
}