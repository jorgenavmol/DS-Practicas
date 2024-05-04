import 'package:flutter/material.dart';

import 'evento_builder.dart';
import 'evento.dart';
import 'empleado.dart';

class Organizador implements Empleado {
  late EventoBuilder _builder;
  late String _nombreOrg;
  late bool tieneEvento = false;
  late String nombreJefe;

  Organizador(String nombreOrg) {
    _nombreOrg = nombreOrg;
  }

  void construirEvento(String nombre, String fecha, String ubicacion) {
    _builder.construirNombre(nombre);
    _builder.construirFecha(fecha);
    _builder.construirUbicacion(ubicacion);
    tieneEvento = true;
  }

  //AÑADIDA.
  EventoBuilder getBuilder() {
    return _builder;
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
