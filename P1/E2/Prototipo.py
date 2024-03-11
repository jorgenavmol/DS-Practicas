from typing import Protocol
from abc import abstractmethod

class InterfazClonable(Protocol):
    @abstractmethod
    def clonar(self):
        pass