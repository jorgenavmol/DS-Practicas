from abc import ABC, abstractmethod #PARA DEFINIR CLASES ABSTRACTAS EN PYTHON.
from typing import Protocol #PARA DEFINIR INTERFACES EN PYTHON.
from Bicicleta import Bicicleta
from Carrera import Carrera

class FactoriaCarreraYBicicleta(Protocol):
    @abstractmethod
    def crearBicicleta(self, id: int) -> Bicicleta: # "-> Bicicleta" significa que se devolvera un objeto Bicicleta.
        pass
    
    @abstractmethod
    def crearCarrera(self) -> Carrera:
        pass