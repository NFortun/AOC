import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

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

    private Generateur gen;

    private Afficheur afficheur;

    private AlgoDiffusion atomique;

    private AlgoDiffusion sequential;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        aff1 = new Label();
        Generateur gen = new GenImpl();
        atomique = new DiffusionAtomique();
        sequential = new DiffusionSequentielle();
        atomicRadio.setSelected(true);
        afficheur = new Afficheur();
        Canal canal = new Canal(gen);
        canal.addObserver(afficheur);
        gen.addObserver(canal);
        gen.setDiffusion(atomique);

        atomicRadio.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!atomicRadio.isSelected()) {
                    sequentialRadio.setSelected(false);
                    gen.setDiffusion(atomique);
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

        start.setOnMouseClicked(event -> gen.generate());





    }
}
