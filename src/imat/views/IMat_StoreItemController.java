/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat;

import imat.views.IMat_BasketItemController;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import imat.views.IMat_FXMLController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;

/**
 * FXML Controller class
 *
 * @author Gustav
 */
public class IMat_StoreItemController implements Initializable {

    private ShoppingCart sC;

    @FXML
    private Label itemNameLabel, itemPriceLabel, itemQuantityLabel;

    private int itemId;
    @FXML
    private Button addButton;
    @FXML
    private ImageView itemImage, favorizeStarImage;
    @FXML
    private ScrollPane basketScrollPane;
    @FXML
    private TextField totalPrice;

    private boolean isFavorite = false;
    private boolean inBasket = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addButton.setOnMouseClicked(onButtonClicked);
        favorizeStarImage.setOnMouseClicked(favClicked);
        favorizeStarImage.setCursor(Cursor.HAND);
    }

    
    // Add or remove favorites.
    EventHandler<MouseEvent> favClicked
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    Product prod = IMat_Model.getBackEnd().getProduct(itemId);  

                    if (!IMat_Model.getBackEnd().favorites().contains(prod)) {
                        favorizeStarImage.setImage(new Image("imat/images/golden_star.jpg"));
                        IMat_Model.getBackEnd().addFavorite(prod);
                    } else {
                        favorizeStarImage.setImage(new Image("imat/images/star.jpg"));
                        IMat_Model.getBackEnd().removeFavorite(prod);
                    }
                }
            };

    // Adds one to the amount of this specific product in the basket. 
    EventHandler<MouseEvent> onButtonClicked
            = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    Product p;
                    p = IMat_Model.getBackEnd().getProduct(itemId);
                    sC = IMat_Model.getBackEnd().getShoppingCart();
                    boolean inBasket = false;

                    if (sC.getItems().size() > 0) {
                        for (ShoppingItem s : sC.getItems()) {
                            if (s.getProduct().equals(p)) {
                                s.setAmount(s.getAmount() + 1);
                                System.out.println(s.getAmount());
                                System.out.println("Finns redan");
                                inBasket = true;
                            }
                        }
                    } else {
                        System.out.println("Nytt item");
                        ShoppingItem sI = new ShoppingItem(p);
                        sC.addItem(sI);
                        inBasket = true;
                    }

                    if (!inBasket) {
                        System.out.println("bool");
                        ShoppingItem sI = new ShoppingItem(p);
                        sC.addItem(sI);
                    }

                    placeBasketItems(sC.getItems());
                }
            };

    // Place the storeItems in the basket.
    public void placeBasketItems(List<ShoppingItem> list) {
        FlowPane flowPane = new FlowPane();
        flowPane.setVgap(3);
        flowPane.setPrefWidth(255);

        for (ShoppingItem s : list) {
            try {
                Product p = s.getProduct();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("views/IMat_BasketItem.fxml"));
                Node storeItem = loader.load();
                IMat_BasketItemController controller = loader.getController();
                controller.setItemNameLabel(p.getName());
                controller.setItemPriceLabel(p.getPrice() * s.getAmount());
                controller.setItemQuantity("kr");
                controller.setShoppingItem(s);
                controller.setNrOfBasketItems(s.getAmount());
                flowPane.getChildren().add(storeItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        basketScrollPane.setContent(flowPane);
        updateTotalPrice();
    }

    public void updateTotalPrice() {
        IMat_FXMLController.getPresenter().setTotal();
        IMat_FXMLController.getPresenter().setButtonActive();
    }

    //Only testing
    public void setItemNameLabel(String name) {
        this.itemNameLabel.setText(name);
    }

    public void setItemPriceLabel(double price) {
        this.itemPriceLabel.setText(Double.toString(price));
    }

    public void setItemImage(Image image) {
        this.itemImage.setImage(image);
    }

    public void setItemQuantity(String quant) {
        itemQuantityLabel.setText(quant);
    }

    public void setItemID(int id) {
        this.itemId = id;
    }

    public void setScrollPane(ScrollPane sp) {
        this.basketScrollPane = sp;
    }

    public void setFavPic(Image im) {
        this.favorizeStarImage.setImage(im);
    }

}
