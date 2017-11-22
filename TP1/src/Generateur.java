interface Generateur {
    Integer getValue();
    void generate();
    void addObserver(ObserverGenerateurAsync o);
    void notifyObserver();
    void setDiffusion(AlgoDiffusion algoDiffusion);
}
