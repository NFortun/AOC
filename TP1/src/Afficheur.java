import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class Afficheur implements ObserverGenerateur{

    private Label label;

    public Afficheur(Label label) {
        this.label = label;
    }

    @Override
    public void update(GenerateurAsync sub) {
        final String value;
        try {
            value = sub.getValue().get() + "";
            System.out.println(value);
            Platform.runLater(() -> {
                label.setText(value);
//            try {
//                value.setValue(sub.getValue().get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//                Logger.getGlobal().severe("Interruption lors de l'exécution");
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//                Logger.getGlobal().severe("Interruption lors de l'exécution");
//            }
            });
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


    }

}
