import java.util.concurrent.Future;

public interface GenerateurAsync {

    public Future<Integer> getValue();
    public void addObserver(ObserverGenerateur o);
}
