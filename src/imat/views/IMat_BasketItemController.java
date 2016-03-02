package imat.views;

import imat.IMat_Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
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

    private ShoppingItem sI;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        basketRemove.setOnMouseClicked(removeObject);
    }

    // Removes the shoppingItem from the cart and the flowpane.
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

    public void setNrOfBasketItems(double amount) {
        this.nrOfBasketItems.setText((int) amount + "");
    }
}
