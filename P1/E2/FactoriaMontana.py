from E2.Bicicleta import BicicletaMontana
from E2.Carrera import CarreraMontana
from E2.FactoriaCarreraYBicicleta import FactoriaCarreraYBicicleta

class FactoriaMontana(FactoriaCarreraYBicicleta):
    def crear_bicicleta(self, id: int):
        return BicicletaMontana(id)

    def crear_carrera(self):
        return CarreraMontana()
