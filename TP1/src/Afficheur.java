import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.concurrent.ExecutionException;

public class Afficheur implements ObserverGenerateur {

    private Label label;

    public Afficheur(Label label) {
        this.label = label;
    }

    @Override
    public void update(GenerateurAsync sub) {
        final String value;
        try {
            //Récupère la valeur
            value = sub.getValue().get() + "";

            //Ajoute l'actualisation du label dans le thread JavaFx
            Platform.runLater(() -> label.setText(value));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


    }

}
