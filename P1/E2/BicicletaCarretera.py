from E2.Bicicleta import Bicicleta

class BicicletaCarretera(Bicicleta):
    def __init__(self, id):
        super().__init__(id)

    def pedalear(self):
        print("Corre una bicicleta de carretera de id:", self.id)
