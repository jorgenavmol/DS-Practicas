from abc import ABC, abstractmethod #PARA DEFINIR CLASES ABSTRACTAS EN PYTHON.
from typing import List
from E2.Bicicleta import Bicicleta

class Carrera(ABC, Runnable):
    def __init__(self):
        self.bicicletas: List[Bicicleta] = []
        self.nombre: str = ""
        self.instanteRetirada: int = 4

    def set_nombre(self, nombre: str):
        self.nombre = nombre

    @abstractmethod
    def aniade_bicicleta(self, bici: Bicicleta):
        pass

    @abstractmethod
    def retirada(self):
        pass