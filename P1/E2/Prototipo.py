from typing import Protocol
from abc import abstractmethod

class Prototipo(Protocol):
    @abstractmethod
    def clonar(self):
        pass