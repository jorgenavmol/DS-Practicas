// This is a basic Flutter widget test.
//
// To perform an interaction with a widget in your test, use the WidgetTester
// utility in the flutter_test package. For example, you can send tap and scroll
// gestures. You can also use WidgetTester to find child widgets in the widget
// tree, read text, and verify that the values of widget properties are correct.

import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:p2/boda_builder.dart';
import 'package:p2/empleado.dart';
import 'package:p2/evento_builder.dart';
import 'package:p2/jefe.dart';

import 'package:p2/main.dart';
import 'package:p2/organizador.dart';
import 'package:p2/trabajador.dart';

void main() {
  group("Tests de Empleados: ", () {
    late Jefe jefe;
    late Jefe jefeVacio;
    late Empleado trabajador;
    late Empleado organizador;
    late Jefe subJefe;
    late Empleado subTrabajador1;
    late Empleado subTrabajador2;
    late Empleado subOrganizador;

    setUp(() {
      //INICIALIZAMOS LOS EMPLEADOS.
      jefe = Jefe("Sr. Jefe");
      trabajador = Trabajador("Sr. Trabajador");
      organizador = Organizador("Sr. Organizador");
      subJefe = Jefe("Sr. SubJefe");
      subTrabajador1 = Trabajador("Sr. SubTrabajador1");
      subTrabajador2 = Trabajador("Sr. SubTrabajador2");
      subOrganizador = Organizador("Sra. SubOrganizadora");
      jefeVacio = Jefe.empty();
    });

    void agregarEmpleados() {
      //ASIGNAMOS LOS EMPLEADOS AL JEFE.
      jefe.aniadeEmpleado(trabajador);
      jefe.aniadeEmpleado(organizador);
      jefe.aniadeEmpleado(subJefe);

      //ASIGNAMOS EL EMPLEADO AL SUBJEFE.
      subJefe.aniadeEmpleado(subTrabajador1);
      subJefe.aniadeEmpleado(subTrabajador2);
      subJefe.aniadeEmpleado(subOrganizador);
    }

    test('La función getNombre() devuelve el nombre correctamente', () {
      String nombreJefe = "Jefe - Sr. Jefe";
      expect(jefe.getNombre(), nombreJefe);
    });

    test('Los jefes se crean correctamente', () {
      expect(jefe.getNombre(), "Jefe - Sr. Jefe");
      expect(jefe.getEquipo().length, 0);
      expect(subJefe.getNombre(), "Jefe - Sr. SubJefe");
      expect(subJefe.getEquipo().length, 0);
      expect(jefeVacio.getNombre(), "Jefe - ");
      expect(jefeVacio.getEquipo().length, 0);
    });

    test('Los empleados se agregan correctamente', () {
      agregarEmpleados();
      expect(jefe.empleados.length, 3);
      expect(jefe.empleados[0].getNombre(), "Trabajador - Sr. Trabajador");
      expect(jefe.empleados[1].getNombre(), "Organizador - Sr. Organizador");
      expect(jefe.empleados[2].getNombre(), "Jefe - Sr. SubJefe");

      expect(subJefe.empleados.length, 3);
      expect(
          subJefe.empleados[0].getNombre(), "Trabajador - Sr. SubTrabajador1");
      expect(
          subJefe.empleados[1].getNombre(), "Trabajador - Sr. SubTrabajador2");
      expect(subJefe.empleados[2].getNombre(),
          "Organizador - Sra. SubOrganizadora");
    });

    test('El tipo de empleado es el correcto', () {
      //TipoEmpleado solo lo usamos en el main para los botones de la interfaz, no hay un atributo TipoEmpleado en cada clase que inicialicemos
      expect(trabajador, isA<Trabajador>());
      expect(organizador, isA<Organizador>());
      expect(subJefe, isA<Jefe>());
      expect(subTrabajador1, isA<Trabajador>());
      expect(subTrabajador2, isA<Trabajador>());
      expect(subOrganizador, isA<Organizador>());
    });

    test('Los empleados se eliminan correctamente', () {
      agregarEmpleados();
      jefe.eliminaEmpleado(trabajador);
      expect(jefe.empleados.length, 2);
      expect(jefe.empleados[0].getNombre(), "Organizador - Sr. Organizador");
      expect(jefe.empleados[1].getNombre(), "Jefe - Sr. SubJefe");

      subJefe.eliminaEmpleado(subTrabajador1);
      expect(subJefe.empleados.length, 2);
      expect(
          subJefe.empleados[0].getNombre(), "Trabajador - Sr. SubTrabajador2");
      expect(subJefe.empleados[1].getNombre(),
          "Organizador - Sra. SubOrganizadora");
    });

    test('Se obtiene correctamente el equipo de un subjefe', () {
      agregarEmpleados();
      //ACCEDE AL SUBJEFE EN LA LISTA DE EMPLEADOS DEL JEFE
      Jefe subjefe = (jefe.getEquipo()[2] as Jefe);
      //ACCEDE A LA LISTA DE EMPLEADOS DE ESE SUBJEFE
      List<Empleado> equipoSubJefe = subjefe.getEquipo();

      expect(equipoSubJefe.length, 3);
      expect(equipoSubJefe[0].getNombre(), "Trabajador - Sr. SubTrabajador1");
      expect(equipoSubJefe[1].getNombre(), "Trabajador - Sr. SubTrabajador2");
      expect(
          equipoSubJefe[2].getNombre(), "Organizador - Sra. SubOrganizadora");
    });
  });

  group("Tests de Organizador", () {
    late Organizador organizador;
    late EventoBuilder constructor;

    setUp(() {
      constructor = BodaBuilder();
      organizador = Organizador("Sr. Organizador");
      organizador.setBuilder(constructor);
      organizador.construirEvento("Boda", "01/01/2022", "Mi casa");
    });

    test('El organizador se crea correctamente', () {
      expect(organizador.getNombre(), "Organizador - Sr. Organizador");
    });

    test('El evento se construye correctamente', () {
      expect(organizador.tieneEvento, true);
      expect(organizador.getEvento().nombre, "Boda");
      expect(organizador.getEvento().fecha, "01/01/2022");
      expect(organizador.getEvento().ubicacion, "Mi casa");
    });
  });

  group("Test de botones", () {
    testWidgets('Test botón Ingresar Jefes', (WidgetTester tester) async {
      // Construye la aplicación y dispara un frame.
      await tester.pumpWidget(const MyApp());

      // Encuentra el botón Finalizar por el texto.
      Finder botonIngresar = find.text('Ingresar Jefes');

      // Verifica que el botón Finalizar se encuentra en la pantalla.
      expect(botonIngresar, findsOneWidget);

      // Toca el botón Finalizar y dispara un frame.
      await tester.tap(botonIngresar);
      await tester.pump();

      /* // Verifica que se muestra un diálogo.
    expect(find.byType(Dialog), findsOneWidget); */

      // Aquí puedes agregar más verificaciones para el diálogo, como verificar que contiene un campo de texto para introducir el nombre del jefe.
      expect(find.byType(TextField), findsOneWidget);
    });
  });
}
