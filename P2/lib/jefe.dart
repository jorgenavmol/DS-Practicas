import 'dart:core';
import 'empleado.dart';

class Jefe implements Empleado {
  late String nombre;
  final List<Empleado> _empleados = [];

  Jefe(String nomb) {
    nombre = nomb;
  }

  void aniadeEmpleado(Empleado empleado) {
    _empleados.add(empleado);
  }

  void eliminaEmpleado(Empleado empleado) {
    _empleados.remove(empleado);
  }

  @override
  String getNombre() {
    return "Jefe - $nombre";
  }

  List<Empleado> getEquipo() {
    return _empleados;
  }
}
