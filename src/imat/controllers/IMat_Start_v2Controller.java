/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Andreas
 */
public class IMat_Start_v2Controller implements Initializable {

   
    private Stage stage;
    private Parent parent;
    
    @FXML
    private ImageView goToStoreButton;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        goToStoreButton.setOnMouseClicked(toStoreClicked);
    }  
    
    EventHandler<MouseEvent> toStoreClicked
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                   stage = (Stage) goToStoreButton.getScene().getWindow();
                    try {   
                        parent =  FXMLLoader.load(getClass().getResource("IMat_Store.fxml"));
                        imat.IMat_presenter.setStage(stage, parent);
                    } catch (IOException ex) {
                        System.out.println("Fel i startcontrollern.");;
                    }
                }
            };
    
}
