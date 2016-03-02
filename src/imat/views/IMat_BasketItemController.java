/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.views;

import com.sun.org.apache.xerces.internal.util.DOMUtil;
import imat.IMat_Model;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;

/**
 * FXML Controller class
 *
 * @author Andreas
 */
public class IMat_BasketItemController implements Initializable {

    @FXML
    private Label basketProdName;
    @FXML
    private Label basketProdPrice;
    @FXML
    private Label basketProdQuant;
    @FXML
    private Button basketRemove;
    @FXML
    private ScrollPane basketScrollPane;
    @FXML
    private Label nrOfBasketItems;
    @FXML
    private TextField nrOfProducts;
    @FXML
    private Button incAmount;
    @FXML
    private Button decAmount;

    private ShoppingItem sI;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        basketRemove.setOnMouseClicked(removeObject);
        incAmount.setOnMouseClicked(incrementAmount);
        decAmount.setOnMouseClicked(decrementAmount);
    }

    // Removes the shoppigItem from the cart and the flowpane.
    EventHandler<MouseEvent> removeObject
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    IMat_Model.getBackEnd().getShoppingCart().removeItem(sI);
                    IMat_FXMLController.getPresenter().updateBasket();

                    if (IMat_CheckOut_v2Controller.getPresenter() != null) {
                        IMat_CheckOut_v2Controller.getPresenter().updateScrollPane();
                    }
                }
            };

    /*
    Clicking on increment or decrement amount should dynamically change
    its value. This doesn't work yet.
     */

    EventHandler<MouseEvent> incrementAmount
            = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            sI.setAmount(sI.getAmount() + 1);
        }
    };

    EventHandler<MouseEvent> decrementAmount
            = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            sI.setAmount(sI.getAmount() - 1);
        }
    };

    public void setItemNameLabel(String name) {
        this.basketProdName.setText(name);
    }

    public void setItemPriceLabel(double price) {
        this.basketProdPrice.setText(price + "");
    }

    public void setItemQuantity(String quant) {
        basketProdQuant.setText(quant);
    }

    public void setShoppingItem(ShoppingItem sI) {
        this.sI = sI;
    }

    public void setNrOfBasketItems(double amount){
        this.nrOfBasketItems.setText(amount + "");
        //Supposed to dynamically change with .getAmount()
        this.nrOfProducts.setText(amount + "");
    }

  /*  public void setNrOfBasketItems() {

        List<ShoppingItem> sIList = IMat_Model.getBackEnd().getShoppingCart().getItems();

        int i = 1;
        for (ShoppingItem s : sIList) {
            if (s.getProduct().getName().equals(basketProdName.getText())) {
                i++;
            }
        }

        nrOfBasketItems.setText(Integer.toString(i));

    }
*/
}
