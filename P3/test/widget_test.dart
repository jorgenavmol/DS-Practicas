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
  testWidgets('Counter increments smoke test', (WidgetTester tester) async {
    // Build our app and trigger a frame.
    await tester.pumpWidget(const MyApp());

    // Verify that our counter starts at 0.
    expect(find.text('0'), findsOneWidget);
    expect(find.text('1'), findsNothing);

    // Tap the '+' icon and trigger a frame.
    await tester.tap(find.byIcon(Icons.add));
    await tester.pump();

    // Verify that our counter has incremented.
    expect(find.text('0'), findsNothing);
    expect(find.text('1'), findsOneWidget);
  });

  group("Tests de Empleados", () {
    late Jefe jefe;
    late Empleado trabajador;
    late Empleado organizador;
    late Jefe subJefe;
    late Empleado subTrabajador;

    setUp(() {
      //INICIALIZAMOS LOS EMPLEADOS.
      jefe = Jefe("Sr. Jefe");
      trabajador = Trabajador("Sr. Trabajador");
      organizador = Organizador("Sr. Organizador");
      subJefe = Jefe("Sr. SubJefe");
      subTrabajador = Trabajador("Sr. SubTrabajador");

      //ASIGNAMOS LOS EMPLEADOS AL JEFE.
      jefe.aniadeEmpleado(trabajador);
      jefe.aniadeEmpleado(organizador);
      jefe.aniadeEmpleado(subJefe);

      //ASIGNAMOS EL EMPLEADO AL SUBJEFE.
      subJefe.aniadeEmpleado(subTrabajador);      
    });

    test('Los jefes se crean correctamente', (){
      expect(jefe.getNombre(), "Sr. Jefe");   
    });

    test('Los empleados se agregan correctamente', (){
      expect(jefe.empleados.length, 3);
      expect(jefe.empleados[0].getNombre(), "Trabajador - Sr. Trabajador");
      expect(jefe.empleados[1].getNombre(), "Organizador - Sr. Organizador");
      expect(jefe.empleados[2].getNombre(), "Jefe - Sr. SubJefe");

      expect(subJefe.empleados.length, 1);
      expect(subJefe.empleados[0].getNombre(), "Trabajador - Sr. SubTrabajador");
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

    test('El organizador se crea correctamente', (){
      expect(organizador.getNombre(), "Organizador - Sr. Organizador");
    });

    test('El evento se construye correctamente', (){
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
