/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.views;

import imat.IMat;
import imat.IMat_Model;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Andreas
 */
public class IMat_EndScreenController implements Initializable {

    private final String PANE_DEFAULT_COLOR = "-fx-background-color: #FDFDFD;";
    private final String PANE_ENTER_COLOR = "-fx-background-color:  #bdfbec;";
    private final String PANE_CLICKED_COLOR = "-fx-background-color:  #adebdc;";

    @FXML
    private Label date;
    @FXML
    private Label time;
    @FXML
    private Pane confButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        date.setText(IMat_DeliveryController.getStaticDate());
        time.setText(IMat_DeliveryController.getStaticTime());
        
        // ConfirmButton
        confButton.setOnMouseClicked(onDoneClicked);
        confButton.setOnMouseEntered(paneEnter);
        confButton.setOnMouseExited(paneExit);
        confButton.setCursor(Cursor.HAND);
    }

    EventHandler<MouseEvent> onDoneClicked
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    try {
                        Parent start = FXMLLoader.load(getClass().getResource("IMat_Store_v2.fxml"));
                        IMat.getStage().setScene(new Scene(start, 1360, 768));
                    } catch (IOException ex) {
                        Logger.getLogger(IMat_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };

    EventHandler<MouseEvent> paneEnter
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    ((Pane) t.getSource()).setStyle(PANE_ENTER_COLOR);
                }
            };

    EventHandler<MouseEvent> paneExit
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    ((Pane) t.getSource()).setStyle(PANE_DEFAULT_COLOR);
                }
            };
}
