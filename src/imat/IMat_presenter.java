package imat;

import imat.views.IMat_BasketItemController;
import imat.views.IMat_FXMLController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;

/**
 *
 * @author Andreas
 */
public class IMat_presenter extends Observable {

    private IMat_FXMLController FXMLcont;
    private final IMat_Model model;

    // MenuButton colors.
    private final String MENU_DEFAULT_COLOR = "-fx-background-color: #FFFFFF;";
    private final String MENU_ENTER_COLOR = "-fx-background-color: #d6eeff;";
    private final String MENU_CLICKED_COLOR = "-fx-background-color: #a6eafc;";

    // SearchButton colors.
    private final String SEARCH_BTN_DEFAULT = "-fx-background-color: #a6eafc;";
    private final String SEARCH_BTN_ENTER = "-fx-background-color: #95d9eb;";
    private final String SEARCH_BTN_DOWN = "-fx-background-color: #84c8da;";

    private final List<Pane> menuButtonsList;

    private final Pane CategoryDairy,
            CategoryVegetables,
            CategoryFruit_Berries,
            CategoryDryGoods,
            CategoryMeat_Fish_Shellfish,
            CategoryDrinks,
            CategoryCandy_Snacks,
            CategoryBread,
            CategoryFavorites;

    private final Button searchButton;
    private Button toCheckout;

    private TextField totalPrice;
    private ScrollPane basketScrollPane;
    private ScrollPane checkOutScrollPane;
    private ScrollPane subScrollPane;

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
            IMat_FXMLController FXMLcont,
            Pane CategoryFavorites) {

        model = new IMat_Model();

        checkOutScrollPane = new ScrollPane();

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

        updateBasket();
        System.out.println(IMat_Model.getBackEnd().getShoppingCart().getItems().size());

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

    public void colorChangeOnClick(MouseEvent t) {
        for (Pane p : menuButtonsList) {
            p.setStyle(MENU_DEFAULT_COLOR);
        }

        getButton(t).setStyle(MENU_CLICKED_COLOR);
    }

    // Sets the totalPrice every time a product is added to the basket.
    public void setTotal() {
        totalPrice.setText(Double.toString(IMat_Model.getBackEnd().getShoppingCart().getTotal()) + " kr");
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
        flowPane.setVgap(6);
        flowPane.setHgap(6);
        flowPane.setStyle("-fx-background: #FFFFFF;");
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

    public IMat_FXMLController getFXMLCont() {
        return FXMLcont;
    }

}
