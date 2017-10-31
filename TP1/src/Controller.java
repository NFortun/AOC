import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller  implements Initializable{

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

    private Afficheur aff1;
    private Afficheur aff2;
    private Afficheur aff3;
    private Afficheur aff4;

    private Canal canal1;
    private Canal canal2;
    private Canal canal3;
    private Canal canal4;

    private Generateur gen;
    private AlgoDiffusion atomic;
    private AlgoDiffusion sequential;
    private ScheduledExecutorService threadPoolExecutor;

    private boolean running;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Initialisation
        gen = new GenImpl();
        atomic = new DiffusionAtomique();
        sequential = new DiffusionSequentielle();
        gen.setDiffusion(atomic);
        running = false;

        //Linkage des afficheurs
        aff1 = new Afficheur();
        canal1 = new Canal(gen);
        canal1.addObserver(aff1);
        gen.addObserver(canal1);

        aff2 = new Afficheur();
        canal2 = new Canal(gen);
        canal2.addObserver(aff2);
        gen.addObserver(canal2);

        aff3 = new Afficheur();
        canal3 = new Canal(gen);
        canal3.addObserver(aff3);
        gen.addObserver(canal3);

        aff4 = new Afficheur();
        canal4 = new Canal(gen);
        canal4.addObserver(aff4);
        gen.addObserver(canal4);

        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.millis(100),
                event -> {
                    lab1.setText(aff1.getValue().toString());
                    lab2.setText(aff2.getValue().toString());
                    lab3.setText(aff3.getValue().toString());
                    lab4.setText(aff4.getValue().toString());
                }
        ));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();

        //group les radio buttons
        ToggleGroup group = new ToggleGroup();
        atomicRadio.setToggleGroup(group);
        sequentialRadio.setToggleGroup(group);
        atomicRadio.setSelected(true);


        atomicRadio.setOnMouseClicked(event -> select(atomicRadio, sequentialRadio, gen, atomic));
        sequentialRadio.setOnMouseClicked(event -> select(sequentialRadio, atomicRadio, gen, sequential));

        start.setOnMouseClicked(event -> launchGeneration());
        stop.setOnMouseClicked(event -> stopGeneration());

    }

    private void launchGeneration() {
        if (!running/*threadPoolExecutor == null || threadPoolExecutor.isShutdown()*/) {
            threadPoolExecutor = Executors.newScheduledThreadPool(1);
            threadPoolExecutor.scheduleAtFixedRate(
                    () -> gen.generate()
                    , 0, 1000, TimeUnit.MILLISECONDS);
            running = true;
            atomicRadio.setDisable(true);
            sequentialRadio.setDisable(true);
        }
    }

    private void stopGeneration(){
        if(running/*threadPoolExecutor != null && !threadPoolExecutor.isShutdown()*/) {
            threadPoolExecutor.shutdown();
            running = false;
            atomicRadio.setDisable(false);
            sequentialRadio.setDisable(false);
        }
    }

    private void select(RadioButton selectedRadio, RadioButton diselectRadio, Generateur gen, AlgoDiffusion diffusion){
        if(threadPoolExecutor == null || threadPoolExecutor.isShutdown()) {
            gen.setDiffusion(diffusion);
        }

    }

    void shutdown(){
        if(threadPoolExecutor != null) {
            if(!threadPoolExecutor.isShutdown()) threadPoolExecutor.shutdown();
            try {
                threadPoolExecutor.awaitTermination(1000,TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            canal1.shutdown();
            canal2.shutdown();
            canal3.shutdown();
            canal4.shutdown();
        }
    }
}
