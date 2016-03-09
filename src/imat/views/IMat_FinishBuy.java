/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.views;

import imat.IMat_Checkout_presenter;
import imat.IMat_Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;



/**
 * FXML Controller class
 *
 */
public class IMat_FinishBuy implements Initializable {
    @FXML
    private TextField checkoutTotPrice1;
    @FXML
    private ScrollPane basketScrollPane1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         checkoutTotPrice1.setText(Double.toString(IMat_Model.getBackEnd().getShoppingCart().getTotal()) + " kr");
         
    }    
    
}
