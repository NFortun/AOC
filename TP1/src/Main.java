import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Platform.exit;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ressources/index.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();

        Scene scene = new Scene(root);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.setOnCloseRequest(e->controller.shutdown());
        stage.show();

    }

    //
//    public static void main(String[] args){
//        GenImpl g = new GenImpl();
//
//        for(int i = 0; i < 4; i++) {
//            Afficheur aff = new Afficheur();
//            Canal c = new Canal(g);
//            g.addObserver(c);
//            c.addObserver(aff);
//        }
//
//        g.generate();
//        g.generate();
//        g.generate();
//        g.generate();
//        g.generate();
//        g.generate();
//        g.generate();
//    }
}
