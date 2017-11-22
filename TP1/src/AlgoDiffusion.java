import java.util.List;

interface AlgoDiffusion {

    /**
     * Permet de configurer l'algorithme en indiquant le générateur et ses observateurs
     * @param list liste des observateurs du générateur
     * @param gen le générateur qui appel l'algorithme
     */
    void configure(List<ObserverGenerateurAsync> list, Generateur gen);

    /**
     * Exécute l'algorithme de diffusion
     */
    void execute();
}
