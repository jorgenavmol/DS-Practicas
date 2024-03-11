from abc import ABC, abstractmethod #PARA DEFINIR CLASES ABSTRACTAS EN PYTHON.

class Bicicleta(ABC):
    def __init__(self, id):
        self.id = id

    @abstractmethod
    def pedalear(self):
        pass
