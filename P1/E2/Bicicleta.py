from abc import ABC, abstractmethod #PARA DEFINIR CLASES ABSTRACTAS EN PYTHON.
from E2.Prototipo import Prototipo

class Bicicleta(Prototipo):
    def __init__(self, id):
        self.id = id

    @abstractmethod
    def pedalear(self):
        pass

    def clonar(self):
        pass
        
