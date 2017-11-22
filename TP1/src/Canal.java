import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Canal implements GenerateurAsync, ObserverGenerateurAsync{

    private int latency;
    private Generateur gen;

    private ObserverGenerateur obs;

    private ScheduledExecutorService getval;
    private ScheduledExecutorService upd;

    Canal(Generateur gen, int latency) {
        this.gen = gen;
        this.latency = latency;
    }

    /**
     * Donne la valeur du générateur en simulant l'asynchronisme
     * @return Le Future de la valeur du générateur
     */
    @Override
    public Future<Integer> getValue() {
        if(getval == null) getval = Executors.newScheduledThreadPool(1);
        return getval.schedule(()->gen.getValue(), latency, TimeUnit.MILLISECONDS);
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
        }, latency, TimeUnit.MILLISECONDS);
    }

    /**
     * Met fin à l'activité des Threads
     */
    public void shutdown(){
        if(getval != null) getval.shutdownNow();
        if(upd != null) upd.shutdownNow();
    }
}
