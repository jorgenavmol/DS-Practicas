from E2.Bicicleta import Bicicleta

class BicicletaMontana(Bicicleta):
    def __init__(self, id):
        super().__init__(id)

    def pedalear(self):
        print("Corre una bicicleta de montaña de id:", self.id)
