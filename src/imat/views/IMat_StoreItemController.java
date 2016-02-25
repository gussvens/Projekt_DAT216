/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.project.Product;

/**
 * FXML Controller class
 *
 * @author Gustav
 */
public class IMat_StoreItemController implements Initializable {
    @FXML private Label itemNameLabel, itemPriceLabel, itemQuantityLabel;
    @FXML private Button addButton;
    @FXML private ImageView itemImage, favorizeStarImage;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setItemNameLabel(String name){
        this.itemNameLabel.setText(name);
    }

    public void setItemPriceLabel(int price){
        this.itemPriceLabel.setText(price + " kr");
    }

    public void setItemImage(Image image){
        this.itemImage.setImage(image);
    }

   /* public IMat_StoreItemController(Product item){
        this.itemNameLabel.setText(item.getName());
        this.itemPriceLabel.setText(item.getPrice() + " kr");
        this.itemQuantityLabel.setText(item.getUnit() + " " + item.getUnitSuffix());
    }
    */
}
