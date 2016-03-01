/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.views;

import imat.IMat;
import imat.IMat_Checkout_presenter;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;

/**
 * FXML Controller class
 *
 * @author Andreas
 */
public class IMat_CheckOut_v2Controller implements Initializable {

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField city;
    @FXML
    private TextField address;
    @FXML
    private TextField zipCode;
    @FXML
    private TextField card1;
    @FXML
    private TextField card2;
    @FXML
    private TextField card3;
    @FXML
    private TextField card4;
    @FXML
    private TextField cvc;
    @FXML
    private TextArea comment;
    @FXML
    private ComboBox payment;
    @FXML
    private ComboBox cardType;
    @FXML
    private ComboBox deliveryDay;
    @FXML
    private ScrollPane basketScrollPane;
    @FXML
    private Button homeButton;
    @FXML
    private Button backToStore;
    @FXML
    private TextField checkoutTotPrice;

    // Had to have this to be able to delete products from this view.
    private static IMat_Checkout_presenter pres;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pres = new IMat_Checkout_presenter(
                basketScrollPane,
                checkoutTotPrice
        );
        
        
        homeButton.setOnMouseClicked(homeButtonClicked);
        backToStore.setOnMouseClicked(backToStoreClicked);
        pres.updateScrollPane();
    }

    public void updateTotPrice() {
        checkoutTotPrice.setText(Double.toString(IMat_Model.getBackEnd().getShoppingCart().getTotal()) + " kr");
    }
    
    
    
    /*
    // Sets the basket.
    public void updateBasket() {
        FlowPane flowPane = new FlowPane();
        flowPane.setVgap(6);
        flowPane.setHgap(6);
        flowPane.setPrefWidth(255);

        for (ShoppingItem s : IMat_Model.getBackEnd().getShoppingCart().getItems()) {
            try {
                Product p = s.getProduct();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("IMat_BasketItem.fxml"));
                Node storeItem = loader.load();
                IMat_BasketItemController controller = loader.getController();
                controller.setItemNameLabel(p.getName());
                controller.setItemPriceLabel(p.getPrice());
                controller.setItemQuantity(p.getUnit());
                controller.setShoppingItem(s);
                flowPane.getChildren().add(storeItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        basketScrollPane.setContent(flowPane);
        checkoutTotPrice.setText(Double.toString(IMat_Model.getBackEnd().getShoppingCart().getTotal()) + " kr");
    }
*/
    // Back to start
    EventHandler<MouseEvent> homeButtonClicked
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    try {
                        Parent start = FXMLLoader.load(getClass().getResource("IMat_Start_v2.fxml"));
                        IMat.getStage().setScene(new Scene(start, 1360, 768));
                    } catch (IOException ex) {
                        Logger.getLogger(IMat_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };

    // Back to store
    EventHandler<MouseEvent> backToStoreClicked
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    try {
                        Parent start = FXMLLoader.load(getClass().getResource("IMat_Store.fxml"));
                        IMat.getStage().setScene(new Scene(start, 1360, 768));
                    } catch (IOException ex) {
                        Logger.getLogger(IMat_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };

    public static IMat_Checkout_presenter getPresenter(){
        return pres;
    }
    
}