/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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

    
    @FXML
    private Label itemNameLabel, itemPriceLabel, itemQuantityLabel, itemId;
    @FXML
    private Button addButton;
    @FXML
    private ImageView itemImage, favorizeStarImage;
    @FXML
    private ScrollPane basketScrollPane;

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
                    
                    System.out.println(itemNameLabel.getText());
                    int id = Integer.parseInt(itemId.getText());
                   Product p = new Product();
                   p = IMatDataHandler.getInstance().getProduct(id);
                   
                    

                }
            };

    public void placeStoreItems(List<Product> list) {
        FlowPane flowPane = new FlowPane();
        flowPane.setVgap(6);
        flowPane.setHgap(6);
        flowPane.setPrefWidth(250);

        for (Product p : list) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("IMat_StoreItem.fxml"));
                Node storeItem = loader.load();
                IMat_StoreItemController controller = loader.getController();
                controller.setItemNameLabel(p.getName());
                controller.setItemPriceLabel(p.getPrice());
                //controller.setItemImage(IMatDataHandler.getInstance().getFXImage(p));
                controller.setItemQuantity(p.getUnit());
                flowPane.getChildren().add(storeItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.basketScrollPane.setContent(flowPane);
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
    
    public void setItemID(String id){
        itemId.setText(id);
    }

    /* public IMat_StoreItemController(Product item){
     this.itemNameLabel.setText(item.getName());
     this.itemPriceLabel.setText(item.getPrice() + " kr");
     this.itemQuantityLabel.setText(item.getUnit() + " " + item.getUnitSuffix());
     }
     */
}
