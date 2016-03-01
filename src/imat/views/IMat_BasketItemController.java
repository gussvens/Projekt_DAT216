/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import se.chalmers.ait.dat215.project.ShoppingCart;

/**
 * FXML Controller class
 *
 * @author Andreas
 */
public class IMat_BasketItemController implements Initializable {

    ShoppingCart sC;
    
    @FXML
    private Label basketProdName;
    @FXML
    private Label basketProdPrice;
    @FXML
    private Label basketProdQuant;
    @FXML
    private Button basketRemove;
      @FXML
    private TextField totPriceLabel;
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setItemNameLabel(String name) {
        this.basketProdName.setText(name);
    }

    public void setItemPriceLabel(double price) {
        this.basketProdPrice.setText(Double.toString(price));
    }


    public void setItemQuantity(String quant) {
        basketProdQuant.setText(quant);
    }
    
    public void setPayButton(Boolean bool){
        
    }

    public void setPayTotal(double sum){
        totPriceLabel.setText(Double.toString(sum));
    }

    
    
    
}
