package imat;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Andreas
 */
public class IMat extends Application {

    private static IMat theInstance = new IMat();
    private Stage stage;


    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("views/IMat_Start_v2.fxml"));

        IMat_Model model = new IMat_Model();

        Scene scene = new Scene(root, 1360, 768);

        theInstance.stage = primaryStage;

        primaryStage.setTitle("iMat");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public synchronized static Stage getStage(){
        return theInstance.stage;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
