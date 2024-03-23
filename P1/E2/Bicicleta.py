from abc import ABC, abstractmethod #PARA DEFINIR CLASES ABSTRACTAS EN PYTHON.
from Prototipo import Prototipo

class Bicicleta(Prototipo):
    def __init__(self, id):
        self.id = id

    @abstractmethod
    def pedalear(self):
        pass

    @abstractmethod
    def clonar(self):
        pass
    
    def setId(self, id):
        self.id = id
        
