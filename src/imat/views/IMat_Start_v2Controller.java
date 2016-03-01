/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import imat.IMat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import se.chalmers.ait.dat215.project.Customer;

/**
 * FXML Controller class
 *
 * @author Andreas
 */
public class IMat_Start_v2Controller implements Initializable {

    @FXML private ImageView goToStoreButton;
    @FXML private ImageView goToSavedListButton;
    @FXML private ImageView goToHistoryButton;
    @FXML private ImageView goToSettingsButton;


    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void storeButtonClicked() throws IOException{
        System.out.println("hej");
        Parent store = FXMLLoader.load(getClass().getResource("IMat_Store.fxml"));
        IMat.getStage().setScene(new Scene(store, 1360, 768));
        System.out.println("då");
    }

    @FXML
    private void savedListButtonClicked() throws IOException{
        Parent savedList = FXMLLoader.load(getClass().getResource("IMat_SavedList.fxml"));
        IMat.getStage().setScene(new Scene(savedList, 1360, 768));
    }

    @FXML
    private void historyButtonClicked() throws IOException{
        Parent history = FXMLLoader.load(getClass().getResource("IMat_History.fxml"));
        IMat.getStage().setScene(new Scene(history, 1360, 768));
    }

    @FXML
    private void settingsButtonClicked() throws IOException{
        Parent settings = FXMLLoader.load(getClass().getResource("IMat_Settings.fxml"));
        IMat.getStage().setScene(new Scene(settings, 1360, 768));
    }

}
