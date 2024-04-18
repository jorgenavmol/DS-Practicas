import 'package:flutter/material.dart';
import 'evento_builder.dart';
import 'organizador.dart';
import 'jefe.dart';
import 'empleado.dart';
import 'trabajador.dart';
import 'conferencia_builder.dart';
import 'boda_builder.dart';
import 'tipo_empleado.dart';
import 'tipo_evento.dart';

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
        colorScheme: ColorScheme.fromSeed(
          seedColor: Colors.greenAccent,
        ),
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
  bool jefesIngresados = false;
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
            enabled: !jefesIngresados,
            decoration: const InputDecoration(labelText: 'Número de Jefes'),
          ),
          ElevatedButton(
            onPressed: () {
              int numJefes = int.tryParse(numJefesController.text) ?? 0;
              if (numJefes > 0 && !jefesIngresados) {
                for (int i = 0; i < numJefes; i++) {
                  jefes.add(Jefe.empty());
                }
                showDialog(
                  context: context,
                  builder: (BuildContext context) {
                    return CrearJefesDialog(
                      jefes: jefes,
                    );
                  },
                ).then((_) {
                  setState(() {
                    jefesIngresados = true;
                  }); // Actualizar la pantalla cuando se cierre el diálogo de creación de jefes
                });
              } else {
                // Mostrar mensaje de error
              }
            },
            child: const Text('Ingresar Jefes'),
          ),
          const SizedBox(height: 24.0),
          // Botones de jefes
          Wrap(
            spacing: 8.0,
            runSpacing: 8.0,
            children: [
              for (var jefe in jefes)
                ElevatedButton(
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => GestorEmpleadosPage(
                          jefe: jefe,
                          jefes: jefes,
                        ),
                      ),
                    ).then((_) {
                      // Actualizar la lista de jefes cuando se regrese a la página de inicio
                      setState(() {});
                    });
                  },
                  child: Text(jefe.nombre),
                ),
            ],
          ),
        ],
      ),
    );
  }
}

class GestorEmpleadosPage extends StatefulWidget {
  final Jefe jefe;
  final List<Jefe> jefes;
  const GestorEmpleadosPage(
      {super.key, required this.jefe, required this.jefes});

  @override
  State<GestorEmpleadosPage> createState() => _GestorEmpleadosPageState();
}

class _GestorEmpleadosPageState extends State<GestorEmpleadosPage> {
  final TextEditingController nombreController = TextEditingController();
  late TipoEmpleado tipoSeleccionado = TipoEmpleado.trabajador;
  late TipoEvento eventoSeleccionado = TipoEvento.boda;
  final TextEditingController nombreEvController = TextEditingController();
  final TextEditingController fechaController = TextEditingController();
  final TextEditingController ubiController = TextEditingController();

  Empleado crearEmpleado(String nombre) {
    switch (tipoSeleccionado) {
      case TipoEmpleado.trabajador:
        return Trabajador(nombre);
      case TipoEmpleado.organizador:
        return Organizador(nombre);
      case TipoEmpleado.jefe:
        Jefe nuevo = Jefe(nombre);
        widget.jefes.add(nuevo);
        return nuevo;
      default:
        throw Exception('Tipo de empleado no válido');
    }
  }

  // Función para crear un evento
  void _crearEvento(BuildContext context, Organizador organizadorSeleccionado) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: const Text('Crear Evento'),
          content: SingleChildScrollView(
            child: Column(
              children: [
                // Aquí puedes agregar los campos necesarios para crear un evento
                // Por ejemplo:
                DropdownButtonFormField<TipoEvento>(
                  value: eventoSeleccionado,
                  onChanged: (value) {
                    setState(() {
                      eventoSeleccionado = value!;
                    });
                  },
                  items: TipoEvento.values
                      .map<DropdownMenuItem<TipoEvento>>((TipoEvento tipo) {
                    return DropdownMenuItem<TipoEvento>(
                      value: tipo,
                      child: Text(tipo
                          .toString()
                          .split('.')
                          .last), // Para mostrar solo el nombre del enum
                    );
                  }).toList(),
                ),
                TextFormField(
                  controller: nombreEvController,
                  decoration:
                      const InputDecoration(labelText: 'Título del evento'),
                ),
                TextFormField(
                  controller: fechaController,
                  decoration:
                      const InputDecoration(labelText: 'Fecha del evento'),
                ),
                TextFormField(
                  controller: ubiController,
                  decoration:
                      const InputDecoration(labelText: 'Ubicación del evento'),
                ),
                ElevatedButton(
                  onPressed: () {
                    String nombre = nombreEvController.text;
                    if (nombre.isNotEmpty) {
                      if (eventoSeleccionado == TipoEvento.boda) {
                        EventoBuilder constructor = BodaBuilder();
                        organizadorSeleccionado.setBuilder(constructor);
                        organizadorSeleccionado.construirEvento(nombreEvController.text, fechaController.text, ubiController.text);
                      } else if (eventoSeleccionado == TipoEvento.conferencia) {
                        EventoBuilder constructor = ConferenciaBuilder();
                        organizadorSeleccionado.setBuilder(constructor);
                        organizadorSeleccionado.construirEvento(nombreEvController.text, fechaController.text, ubiController.text);
                      }
                    }
                    Navigator.pop(
                        context); // Cerrar el diálogo después de agregar el empleado
                  },
                  child: const Text('Crear Evento'),
                ),
              ],
            ),
          ),
        );
      },
    );
  }

  void _mostrarEvento(BuildContext context, Organizador organizadorSeleccionado) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: const Text('Evento'),
          content: SingleChildScrollView(
            child: Column(
              children: [
                 Text(
                  'Nombre: ${organizadorSeleccionado.getEvento().nombre}'
                ),
                Text(
                    'Fecha: ${organizadorSeleccionado.getEvento().fecha}'
                ),
                Text(
                    'Ubicación: ${organizadorSeleccionado.getEvento().ubicacion}'
                ),

                ElevatedButton(
                  onPressed: () {
                    Navigator.pop(context); // Cerrar el diálogo
                  },
                  child: const Text('Salir'),
                ),
              ],
            ),
          ),
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Gestor de Empleados para ${widget.jefe.nombre}'),
      ),
      body: Column(
        children: [
          Expanded(
            child: ListView.builder(
              itemCount: widget.jefe.empleados.length,
              itemBuilder: (context, index) {
                final empleado = widget.jefe.empleados[index];
                if (empleado is Organizador) {
                  return ListTile(
                    title: Text(empleado.getNombre()),
                    onTap: () {
                      // Al hacer tap en el organizador, se abre el formulario para crear un evento
                      if(!empleado.tieneEvento){
                        _crearEvento(context, empleado);
                      }
                      else{
                        _mostrarEvento(context, empleado);
                      }

                    },
                  );
                } else {
                  return ListTile(
                    title: Text(empleado.getNombre()),
                  );
                }
              },
            ),
          ),
          ElevatedButton(
            onPressed: () {
              // Mostrar un diálogo o navegar a una nueva pantalla para ingresar los detalles del nuevo empleado
              _mostrarAgregarEmpleadoDialog(context);
            },
            child: const Text('Añadir Empleado'),
          ),
        ],
      ),
    );
  }

  void _mostrarAgregarEmpleadoDialog(BuildContext context) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: const Text('Agregar Empleado'),
          content: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              TextFormField(
                controller: nombreController,
                decoration:
                    const InputDecoration(labelText: 'Nombre del empleado'),
              ),
              DropdownButtonFormField<TipoEmpleado>(
                value: tipoSeleccionado,
                onChanged: (value) {
                  setState(() {
                    tipoSeleccionado = value!;
                  });
                },
                items: TipoEmpleado.values
                    .map<DropdownMenuItem<TipoEmpleado>>((TipoEmpleado tipo) {
                  return DropdownMenuItem<TipoEmpleado>(
                    value: tipo,
                    child: Text(tipo
                        .toString()
                        .split('.')
                        .last), // Para mostrar solo el nombre del enum
                  );
                }).toList(),
              ),
              ElevatedButton(
                onPressed: () {
                  String nombre = nombreController.text;
                  if (nombre.isNotEmpty) {
                    Empleado nuevoEmpleado = crearEmpleado(nombre);
                    setState(() {
                      widget.jefe.aniadeEmpleado(nuevoEmpleado);
                      nombreController.clear();
                    });
                    Navigator.pop(
                        context); // Cerrar el diálogo después de agregar el empleado
                  }
                },
                child: const Text('Añadir'),
              ),
            ],
          ),
        );
      },
    );
  }
}

class CrearJefesDialog extends StatefulWidget {
  final List<Jefe> jefes;
  const CrearJefesDialog({super.key, required this.jefes});

  @override
  State<CrearJefesDialog> createState() => _CrearJefesDialogState();
}

class _CrearJefesDialogState extends State<CrearJefesDialog> {
  final List<TextEditingController> nombreJefeControllers = [];

  @override
  Widget build(BuildContext context) {
    return AlertDialog(
      title: const Text('Ingresar Nombre del Jefe'),
      content: Column(
        mainAxisSize: MainAxisSize.min,
        children: [
          for (var jefeIndex = 0; jefeIndex < widget.jefes.length; jefeIndex++)
            Column(
              children: [
                TextFormField(
                  controller: nombreJefeControllers[jefeIndex],
                  decoration:
                      const InputDecoration(labelText: 'Nombre del Jefe'),
                ),
                const SizedBox(height: 16.0),
              ],
            ),
          ElevatedButton(
            onPressed: () {
              List<Jefe> nuevosJefes = [];
              for (var i = 0; i < widget.jefes.length; i++) {
                String nombreJefe = nombreJefeControllers[i].text;
                if (nombreJefe.isNotEmpty) {
                  widget.jefes[i].nombre = nombreJefe;
                  nuevosJefes.add(widget.jefes[i]);
                }
              }
              Navigator.of(context).pop();
            },
            child: const Text('Finalizar'),
          ),
        ],
      ),
    );
  }

  @override
  void initState() {
    super.initState();
    // Inicializar los controladores
    for (var _ in widget.jefes) {
      nombreJefeControllers.add(TextEditingController());
    }
  }

  @override
  void dispose() {
    // Liberar los controladores
    for (var controller in nombreJefeControllers) {
      controller.dispose();
    }
    super.dispose();
  }
}
