import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ressources/index.fxml"));
        Parent root = loader.load();
        Controlleur controlleur = loader.getController();

        Scene scene = new Scene(root);

        stage.setTitle("My wonderful generator");
        stage.setScene(scene);
        stage.setOnCloseRequest(e-> controlleur.shutdown());
        stage.show();

    }
}
