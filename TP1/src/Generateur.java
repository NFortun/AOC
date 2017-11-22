interface Generateur {
    /**
     * Fournit la valeur actuelle du générateur
     * @return la valeur
     */
    Integer getValue();

    /**
     * Génère une nouvelle valeur
     */
    void generate();

    /**
     * Ajoute un observer de générateur asynchrone
     * @param o l'observer à ajouter
     */
    void addObserver(ObserverGenerateurAsync o);

    /**
     * Notifie les observer asynchrone du générateur
     */
    void notifyObserver();

    /**
     * Change la méthode de diffusion
     * @param algoDiffusion algorithme de diffusion à appliquer
     */
    void setDiffusion(AlgoDiffusion algoDiffusion);
}
