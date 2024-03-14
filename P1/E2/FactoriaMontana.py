from BicicletaMontana import BicicletaMontana
from CarreraMontana import CarreraMontana
from FactoriaCarreraYBicicleta import FactoriaCarreraYBicicleta

class FactoriaMontana(FactoriaCarreraYBicicleta):
    def crearBicicleta(self, id: int):
        return BicicletaMontana(id)

    def crearCarrera(self):
        return CarreraMontana()
