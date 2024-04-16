import 'evento_builder.dart';
import 'evento.dart';
import 'empleado.dart';

class Organizador implements Empleado {
  late EventoBuilder _builder;
  late String _nombreOrg;

  Organizador(String nombreOrg) {
    _nombreOrg = nombreOrg;
  }

  void construirEvento(String nombre, String fecha, String ubicacion) {
    _builder.construirNombre(nombre);
    _builder.construirFecha(fecha);
    _builder.construirUbicacion(ubicacion);
  }

  Evento getEvento() {
    return _builder.evento;
  }

  @override
  String getNombre() {
    return "Organizador - $_nombreOrg";
  }

  void setBuilder(EventoBuilder builder) {
    _builder = builder;
  }
}
