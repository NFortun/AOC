import java.util.List;
import java.util.concurrent.ExecutionException;

public class DiffusionAtomique implements AlgoDiffusion{
    private List<ObserverGenerateurAsync> observers;
    private  Generateur gen;

    @Override
    public void configure(List<ObserverGenerateurAsync> list, Generateur gen) {
        this.observers = list;
        this.gen = gen;

    }

    @Override
    public void execute() {
        for(ObserverGenerateurAsync o: observers) {
            try {
                o.update(gen).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }
}
