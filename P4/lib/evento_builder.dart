import 'evento.dart';

abstract class EventoBuilder {
  Evento evento = Evento();

  void construirNombre(String nombre);
  void construirFecha(String fecha);
  void construirUbicacion(String ubicacion);
}
