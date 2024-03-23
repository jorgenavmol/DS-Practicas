# Archivo: E2/main.py

import sys
from time import sleep
from FactoriaCarretera import FactoriaCarretera
from FactoriaMontana import FactoriaMontana

def main():
    if len(sys.argv) != 2:
        print("El numero de argumentos tiene que ser 1 (Numero de bicicletas en las carreras).")
        sys.exit(1)

    N = int(sys.argv[1])

    factoria_carretera = FactoriaCarretera()
    factoria_montana = FactoriaMontana()

    #Hi
    carrera_carretera = factoria_carretera.crearCarrera()
    carrera_montana = factoria_montana.crearCarrera()

    for i in range(N):
        bici = factoria_carretera.crearBicicleta(i)
        carrera_carretera.aniadeBicicleta(bici)

    for i in range(N, 2*N):
        bici = factoria_montana.crearBicicleta(i)
        carrera_montana.aniadeBicicleta(bici)

    carrera_montana.setNombre("Carrera de montaña")
    carrera_carretera.setNombre("Carrera de carretera")

    try:
        # Hacer que el programa principal espere 10 segundos
        carrera_carretera.run()
        carrera_montana.run()
    except KeyboardInterrupt:
        # Detener las carreras después de 10 segundos si se interrumpe con Ctrl+C
        print("Alguna de las carreras se ha finalizado de forma manual.")

if __name__ == "__main__":
    main()
