from abc import ABC, abstractmethod #PARA DEFINIR CLASES ABSTRACTAS EN PYTHON.
from typing import List
from Bicicleta import Bicicleta

class Carrera(ABC):
    def __init__(self):
        self.bicicletas = []
        self.nombre: str = ""
        self.instanteRetirada: int = 4

    def setNombre(self, nombre: str):
        self.nombre = nombre

    @abstractmethod
    def run(self):
        pass

    @abstractmethod
    def aniadeBicicleta(self, bici: Bicicleta):
        pass

    @abstractmethod
    def retirada(self):
        pass