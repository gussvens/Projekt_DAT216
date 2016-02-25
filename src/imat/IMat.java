/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
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

        theInstance.stage = primaryStage;

        Scene scene = new Scene(root, 1600, 1000);
        
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
