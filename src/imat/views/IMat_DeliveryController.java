package imat.views;

import imat.IMat;
import imat.IMat_Checkout_presenter;
import imat.IMat_Model;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import se.chalmers.ait.dat215.project.CartEvent;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingCartListener;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Emil on 09/03/2016.
 */
public class IMat_DeliveryController implements Initializable, ShoppingCartListener{
    @FXML
    private Button doneButton;
    @FXML
    private ScrollPane basketScrollPane;
    @FXML
    private Pane backButton;
    @FXML
    private TextField checkoutTotPrice;

    private static IMat_Checkout_presenter pres;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pres = new IMat_Checkout_presenter(
                basketScrollPane,
                checkoutTotPrice
        );

        IMat_Model.getBackEnd().getShoppingCart().addShoppingCartListener(this);

        doneButton.setOnMouseClicked(doneButtonClicked);
        backButton.setOnMouseClicked(backButtonClicked);

        pres.updateScrollPane();
    }

    public void shoppingCartChanged(CartEvent event){
        pres.updateScrollPane();
    }

    EventHandler<MouseEvent> backButtonClicked
            = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            try {
                Parent start = FXMLLoader.load(getClass().getResource("IMat_CheckOut_v2.fxml"));
                IMat.getStage().setScene(new Scene(start, 1360, 768));
            } catch (IOException ex) {
                Logger.getLogger(IMat_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    EventHandler<MouseEvent> doneButtonClicked
            = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            try {
                Parent start = FXMLLoader.load(getClass().getResource("IMat_FinishBuy.fxml"));
                IMat.getStage().setScene(new Scene(start, 1360, 768));
            } catch (IOException ex) {
                Logger.getLogger(IMat_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };
}
