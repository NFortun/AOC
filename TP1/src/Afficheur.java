import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.ExecutionException;

public class Afficheur implements ObserverGenerateur{

    private Integer value = 0;

    @Override
    public void update(GenerateurAsync sub) {
        try {
            value = sub.getValue().get();
            System.out.println(value);
        } catch (InterruptedException e) {
            System.err.println("Interruption");
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.err.println("Erreur à l'exécution");
            e.printStackTrace();
        }
    }

    Integer getValue() {
        return value;
    }
}
