import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.ExecutionException;

public class Afficheur implements ObserverGenerateur{

    private IntegerProperty value = new SimpleIntegerProperty(0);

    @Override
    public void update(GenerateurAsync sub) {
        Integer up = 0;
        try {
            up = sub.getValue().get();
            System.out.println(up);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        value.setValue(up);
    }

    public IntegerProperty getValue() {
        return value;
    }
}
