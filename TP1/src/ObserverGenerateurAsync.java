import java.util.concurrent.Future;

interface ObserverGenerateurAsync {
    Future<Void> update(Generateur sub);
}
