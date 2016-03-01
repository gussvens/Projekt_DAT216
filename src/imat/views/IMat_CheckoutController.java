/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.views;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import imat.IMat_Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;

/**
 *
 * @author Andreas
 */
public class IMat_CheckoutController implements Initializable {

    @FXML
    private ScrollPane checkoutScrollPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    // Sets the basket inside checkout.
    public void setScrollPane(FlowPane fP){
        checkoutScrollPane.setContent(fP);
    }
    
    
}
