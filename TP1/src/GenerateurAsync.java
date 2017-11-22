import java.util.concurrent.Future;

interface GenerateurAsync {

    Future<Integer> getValue();
    void addObserver(ObserverGenerateur o);
}
