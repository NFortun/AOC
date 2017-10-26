import java.util.List;

public interface AlgoDiffusion {
    public void configure(List<ObserverGenerateurAsync> list, Generateur gen);
    public void execute();
}
