class Evento {
  late String nombre;
  late String fecha;
  late String ubicacion;

  //No hacen falta getters y setters en dart

  // Constructor por defecto
  Evento();

  // Named constructor
  Evento.conDatos(this.nombre, this.fecha, this.ubicacion);

  String mostrarEvento() {
    String infoEvento = '''
      Nombre del evento: $nombre
      Fecha del evento: $fecha
      Ubicación del evento: $ubicacion
      ''';
    return infoEvento;
  }

  //AÑADIDA.
  void cambiarNombre(String nuevoNombre) {
    nombre = nuevoNombre;
  }

  //AÑADIDA.
  void cambiarFecha(String nuevaFecha) {
    fecha = nuevaFecha;
  }

  //AÑADIDA.
  void cambiarUbicacion(String nuevaUbicacion) {
    ubicacion = nuevaUbicacion;
  }
}
