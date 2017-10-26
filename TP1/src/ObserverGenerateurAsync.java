import java.util.concurrent.Future;

public interface ObserverGenerateurAsync {
    public Future<Void> update(Generateur sub);
}
