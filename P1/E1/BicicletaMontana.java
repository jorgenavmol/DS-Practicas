package E1;

public class BicicletaMontana extends Bicicleta {

    BicicletaMontana(int id){
        super(id);
    }

    @Override
    public void pedalear(){
        System.out.println("Corre una bicicleta de montaña de id: " + super.id); 
    }
}
