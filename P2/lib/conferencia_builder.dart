import 'evento_builder.dart';

class ConferenciaBuilder extends EventoBuilder {
  @override
  void construirNombre(String nombre) {
    evento.nombre = nombre;
  }

  @override
  void construirFecha(String fecha) {
    evento.fecha = fecha;
  }

  @override
  void construirUbicacion(String ubicacion) {
    evento.ubicacion = ubicacion;
  }
}
