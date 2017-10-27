import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller  implements Initializable{

    @FXML
    private Label aff1;
    @FXML
    private Label aff2;
    @FXML
    private Label aff3;
    @FXML
    private Label aff4;

    @FXML
    private RadioButton atomicRadio;

    @FXML
    private RadioButton sequentialRadio;

    @FXML
    private Button start;
    @FXML
    private Button stop;

    private Generateur gen;

    private Afficheur afficheur;

    private AlgoDiffusion atomic;

    private AlgoDiffusion sequential;

    private ScheduledExecutorService threadPoolExecutor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gen = new GenImpl();
        atomic = new DiffusionAtomique();
        sequential = new DiffusionSequentielle();
        afficheur = new Afficheur();
        Canal canal = new Canal(gen);
        canal.addObserver(afficheur);
        gen.addObserver(canal);
        gen.setDiffusion(atomic);

        atomicRadio.setSelected(true);


        atomicRadio.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!atomicRadio.isSelected()) {
                    sequentialRadio.setSelected(false);
                    gen.setDiffusion(atomic);
                }
            }
        });
        sequentialRadio.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(sequentialRadio.isSelected()) {
                    atomicRadio.setSelected(false);
                    gen.setDiffusion(sequential);
                }
            }
            });

        start.setOnMouseClicked(event -> launchGeneration());
        stop.setOnMouseClicked(event -> stopGeneration());





    }

    private void launchGeneration() {
        if (threadPoolExecutor.isShutdown()) {
            threadPoolExecutor = Executors.newScheduledThreadPool(1);
            threadPoolExecutor.scheduleAtFixedRate(() -> {
                gen.generate();
            }, 0, 1000, TimeUnit.MILLISECONDS);
        }
    }

    private void stopGeneration(){
        if(!threadPoolExecutor.isShutdown()) threadPoolExecutor.shutdown();

    }
}
