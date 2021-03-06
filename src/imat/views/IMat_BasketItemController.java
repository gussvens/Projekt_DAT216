package imat.views;

import imat.IMat_Checkout_presenter;
import imat.IMat_Model;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;
import imat.controllers.*;

/**
 * FXML Controller class
 *
 * @author Andreas
 */
public class IMat_BasketItemController implements Initializable {

    @FXML
    private AnchorPane basketItemBg;
    @FXML
    private Label basketProdName;
    @FXML
    private Label basketProdPrice;
    @FXML
    private Label basketProdQuant;
    @FXML
    private ImageView basketRemove;
    @FXML
    private ScrollPane basketScrollPane;
    @FXML
    private Label nrOfBasketItems;
    @FXML
    private ImageView addAnother;
    @FXML
    private ImageView removeOne;

    private ShoppingItem sI;
    private Boolean inBasket;
    private ShoppingCart sC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        basketRemove.setOnMouseClicked(removeObject);
        basketRemove.setCursor(Cursor.HAND);
        addAnother.setOnMouseClicked(addObject);
        addAnother.setCursor(Cursor.HAND);
        removeOne.setOnMouseClicked(removeOneObject);
        removeOne.setCursor(Cursor.HAND);
        sC = IMat_Model.getBackEnd().getShoppingCart();

        // PlusButton
        addAnother.setOnMouseEntered(enterPlusButton);
        addAnother.setOnMouseExited(exitPlusButton);
        addAnother.setOnMousePressed(clickedPlusButton);
        addAnother.setOnMouseReleased(exitPlusButton);
        addAnother.setCursor(Cursor.HAND);

        //MinusButton
        removeOne.setOnMouseEntered(enterMinusButton);
        removeOne.setOnMouseExited(exitMinusButton);
        removeOne.setOnMousePressed(clickedMinusButton);
        removeOne.setOnMouseReleased(exitMinusButton);
        removeOne.setCursor(Cursor.HAND);

    }

    EventHandler<MouseEvent> enterMinusButton
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    ((ImageView) t.getSource()).setImage(new Image("imat/images/enter_minus_button.jpg"));
                }
            };
    EventHandler<MouseEvent> exitMinusButton
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    ((ImageView) t.getSource()).setImage(new Image("imat/images/def_minus_button.jpg"));
                }
            };
    EventHandler<MouseEvent> clickedMinusButton
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    ((ImageView) t.getSource()).setImage(new Image("imat/images/clicked_minus_button.jpg"));
                }
            };
    EventHandler<MouseEvent> enterPlusButton
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    ((ImageView) t.getSource()).setImage(new Image("imat/images/enter_plus_button.jpg"));
                }
            };
    EventHandler<MouseEvent> exitPlusButton
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    ((ImageView) t.getSource()).setImage(new Image("imat/images/def_plus_button.jpg"));
                }
            };
    EventHandler<MouseEvent> clickedPlusButton
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    ((ImageView) t.getSource()).setImage(new Image("imat/images/clicked_plus_button.jpg"));
                }
            };

    // Removes the shoppingItem from the cart and the flowpane.
    EventHandler<MouseEvent> removeObject
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    FadeTransition ft = new FadeTransition(Duration.millis(400), basketItemBg);
                    ft.setFromValue(1.0);
                    ft.setToValue(0.0);
                    ft.setCycleCount(1);
                    ft.setAutoReverse(true);

                    ft.play();

                    ft.setOnFinished(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            sC.removeItem(sI);
                            IMat_StoreController.getPresenter().updateBasket();

                            if (IMat_CheckOutController.getPresenter() != null) {
                                IMat_CheckOutController.getPresenter().updateScrollPane();
                            }

                        }
                    });
                }
            };

    EventHandler<MouseEvent> removeOneObject
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    Product p = sI.getProduct();
                    sI.setAmount(sI.getAmount() - 1);

                    if (sI.getAmount() == 0) {
                        sC.removeItem(sI);
                    }

                    IMat_StoreController.getPresenter().updateBasket();
                    IMat_Model.getBackEnd().getShoppingCart().fireShoppingCartChanged(new ShoppingItem(p), true);
                    if (getPresenter() != null) {
                        getPresenter().updateScrollPane();
                    }

                    IMat_Model.getBackEnd().getShoppingCart().fireShoppingCartChanged(sI, true);
                }
            };

    EventHandler<MouseEvent> addObject
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {

                    Product p = sI.getProduct();

                    if (sC.getItems().size() > 0) {
                        for (ShoppingItem s : sC.getItems()) {
                            if (s.getProduct().equals(p)) {
                                s.setAmount(s.getAmount() + 1);
                                inBasket = true;
                            }
                        }
                    } else {
                        ShoppingItem sI = new ShoppingItem(p);
                        sC.addItem(sI);
                        inBasket = true;
                    }

                    if (!inBasket) {
                        ShoppingItem sI = new ShoppingItem(p);
                        sC.addItem(sI);
                    }
                    nrOfBasketItems.setText(Integer.toString(Integer.parseInt(nrOfBasketItems.getText()) + 1));

                    IMat_StoreController.getPresenter().updateBasket();
                    if (getPresenter() != null) {
                        getPresenter().updateScrollPane();
                    }


                    IMat_Model.getBackEnd().getShoppingCart().fireShoppingCartChanged(new ShoppingItem(p), true);

                    //IMat_StoreController.getPresenter().updateBasket();
                    if (IMat_CheckOutController.getPresenter() != null) {
                        IMat_CheckOutController.getPresenter().updateScrollPane();
                    }


                    /*
                     //IMat_Model.getBackEnd().getShoppingCart().fireShoppingCartChanged(new ShoppingItem(p), true);
                     IMat_StoreController.getPresenter().updateBasket();
                     if (IMat_CheckOutController.getPresenter() != null) {
                     IMat_CheckOutController.getPresenter().updateScrollPane();
                     }
                     */

                }
            };

    public void setItemNameLabel(String name) {
        this.basketProdName.setText(name);
    }

    public void setItemPriceLabel(double price) {
        double d = Math.round(price*100.0)/100.0;
        
        this.basketProdPrice.setText(d + "");
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

    private IMat_Checkout_presenter getPresenter() {
        return IMat_CheckOutController.getPresenter();
    }
}
