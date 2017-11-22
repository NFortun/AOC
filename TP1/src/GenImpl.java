import java.util.ArrayList;
import java.util.List;

public class GenImpl implements Generateur {

    private Integer value = 0;
    private List<ObserverGenerateurAsync> obs = new ArrayList<ObserverGenerateurAsync>();
    private AlgoDiffusion diffusion;

    @Override
    public Integer getValue() {
        return value;
    }


    @Override
    public void notifyObserver() {
        diffusion.configure(obs, this);
        diffusion.execute();
    }

    @Override
    public void generate() {
        //value = new Random().nextInt();
        value++;
        notifyObserver();
    }

    @Override
    public void addObserver(ObserverGenerateurAsync o) {
        obs.add(o);
    }

    @Override
    public void setDiffusion(AlgoDiffusion algoDiffusion){
        this.diffusion = algoDiffusion;
    }
}
