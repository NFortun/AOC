public interface Generateur {
    public Integer getValue();
    public void generate();
    public void addObserver(ObserverGenerateurAsync o);
    public void notifyObserver();
}
