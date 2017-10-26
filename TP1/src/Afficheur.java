import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.ExecutionException;

public class Afficheur implements ObserverGenerateur{


    public Afficheur() {}

    @Override
    public void update(GenerateurAsync sub) {
        try {
            System.out.println(sub.getValue().get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
