import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Canal implements GenerateurAsync, ObserverGenerateurAsync{

    Generateur gen;

    private List<ObserverGenerateur> obs = new ArrayList<ObserverGenerateur>();

    public Canal(Generateur gen) {
        this.gen = gen;
    }

    @Override
    public Future<Integer> getValue() {
        return Executors.newScheduledThreadPool(1).schedule(()->gen.getValue(), 1000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void notifyObserver() {
        for(ObserverGenerateur o:obs){
            o.update(this);
        }
    }

    @Override
    public void addObserver(ObserverGenerateur o) {
        obs.add(o);
    }

    @Override
    public Future<Void> update(Generateur sub) {
        return Executors.newScheduledThreadPool(1).schedule(() -> {
            notifyObserver();
            return null;
        }, 0, TimeUnit.MILLISECONDS);
    }
}
