import java.util.concurrent.ExecutionException;

public class Afficheur implements ObserverGenerateur{


    public Afficheur() {}

    @Override
    public void update(GenerateurAsync sub) {
        try {
            System.out.println(sub.getValue().get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
