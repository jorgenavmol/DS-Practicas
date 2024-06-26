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
          seedColor: Colors.lightBlueAccent,
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
            onPressed:() {
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
                          organizadores: organizadores,
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
          const SizedBox(height: 24.0),
          const Text(
            'Resumen eventos:',
            style: TextStyle(fontSize: 24.0),
          ),
          // Mostrar el resumen de eventos
          Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              for (var organizador in organizadores)
                if (organizador.tieneEvento)
                  Card(
                    margin: const EdgeInsets.symmetric(vertical: 8.0),
                    child: Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            organizador.getNombre(),
                            style: const TextStyle(fontWeight: FontWeight.bold),
                          ),
                          Text(
                            'Jefe: ${organizador.nombreJefe}',
                            style: const TextStyle(fontWeight: FontWeight.bold),
                          ),
                          Text('Nombre: ${organizador.getEvento().nombre}'),
                          Text('Fecha: ${organizador.getEvento().fecha}'),
                          Text('Ubicación: ${organizador.getEvento().ubicacion}'),
                        ],
                      ),
                    ),
                  ),
            ],
          ),
        ],
      ),
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

class GestorEmpleadosPage extends StatefulWidget {
  final Jefe jefe;
  final List<Jefe> jefes;
  final List<Organizador> organizadores;
  const GestorEmpleadosPage(
      {super.key, required this.jefe, required this.jefes, required this.organizadores});

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
                    tileColor: Colors.blue.withOpacity(0.1), // Cambia el color de fondo cuando se toca
                    onTap: () {
                      // Al hacer tap en el organizador, se abre el formulario para crear un evento
                      if (!empleado.tieneEvento) {
                        _crearEvento(context, empleado);
                      } else {
                        _mostrarEvento(context, empleado);
                      }
                    },
                    trailing: IconButton(
                      icon: const Icon(Icons.delete),
                      onPressed: () {
                        //AÑADIDO PARA ELIMINAR EMPLEADOS
                        _mostrarConfirmacionEliminarEmpleado(context, empleado);
                      },
                    ),
                  );

                } else {
                  return ListTile(
                    title: Text(empleado.getNombre()),
                    trailing: IconButton(
                      icon: const Icon(Icons.delete),
                      onPressed: () {
                        //AÑADIDO PARA ELIMINAR EMPLEADOS
                        _mostrarConfirmacionEliminarEmpleado(context, empleado);
                      },
                    ),
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

  Empleado crearEmpleado(String nombre) {
    switch (tipoSeleccionado) {
      case TipoEmpleado.trabajador:
        return Trabajador(nombre);
      case TipoEmpleado.organizador:
        Organizador nuevoOrg = Organizador(nombre);
        widget.organizadores.add(nuevoOrg);
        nuevoOrg.nombreJefe = widget.jefe.nombre;
        return nuevoOrg;
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
                    String fecha = fechaController.text;
                    String ubi = ubiController.text;
                    if (nombre.isNotEmpty && fecha.isNotEmpty && ubi.isNotEmpty) {
                      if (eventoSeleccionado == TipoEvento.boda) {
                        EventoBuilder constructor = BodaBuilder();
                        organizadorSeleccionado.setBuilder(constructor);
                        organizadorSeleccionado.construirEvento(nombre, fecha, ubi);
                      } else if (eventoSeleccionado == TipoEvento.conferencia) {
                        EventoBuilder constructor = ConferenciaBuilder();
                        organizadorSeleccionado.setBuilder(constructor);
                        organizadorSeleccionado.construirEvento(nombre, fecha, ubi);
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

  //MODIFICADO PARA SER EDITABLE
  void _mostrarEvento(BuildContext context, Organizador organizadorSeleccionado) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        TextEditingController nombreEventoController = TextEditingController();
        TextEditingController fechaEventoController = TextEditingController();
        TextEditingController ubicacionEventoController = TextEditingController();

        nombreEventoController.text = organizadorSeleccionado.getEvento().nombre; // Establecer el texto inicial del nombre del evento
        fechaEventoController.text = organizadorSeleccionado.getEvento().fecha; // Establecer el texto inicial de la fecha del evento
        ubicacionEventoController.text = organizadorSeleccionado.getEvento().ubicacion; // Establecer el texto inicial de la ubicación del evento

        return AlertDialog(
          title: const Text('Evento'),
          content: SingleChildScrollView(
            child: Column(
              children: [
                // Nombre del evento editable
                TextFormField(
                  controller: nombreEventoController,
                  decoration: const InputDecoration(
                    labelText: 'Nombre del evento',
                    border: InputBorder.none, // Eliminar el borde del TextFormField
                  ),
                  onTap: () {
                    _editarNombreEvento(context, organizadorSeleccionado, nombreEventoController); //AÑADIDO
                  },
                ),
                const Divider(), // Separador entre los campos

                // Fecha del evento editable
                TextFormField(
                  controller: fechaEventoController,
                  decoration: const InputDecoration(
                    labelText: 'Fecha del evento',
                    border: InputBorder.none, // Eliminar el borde del TextFormField
                  ),
                  onTap: () {
                    _editarFechaEvento(context, organizadorSeleccionado, fechaEventoController); //AÑADIDO
                  },
                ),
                const Divider(), // Separador entre los campos

                // Ubicación del evento editable
                TextFormField(
                  controller: ubicacionEventoController,
                  decoration: const InputDecoration(
                    labelText: 'Ubicación del evento',
                    border: InputBorder.none, // Eliminar el borde del TextFormField
                  ),
                  onTap: () {
                    _editarUbicacionEvento(context, organizadorSeleccionado, ubicacionEventoController); //AÑADIDO
                  },
                ),
              ],
            ),
          ),
          actions: [
            ElevatedButton(
              onPressed: () {
                Navigator.pop(context); // Cerrar el diálogo
              },
              child: const Text('Guardar'),
            ),
          ],
        );
      },
    );
  }

  //AÑADIDO
  void _editarNombreEvento(BuildContext context, Organizador organizador, TextEditingController controller) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: const Text('Editar Nombre del Evento'),
          content: TextFormField(
            controller: controller,
            decoration: const InputDecoration(hintText: 'Nuevo nombre del evento'),
          ),
          actions: [
            ElevatedButton(
              onPressed: () {
                String nuevoNombre = controller.text;
                organizador.cambiarNombreEvento(nuevoNombre);
                Navigator.pop(context); // Cerrar el diálogo de edición
                // Mostrar mensaje de éxito o hacer otras acciones si es necesario
              },
              child: const Text('Guardar'),
            ),
            ElevatedButton(
              onPressed: () {
                Navigator.pop(context); // Cerrar el diálogo de edición
              },
              child: const Text('Cancelar'),
            ),
          ],
        );
      },
    );
  }

  //AÑADIDO
  void _editarFechaEvento(BuildContext context, Organizador organizador, TextEditingController controller) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: const Text('Editar Fecha del Evento'),
          content: TextFormField(
            controller: controller,
            decoration: const InputDecoration(hintText: 'Nueva fecha del evento'),
          ),
          actions: [
            ElevatedButton(
              onPressed: () {
                String nuevaFecha = controller.text;
                organizador.cambiarFechaEvento(nuevaFecha);
                Navigator.pop(context); // Cerrar el diálogo de edición
                // Mostrar mensaje de éxito o hacer otras acciones si es necesario
              },
              child: const Text('Guardar'),
            ),
            ElevatedButton(
              onPressed: () {
                Navigator.pop(context); // Cerrar el diálogo de edición
              },
              child: const Text('Cancelar'),
            ),
          ],
        );
      },
    );
  }

  //AÑADIDO
  void _editarUbicacionEvento(BuildContext context, Organizador organizador, TextEditingController controller) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: const Text('Editar Ubicación del Evento'),
          content: TextFormField(
            controller: controller,
            decoration: const InputDecoration(hintText: 'Nueva ubicación del evento'),
          ),
          actions: [
            ElevatedButton(
              onPressed: () {
                String nuevaUbi = controller.text;
                organizador.cambiarUbiEvento(nuevaUbi);
                Navigator.pop(context); // Cerrar el diálogo de edición
                // Mostrar mensaje de éxito o hacer otras acciones si es necesario
              },
              child: const Text('Guardar'),
            ),
            ElevatedButton(
              onPressed: () {
                Navigator.pop(context); // Cerrar el diálogo de edición
              },
              child: const Text('Cancelar'),
            ),
          ],
        );
      },
    );
  }

// Las funciones para editar la fecha y la ubicación del evento serían similares a _editarNombreEvento.



  //AÑADIDO PARA ELIMINAR EMPLEADOS
  void _mostrarConfirmacionEliminarEmpleado(BuildContext context, Empleado empleado) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text('Eliminar ${empleado.getNombre()}'),
          content: Text('¿Estás seguro de que quieres eliminar a ${empleado.getNombre()}?'),
          actions: [
            TextButton(
              onPressed: () {
                Navigator.of(context).pop(); // Cerrar el diálogo de confirmación
              },
              child: const Text('Cancelar'),
            ),
            TextButton(
              onPressed: () {
                setState(() {
                  borrarJerarquia(empleado, widget.jefe);
                });
                Navigator.of(context).pop(); // Cerrar el diálogo de confirmación
              },
              child: const Text('Eliminar'),
            ),
          ],
        );
      },
    );
  }

  //AÑADIDO PARA ELIMINAR EMPLEADOS
  void borrarJerarquia(Empleado empleado, Jefe suJefe){
    suJefe.eliminaEmpleado(empleado);
    if(empleado is Jefe){
      widget.jefes.remove(empleado);
      for(Empleado sub in empleado.getEquipo()){
        borrarJerarquia(sub, empleado);
      }
    }
    if(empleado is Organizador){
      widget.organizadores.remove(empleado);
    }
  }

}
