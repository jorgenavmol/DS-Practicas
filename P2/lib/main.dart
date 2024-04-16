import 'package:flutter/material.dart';
import 'evento_builder.dart';
import 'organizador.dart';
import 'jefe.dart';
import 'trabajador.dart';
import 'conferencia_builder.dart';
import 'boda_builder.dart';
import 'tipo_empleado.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Creador de Eventos',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.greenAccent,),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Creador de Eventos'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  final TextEditingController numJefesController = TextEditingController();
  final TextEditingController nombreEmpleadoController = TextEditingController();
  final TextEditingController nombreEventoController = TextEditingController();
  final TextEditingController fechaEventoController = TextEditingController();
  final TextEditingController ubicacionEventoController = TextEditingController();
  String tipoEvento = 'Conferencia'; // Valor por defecto

  final List<Jefe> jefes = [];
  final List<Organizador> organizadores = [];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: ListView(
        padding: const EdgeInsets.all(16.0),
        children: [
          const Text(
            'Ingresar Jefes y Empleados:',
            style: TextStyle(fontSize: 24.0),
          ),
          TextFormField(
            controller: numJefesController,
            keyboardType: TextInputType.number,
            decoration: const InputDecoration(labelText: 'Número de Jefes'),
          ),
          ElevatedButton(
            onPressed: () {
              int numJefes = int.tryParse(numJefesController.text) ?? 0;
              if (numJefes > 0) {
                for (int i = 0; i < numJefes; i++) {
                  jefes.add(Jefe('Jefe ${i + 1}'));
                }
                showDialog(
                  context: context,
                  builder: (BuildContext context) {
                    return CrearEmpleadosDialog(
                      jefes: jefes,
                      organizadores: organizadores
                    );
                  },
                );
              } else {
                // Mostrar mensaje de error
              }
            },
            child: const Text('Ingresar Jefes y Empleados'),
          ),
          const SizedBox(height: 24.0),
          // Aquí colocaremos la sección para crear eventos según el organizador
          // La implementación dependerá de cómo quieras diseñar esta parte
        ],
      ),
    );
  }
}

class CrearEmpleadosDialog extends StatefulWidget {
  final List<Jefe> jefes;
  final List<Organizador> organizadores;
  const CrearEmpleadosDialog({super.key, required this.jefes, required this.organizadores});

  @override
  State<CrearEmpleadosDialog>createState() => _CrearEmpleadosDialogState();
}

class _CrearEmpleadosDialogState extends State<CrearEmpleadosDialog> {
  final TextEditingController nombreEmpleadoController = TextEditingController();
  TipoEmpleado? tipoEmpleadoSeleccionado;

  @override
  Widget build(BuildContext context) {
    return AlertDialog(
      title: const Text('Ingresar Empleados'),
      content: Column(
        mainAxisSize: MainAxisSize.min,
        children: [
          for (var jefe in widget.jefes)
            Column(
              children: [
                Text('Jefe: ${jefe.nombre}'),
                TextFormField(
                  controller: nombreEmpleadoController,
                  decoration: const InputDecoration(labelText: 'Nombre del Empleado'),
                ),
                DropdownButtonFormField<TipoEmpleado>(
                  value: tipoEmpleadoSeleccionado,
                  onChanged: (TipoEmpleado? newValue) {
                    setState(() {
                      tipoEmpleadoSeleccionado = newValue;
                    });
                  },
                  items: TipoEmpleado.values.map<DropdownMenuItem<TipoEmpleado>>((TipoEmpleado value) {
                    return DropdownMenuItem<TipoEmpleado>(
                      value: value,
                      child: Text(value.toString().split('.').last),
                    );
                  }).toList(),
                ),
                ElevatedButton(
                  onPressed: () {
                    String nombreEmpleado = nombreEmpleadoController.text;
                    if (tipoEmpleadoSeleccionado != null) {
                      switch (tipoEmpleadoSeleccionado) {
                        case TipoEmpleado.organizador:
                          Organizador organizador = Organizador(nombreEmpleado);
                          widget.organizadores.add(organizador); // Modificación aquí
                          jefe.aniadeEmpleado(organizador);
                          break;
                        case TipoEmpleado.trabajador:
                          jefe.aniadeEmpleado(Trabajador(nombreEmpleado));
                          break;
                        case TipoEmpleado.jefe:
                          Jefe nuevoJefe = Jefe(nombreEmpleado);
                          widget.jefes.add(nuevoJefe); // Modificación aquí
                          jefe.aniadeEmpleado(nuevoJefe);
                          break;
                        default:
                          break;
                      }
                      setState(() {
                        nombreEmpleadoController.clear();
                        tipoEmpleadoSeleccionado = null;
                      });
                    } else {
                      // Mostrar mensaje de error indicando que se debe seleccionar un tipo de empleado
                    }
                  },
                  child: const Text('Agregar Empleado'),
                ),
                const SizedBox(height: 16.0),
              ],
            ),
          ElevatedButton(
            onPressed: () {
              Navigator.of(context).pop();
            },
            child: const Text('Finalizar'),
          ),
        ],
      ),
    );
  }
}


