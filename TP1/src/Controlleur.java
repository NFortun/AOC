import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controlleur implements Initializable{

    @FXML
    private Label lab1;
    @FXML
    private Label lab2;
    @FXML
    private Label lab3;
    @FXML
    private Label lab4;

    @FXML
    private RadioButton atomicRadio;
    @FXML
    private RadioButton sequentialRadio;

    @FXML
    private Button start;
    @FXML
    private Button stop;

    private Canal canal1;
    private Canal canal2;
    private Canal canal3;
    private Canal canal4;

    private Generateur gen;
    private AlgoDiffusion atomic;
    private AlgoDiffusion sequential;
    private ScheduledExecutorService threadPoolExecutor;

    private boolean running;

    /**
     * Initialise le générateur, les algorithmes de diffusion
     * ainsi que les afficheurs et les canaux simulant l'asynchronisme.
     *
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Initialisation
        gen = new GenImpl();
        atomic = new DiffusionAtomique();
        sequential = new DiffusionSequentielle();
        gen.setDiffusion(atomic);
        running = false;

        //Linkage des afficheurs
        Afficheur aff1 = new Afficheur(lab1);
        canal1 = new Canal(gen, 0);
        canal1.addObserver(aff1);
        gen.addObserver(canal1);

        Afficheur aff2 = new Afficheur(lab2);
        canal2 = new Canal(gen, 100);
        canal2.addObserver(aff2);
        gen.addObserver(canal2);

        Afficheur aff3 = new Afficheur(lab3);
        canal3 = new Canal(gen, 500);
        canal3.addObserver(aff3);
        gen.addObserver(canal3);

        Afficheur aff4 = new Afficheur(lab4);
        canal4 = new Canal(gen, 1000);
        canal4.addObserver(aff4);
        gen.addObserver(canal4);

        //group les radio buttons
        ToggleGroup group = new ToggleGroup();
        atomicRadio.setToggleGroup(group);
        sequentialRadio.setToggleGroup(group);
        atomicRadio.setSelected(true);

        //Mise en place des Listener
        atomicRadio.setOnMouseClicked(event -> select(gen, atomic));
        sequentialRadio.setOnMouseClicked(event -> select(gen, sequential));

        start.setOnMouseClicked(event -> launchGeneration());
        stop.setOnMouseClicked(event -> stopGeneration());

    }

    /**
     * Lance la génération de valeur à interval régulier
     */
    private void launchGeneration() {
        if (!running) { // Si le générateur n'est pas déjà lancé
            // Initialise un nouveau scheduler
            threadPoolExecutor = Executors.newScheduledThreadPool(1);
            // Lance la génération de valeur toute les secondes
            threadPoolExecutor.scheduleAtFixedRate(
                    gen::generate
                    , 0, 1000, TimeUnit.MILLISECONDS);
            running = true;
        }
    }

    /**
     * Stop la génération de valeur à interval régulier
     */
    private void stopGeneration(){
        if(running) { // Si le générateur est lancé
            // Arrête le scheduler
            threadPoolExecutor.shutdown();
            running = false;
        }
    }

    /**
     * Change l'algorithme de diffusion
     * @param gen le générateur qui doit appliquer l'algorithme
     * @param diffusion l'algorithme à appliquer
     */
    private void select(Generateur gen, AlgoDiffusion diffusion){
         gen.setDiffusion(diffusion);
    }

    /**
     * Arrête l'ensemble des Thread avant de lui-même s'éteindre
     */
    void shutdown(){
        if(threadPoolExecutor != null) { // Si le threadPool a été instantié au moins une fois
            // Le désactive si ce n'est pas déjà le cas
            if(!threadPoolExecutor.isShutdown()) threadPoolExecutor.shutdownNow();

            // Attends 1 seconde que le Thread finisse
            try {
                threadPoolExecutor.awaitTermination(1000,TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Ferme les différents canaux
            canal1.shutdown();
            canal2.shutdown();
            canal3.shutdown();
            canal4.shutdown();
        }
    }
}
