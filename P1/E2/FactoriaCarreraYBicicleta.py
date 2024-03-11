from abc import ABC, abstractmethod #PARA DEFINIR CLASES ABSTRACTAS EN PYTHON.
from typing import Protocol #PARA DEFINIR INTERFACES EN PYTHON.
from E2.Bicicleta import Bicicleta
from E2.Carrera import Carrera

class FactoriaCarreraYBicicleta(Protocol):
    @abstractmethod
    def crear_bicicleta(self, id: int) -> Bicicleta: # "-> Bicicleta" significa que se devolvera un objeto Bicicleta.
        pass
    
    @abstractmethod
    def crear_carrera(self) -> Carrera:
        pass