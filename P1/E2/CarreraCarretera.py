from Carrera import Carrera
from random import Random
from time import sleep

class CarreraCarretera(Carrera):
    def __init__(self):
        super().__init__()

    def aniadeBicicleta(self, bici):
        self.bicicletas.append(bici)

    def run(self):
        contador = 0
        try:
            while contador < 60:
                contador += 1
                if contador == self.instanteRetirada:
                    self.retirada()
                for bicicleta in self.bicicletas:
                    bicicleta.pedalear()
                sleep(1)  # Simula una iteración de la carrera cada segundo

                if contador == 60:
                    print(self.nombre + " finalizada.")

        except KeyboardInterrupt:
            print(self.nombre + " finalizada.")

    def retirada(self):
        N = len(self.bicicletas)
        # Coge la parte entera de la multiplicación, El tamaño de entrada minimo es 10
        retiradas = int(N * 0.1)
        random = Random()

        for i in range(retiradas):
            rand = random.randint(0, len(self.bicicletas) - 1)
            print("Se retira la bicicleta de carretera:", self.bicicletas[rand].id)
            self.bicicletas.pop(rand)
