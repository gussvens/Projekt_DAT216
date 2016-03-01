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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingCartListener;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addButton.setOnMouseClicked(onButtonClicked);

    }

    EventHandler<MouseEvent> onButtonClicked
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    Product p;
                    p = IMat_Model.getBackEnd().getProduct(itemId);
                    sC = IMat_Model.getBackEnd().getShoppingCart();
                    List<Product> l = IMatDataHandler.getInstance().getProducts();
                    for (Product x : l) {
                        System.out.println(x.getName());
                    }
                    ShoppingItem sI = new ShoppingItem(p);

                    //Attempt at incrementing items already in the cart
                    if (!sC.getItems().contains(sI)) {
                        sC.addItem(sI);
                    }
                    /*
                     for (ShoppingItem s : sC.getItems()) {
                     if (s.getProduct().getName().equals(p.getName())) {
                     s.setAmount(s.getAmount() + 1);
                     System.out.println(s.getAmount());
                     }
                     }
                     */
                    placeBasketItems(sC.getItems());
                }
            };

    public void placeBasketItems(List<ShoppingItem> list) {
        FlowPane flowPane = new FlowPane();
        flowPane.setVgap(6);
        flowPane.setHgap(6);
        flowPane.setPrefWidth(255);

        for (ShoppingItem s : list) {
            try {
                Product p = s.getProduct();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("views/IMat_BasketItem.fxml"));
                Node storeItem = loader.load();
                IMat_BasketItemController controller = loader.getController();
                controller.setItemNameLabel(p.getName());
                controller.setItemPriceLabel(p.getPrice());
                controller.setItemQuantity(p.getUnit());
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

    /* public IMat_StoreItemController(Product item){
     this.itemNameLabel.setText(item.getName());
     this.itemPriceLabel.setText(item.getPrice() + " kr");
     this.itemQuantityLabel.setText(item.getUnit() + " " + item.getUnitSuffix());
     }
     */
}
