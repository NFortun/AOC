import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class GenImpl implements Generateur {

    private Integer value = 0;
    private List<ObserverGenerateurAsync> obs = new ArrayList<ObserverGenerateurAsync>();

    @Override
    public Integer getValue() {
        return value;
    }


    @Override
    public void notifyObserver() {
        for(ObserverGenerateurAsync o:obs) {
            try {
                o.update(this).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void generate() {
        //value = new Random().nextInt();
        value++;
        notifyObserver();
    }

    @Override
    public void addObserver(ObserverGenerateurAsync o) {
        obs.add(o);
    }
}
