import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Canal implements GenerateurAsync, ObserverGenerateurAsync{

    private Generateur gen;

    private ObserverGenerateur obs;

    private ScheduledExecutorService getval;
    private ScheduledExecutorService upd;

    private Random rand = new Random();

    Canal(Generateur gen) {
        this.gen = gen;
    }

    @Override
    public Future<Integer> getValue() {
        if(getval == null) getval = Executors.newScheduledThreadPool(1);
        return getval.schedule(()->gen.getValue(), rand.nextInt() % 5000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void addObserver(ObserverGenerateur o) {
        obs = o;
    }

    @Override
    public Future<Void> update(Generateur sub) {
        if(upd == null) upd = Executors.newScheduledThreadPool(1);
        return upd.schedule(() -> {
            obs.update(this);
            return null;
        }, rand.nextInt() % 5000, TimeUnit.MILLISECONDS);
    }

    void shutdown(){
        if(getval != null) getval.shutdown();
        if(upd != null) upd.shutdown();
    }
}
