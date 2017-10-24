import java.util.concurrent.TimeUnit;

public interface Scheduler {
    public Future<Integer> schedule(Callable<Integer> c, Integer delay, TimeUnit timeUnit);
}
