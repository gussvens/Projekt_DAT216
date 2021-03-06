package imat;

import imat.views.IMat_BasketItemController;
import imat.views.IMat_StoreController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;

/**
 *
 * @author Group 12
 */
public class IMat_presenter extends Observable {
    //private final IMat_Model model;

    // MenuButton colors.
    private final String MENU_DEFAULT_COLOR = "-fx-background-color: #FDFDFD;";
    private final String MENU_ENTER_COLOR = "-fx-background-color:  #bdfbec;";
    private final String MENU_CLICKED_COLOR = "-fx-background-color:  #adebdc;";

    //ff9900
    //ffcc80


    //BCE954
    //667c26


    // SearchButton colors.
    private final String SEARCH_BTN_DEFAULT = "-fx-background-color: #FDFDFD;";
    private final String SEARCH_BTN_ENTER = "-fx-background-color: #EEEEEE;";
    private final String SEARCH_BTN_DOWN = "-fx-background-color: #9bd1c4;";

    private IMat_StoreController FXMLcont;
    private final List<Pane> menuButtonsList;
    private ScrollPane basketScrollPane;
    private ScrollPane checkOutScrollPane;
    private ScrollPane subScrollPane;
    private final Button searchButton;
    private Button removeAllFromBasket;
    //private Button saveAsListButton;
    private Button toCheckout;
    private TextField totalPrice;
    private TextField searchTextField;
    private ImageView menuFavStar;
    private final Pane CategoryDairy,
            CategoryVegetables,
            CategoryFruit_Berries,
            CategoryDryGoods,
            CategoryMeat_Fish_Shellfish,
            CategoryDrinks,
            CategoryCandy_Snacks,
            CategoryBread,
            CategoryFavorites;

    public IMat_presenter(
            Pane CategoryDairy,
            Pane CategoryVegetables,
            Pane CategoryFruit_Berries,
            Pane CategoryDryGoods,
            Pane CategoryMeat_Fish_Shellfish,
            Pane CategoryDrinks,
            Pane CategoryCandy_Snacks,
            Pane CategoryBread,
            Button searchButton,
            TextField totalPrice,
            Button toCheckout,
            ScrollPane basketScrollPane,
            ScrollPane subScrollPane,
            IMat_StoreController FXMLcont,
            Pane CategoryFavorites,
            ImageView menuFavStar,
            //Button saveAsListButton,
            Button removeAllFromBasket,
            TextField searchTextField) {

        this.searchTextField = searchTextField;
        // this.saveAsListButton = saveAsListButton;
        this.removeAllFromBasket = removeAllFromBasket;
        this.menuFavStar = menuFavStar;
        this.CategoryFavorites = CategoryFavorites;
        this.FXMLcont = FXMLcont;
        this.subScrollPane = subScrollPane;
        this.basketScrollPane = basketScrollPane;
        this.toCheckout = toCheckout;
        this.totalPrice = totalPrice;
        this.searchButton = searchButton;
        this.CategoryDairy = CategoryDairy;
        this.CategoryCandy_Snacks = CategoryCandy_Snacks;
        this.CategoryDrinks = CategoryDrinks;
        this.CategoryDryGoods = CategoryDryGoods;
        this.CategoryFruit_Berries = CategoryFruit_Berries;
        this.CategoryMeat_Fish_Shellfish = CategoryMeat_Fish_Shellfish;
        this.CategoryVegetables = CategoryVegetables;
        this.CategoryBread = CategoryBread;


        /*
         Puts all menuButtonObjects into a list so the listernerbinding
         will be more effective.
         */
        menuButtonsList = new ArrayList<>();
        menuButtonsList.add(CategoryDairy);
        menuButtonsList.add(CategoryCandy_Snacks);
        menuButtonsList.add(CategoryDryGoods);
        menuButtonsList.add(CategoryFruit_Berries);
        menuButtonsList.add(CategoryMeat_Fish_Shellfish);
        menuButtonsList.add(CategoryVegetables);
        menuButtonsList.add(CategoryDrinks);
        menuButtonsList.add(CategoryBread);
        menuButtonsList.add(CategoryFavorites);

        // Sets mouseEvents and cursor to the searchButton
        for (Pane p : menuButtonsList) {
            p.setOnMouseEntered(menuButtonEnter);
            p.setOnMouseExited(menuButtonExit);
            p.setCursor(Cursor.HAND);
        }

        // Sets mouseEvents and cursor to the searchButton.
        searchButton.setOnMouseEntered(searchButtonEnter);
        searchButton.setOnMouseExited(searchButtonExit);
        searchButton.setOnMousePressed(searchButtonPressed);
        searchButton.setOnMouseReleased(searchButtonReleased);
        searchButton.setCursor(Cursor.HAND);

        checkOutScrollPane = new ScrollPane();

        // NOT SURE IF NEEDED HERE...
        //model = new IMat_Model();

        updateBasket();
    }

    // EVENTHANDLERS
    EventHandler<MouseEvent> searchButtonEnter
            = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            setSeachButtonColor(t, SEARCH_BTN_ENTER);
        }
    };

    EventHandler<MouseEvent> searchButtonExit
            = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            setSeachButtonColor(t, SEARCH_BTN_DEFAULT);
        }
    };
    EventHandler<MouseEvent> searchButtonPressed
            = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            setSeachButtonColor(t, SEARCH_BTN_DOWN);
        }
    };
    EventHandler<MouseEvent> searchButtonReleased
            = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            setSeachButtonColor(t, SEARCH_BTN_ENTER);
        }
    };

    // Resets the color of the menuButton on mouseExit. 
    EventHandler<MouseEvent> menuButtonExit
            = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            if (getButton(t).getStyle().equals(MENU_ENTER_COLOR)) {
                getButton(t).setStyle(MENU_DEFAULT_COLOR);
            }
        }
    };

    // Changes the color of the menuButton on mouseEnter.
    EventHandler<MouseEvent> menuButtonEnter
            = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            if (!getButton(t).getStyle().equals(MENU_CLICKED_COLOR)) {
                getButton(t).setStyle(MENU_ENTER_COLOR);
            }
        }
    };

    // Helper...
    private void setSeachButtonColor(MouseEvent t, String s) {
        ((Button) t.getSource()).setStyle(s);
    }

    // Helper to get the right menuButton.
    private Pane getButton(MouseEvent t) {
        return ((Pane) t.getSource());
    }

    private double getTotal() {
        return IMat_Model.getBackEnd().getShoppingCart().getTotal();
    }

    // Sets the color of a menuPane when it's clicked.
    public void colorChangeOnClick(MouseEvent t) {
        for (Pane p : menuButtonsList) {
            p.setStyle(MENU_DEFAULT_COLOR);
        }

        getButton(t).setStyle(MENU_CLICKED_COLOR);
        //Change colour of selected item to white. getButton(t).getChildren().get(0).getAccessibleRole() something?
    }


        // Sets the totalPrice every time a product is added to the basket.
    


    public void setTotal() {
        double d = Math.round(getTotal()*100.0)/100.0;
        totalPrice.setText(Double.toString(d) + " kr");
    }

    /* Sets the toCheckout-button to active 
     when there are products in the basket
     */
    public void setButtonActive() {
        if (toCheckout.isDisabled()) {
            toCheckout.setDisable(false);
        }
    }

    /* Updates the basket when removing products from the basket.
     Sets the toCheckout-button to be disabled if list is empty.
        
     !!Could be used to update when placing products as well, I guess. !!
     */
    public void updateBasket() {
        FlowPane flowPane = new FlowPane();
        flowPane.setVgap(3);
        flowPane.setPrefWidth(255);

        for (ShoppingItem s : IMat_Model.getBackEnd().getShoppingCart().getItems()) {
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
        setTotal();

    }

    public void filterSearch() {
        FlowPane flowPane = new FlowPane();
        flowPane.setVgap(6);
        flowPane.setHgap(6);
        flowPane.setPrefWidth(255);

        for (ShoppingItem s : IMat_Model.getBackEnd().getShoppingCart().getItems()) {
            try {
                Product p = s.getProduct();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("views/IMat_BasketItem.fxml"));
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
        setTotal();

        if (IMat_Model.getBackEnd().getShoppingCart().getItems().isEmpty()) {
            toCheckout.setDisable(true);
        } else {
            toCheckout.setDisable(false);
        }
    }

    public void setCheckoutScrollPane(ScrollPane sP) {
        this.checkOutScrollPane = new ScrollPane();
        this.checkOutScrollPane = sP;
    }

    //
    //
    // NOT NEEDED NOW... CAN BE REMOVED WHEN DONE
    public static void setStage(Stage stage, Parent parent) {
        Scene scene = new Scene(parent);
        stage.setScene(scene);
    }

    public IMat_StoreController getFXMLCont() {
        return FXMLcont;
    }

    public void setFavStarInactive() {
        menuFavStar.setImage(new Image("imat/images/star_trans_new.png"));
    }

    public void setFavStarActive() {
        menuFavStar.setImage(new Image("imat/images/golden_star_trans_new.png"));
    }



    /* public void setSaveListButtonActive(){
     saveAsListButton.setDisable(false);
     }
     public void setSaveListButtonInctive(){
     saveAsListButton.setDisable(true);
     }*/

    public void clearSeachField() {
        searchTextField.setText("");
        searchTextField.setPromptText("Sök produkter här...");
    }
}

