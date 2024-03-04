package E1;

public class BicicletaCarretera extends Bicicleta {

    BicicletaCarretera(int id){
        super(id);
    }

    @Override
    public String correr(){
        return "Corre una bicicleta de carretera de id: " + super.id;
    }

}
