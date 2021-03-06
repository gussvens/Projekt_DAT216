package imat;

import java.net.URL;
import java.util.ResourceBundle;

import imat.views.IMat_StoreController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;

/**
 * FXML Controller class
 *
 * @author Group 12
 */
public class IMat_StoreItemController implements Initializable {

    // MenuButton colors.
    private final String MENU_DEFAULT_COLOR = "-fx-background-color: #009973;";
    private final String MENU_ENTER_COLOR = "-fx-background-color:  #22BB95;";
    private final String MENU_CLICKED_COLOR = "-fx-background-color:  #11AA84;";
    
    
    // FXML inports
    @FXML
    private Label itemNameLabel, itemPriceLabel, itemQuantityLabel;
    @FXML
    private Button addButton;
    @FXML
    private ImageView itemImage, favorizeStarImage;
    @FXML
    private ScrollPane basketScrollPane;
    @FXML
    private TextField totalPrice;

    // Other variables
    private boolean isFavorite = false;
    private boolean inBasket = false;
    private ShoppingCart sC;
    private int itemId;

    // Adds some listeners and sets the cursor for the favorite-button.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addButton.setOnMouseClicked(onButtonClicked);
        addButton.setOnMouseEntered(storeItemEnter);
        addButton.setOnMouseExited(storeItemExit);
        addButton.setOnMousePressed(storeItemPressed);
        addButton.setOnMouseReleased(storeItemReleased);
        favorizeStarImage.setOnMouseClicked(favClicked);
        favorizeStarImage.setCursor(Cursor.HAND);
    }

    /*
     Add or remove favorites and sets the star in the menu to active if
     there are any objects set to favorite.
     */
    EventHandler<MouseEvent> favClicked
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    Product prod = IMat_Model.getBackEnd().getProduct(itemId);

                    if (!IMat_Model.getBackEnd().favorites().contains(prod)) {
                        favorizeStarImage.setImage(new Image("imat/images/golden_star_trans_new.png"));
                        IMat_Model.getBackEnd().addFavorite(prod);
                        IMat_StoreController.getPresenter().setFavStarActive();
                    } else {
                        favorizeStarImage.setImage(new Image("imat/images/star_trans_new.png"));
                        IMat_Model.getBackEnd().removeFavorite(prod);
                        if (IMat_Model.getBackEnd().favorites().isEmpty()) {
                            IMat_StoreController.getPresenter().setFavStarInactive();
                        }
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
            updateTotalPrice();
            IMat_Model.getBackEnd().getShoppingCart().fireShoppingCartChanged(new ShoppingItem(p), true);

        }
    };

    // Place the storeItems in the basket.
    /*public void placeBasketItems(List<ShoppingItem> list) {
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
        IMat_StoreController.getPresenter().setRemoveAllFromBasketActive();
        IMat_StoreController.getPresenter().setSaveListButtonActive();
        updateTotalPrice();
    }*/

    
    
    public void updateTotalPrice() {
        IMat_StoreController.getPresenter().setTotal();
        IMat_StoreController.getPresenter().setButtonActive();
    }

    // Resets the color of the menuButton on mouseExit. 
    EventHandler<MouseEvent> storeItemExit
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    if (getButton(t).getStyle().equals(MENU_ENTER_COLOR)) {
                        getButton(t).setStyle(MENU_DEFAULT_COLOR);
                    }
                }
            };
    
    EventHandler<MouseEvent> storeItemPressed
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    
                    getButton(t).setStyle(MENU_CLICKED_COLOR);
                    
                }
            };
    EventHandler<MouseEvent> storeItemReleased
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    
                    getButton(t).setStyle(MENU_ENTER_COLOR);
                    
                }
            };

    // Changes the color of the menuButton on mouseEnter.
    EventHandler<MouseEvent> storeItemEnter
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    if (!getButton(t).getStyle().equals(MENU_CLICKED_COLOR)) {
                        getButton(t).setStyle(MENU_ENTER_COLOR);
                    }
                }
            };

    // Helper to get the right menuButton.
    private Button getButton(MouseEvent t) {
        return ((Button) t.getSource());
    }
    
    
    

    // Setters
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
