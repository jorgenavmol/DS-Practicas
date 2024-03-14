from BicicletaCarretera import BicicletaCarretera
from CarreraCarretera import CarreraCarretera
from FactoriaCarreraYBicicleta import FactoriaCarreraYBicicleta

class FactoriaCarretera(FactoriaCarreraYBicicleta):
    def crearBicicleta(self, id: int):
        return BicicletaCarretera(id)

    def crearCarrera(self):
        return CarreraCarretera()
