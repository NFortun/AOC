import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ressources/index.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
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
