import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Canal implements GenerateurAsync, ObserverGenerateurAsync{

    Generateur gen;

    private ObserverGenerateur obs;

    public Canal(Generateur gen) {
        this.gen = gen;
    }

    @Override
    public Future<Integer> getValue() {
        return Executors.newScheduledThreadPool(1).schedule(()->gen.getValue(), 1000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void addObserver(ObserverGenerateur o) {
        obs = o;
    }

    @Override
    public Future<Void> update(Generateur sub) {
        return Executors.newScheduledThreadPool(1).schedule(() -> {
            obs.update(this);
            return null;
        }, 1000, TimeUnit.MILLISECONDS);
    }
}
