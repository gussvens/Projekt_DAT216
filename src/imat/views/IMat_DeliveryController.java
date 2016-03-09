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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Emil on 09/03/2016.
 */
public class IMat_DeliveryController implements Initializable{
    @FXML
    private Button doneButton;
    @FXML
    private ScrollPane basketScrollPane;
    @FXML
    private Pane backButton;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        doneButton.setOnMouseClicked(doneButtonClicked);
        backButton.setOnMouseClicked(backButtonClicked);

        FlowPane basketFlowPane = new FlowPane();
        basketFlowPane.setVgap(3);
        basketFlowPane.setPrefWidth(255);

        for (ShoppingItem s : IMat_Model.getBackEnd().getShoppingCart().getItems()) {
            try {
                Product p = s.getProduct();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("IMat_BasketItem.fxml"));
                Node storeItem = loader.load();
                IMat_BasketItemController controller = loader.getController();
                controller.setItemNameLabel(p.getName());
                controller.setItemPriceLabel(p.getPrice() * s.getAmount());
                controller.setItemQuantity("kr");
                controller.setShoppingItem(s);
                controller.setNrOfBasketItems(s.getAmount());
                basketFlowPane.getChildren().add(storeItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        basketScrollPane.setContent(basketFlowPane);
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
