from abc import ABC, abstractmethod #PARA DEFINIR CLASES ABSTRACTAS EN PYTHON.
from typing import List
from Bicicleta import Bicicleta

class Carrera(ABC):
    def __init__(self):
        self.bicicletas: List[Bicicleta] = []
        self.nombre: str = ""
        self.instanteRetirada: int = 4

    def setNombre(self, nombre: str):
        self.nombre = nombre

    @abstractmethod
    def aniadeBicicleta(self, bici: Bicicleta):
        pass

    @abstractmethod
    def retirada(self):
        pass