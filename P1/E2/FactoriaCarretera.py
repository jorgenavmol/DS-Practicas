from E2.Bicicleta import BicicletaCarretera
from E2.Carrera import CarreraCarretera
from E2.FactoriaCarreraYBicicleta import FactoriaCarreraYBicicleta

class FactoriaCarretera(FactoriaCarreraYBicicleta):
    def crear_bicicleta(self, id: int):
        return BicicletaCarretera(id)

    def crear_carrera(self):
        return CarreraCarretera()
