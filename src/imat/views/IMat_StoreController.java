package imat.views;

import imat.*;
import imat.IMat_StoreItemController;
import imat.IMat_presenter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import se.chalmers.ait.dat215.project.*;

/**
 *
 * @author Group 12
 */
public class IMat_StoreController implements Initializable, ShoppingCartListener {

    private final String BTN_DEFAULT_ENTER = "-fx-background-color: #bdfbec;";
    private final String BTN_DEFAULT_EXIT = "-fx-background-color: #FFFFFF;";
    private final String BTN_DEFAULT_CLICK = "-fx-background-color: #9bd1c4;";

    private static IMat_presenter pres;
    private static int activeSubindex;

    // Imports from sceneBuilder.
    @FXML
    private Pane CategoryDairy;
    @FXML
    private Pane CategoryVegetables;
    @FXML
    private Pane CategoryFruit_Berries;
    @FXML
    private Pane CategoryDryGoods;
    @FXML
    private Pane CategoryMeat_Fish_Shellfish;
    @FXML
    private Pane CategoryDrinks;
    @FXML
    private Pane CategoryCandy_Snacks;
    @FXML
    private Pane CategoryBread;

    @FXML
    private Button searchButton;
    @FXML
    private TextField searchField;
    @FXML
    private Pane historyButton;
    @FXML
    private Pane settingsButton;
    @FXML
    private ScrollPane storeItemScrollPane;
    @FXML
    private ScrollPane basketScrollPane;
    @FXML
    private TextField totalPrice;
    @FXML
    private Button toCheckout;
    @FXML
    private Label subName;
    @FXML
    private ScrollPane subScrollPane;
    @FXML
    private Pane CategoryFavorites;
    @FXML
    private ImageView menuFavStar;
    @FXML
    private Button removeAllFromBasket;
    @FXML
    private ImageView userPic;
    @FXML
    private ImageView historyPic;
    @FXML
    private Pane basketTopPane;
    @FXML
    private Pane bgPane;

    private List<Product> prodList;
    private List<Product> tempProdList;
    private List<Pane> menuButtonList;
    private List<String> subNameList;
    private String currentCategory;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        totalPrice.setText(Double.toString(IMat_Model.getBackEnd().getShoppingCart().getTotal()));

        if (!IMat_Model.getBackEnd().favorites().isEmpty()) {
            menuFavStar.setImage(new Image("imat/images/golden_star_trans_new.png"));
        }

        pres = new IMat_presenter(
                CategoryVegetables,
                CategoryFruit_Berries,
                CategoryBread,
                CategoryDairy,
                CategoryDryGoods,
                CategoryMeat_Fish_Shellfish,
                CategoryDrinks,
                CategoryCandy_Snacks,
                searchButton,
                totalPrice,
                toCheckout,
                basketScrollPane,
                subScrollPane,
                this,
                CategoryFavorites,
                menuFavStar,
                //  saveAsListButton,
                removeAllFromBasket,
                searchField
        );

        menuButtonList = new ArrayList<>();
        menuButtonList.add(CategoryCandy_Snacks);
        menuButtonList.add(CategoryDairy);
        menuButtonList.add(CategoryDrinks);
        menuButtonList.add(CategoryDryGoods);
        menuButtonList.add(CategoryFruit_Berries);
        menuButtonList.add(CategoryMeat_Fish_Shellfish);
        menuButtonList.add(CategoryVegetables);
        menuButtonList.add(CategoryBread);
        menuButtonList.add(CategoryFavorites);

        for (Pane p : menuButtonList) {
            p.setOnMouseClicked(menuButtonClicked);
        }

        // SearchButton
        searchButton.setOnMouseClicked(searchButtonClicked);
        searchButton.setOnMouseEntered(searchButtonEnter);
        searchButton.setOnMouseClicked(searchButtonClicked);
        searchButton.setCursor(Cursor.HAND);

        // HistoryButton
        historyButton.setOnMouseClicked(historyButtonClicked);
        historyButton.setOnMouseEntered(historyButtonEnter);
        historyButton.setOnMouseExited(historyButtonExit);
        historyButton.setCursor(Cursor.HAND);

        // SettingButton
        settingsButton.setOnMouseClicked(settingsButtonClicked);
        settingsButton.setOnMouseEntered(settingsButtonEnter);
        settingsButton.setOnMouseExited(settingsButtonExit);
        settingsButton.setCursor(Cursor.HAND);

        // Empty-basketButton
        removeAllFromBasket.setOnMouseClicked(setBasketEmpty);
        removeAllFromBasket.setOnMouseEntered(removeButtonEnter);
        removeAllFromBasket.setOnMouseExited(removeButtonExit);
        removeAllFromBasket.setCursor(Cursor.HAND);

        // CheckoutButton
        toCheckout.setOnMouseClicked(checkoutButtonClicked);
        toCheckout.setOnMouseEntered(toCheckOutEnter);
        toCheckout.setOnMouseExited(toCheckOutExit);
        toCheckout.setCursor(Cursor.HAND);

        searchField.setOnKeyPressed(searchEnterPressed);
        // homeButton.setOnMouseClicked(homeButtonClicked);
        // saveAsListButton.setOnMouseClicked(saveList);

        // Initializing centerstage.
        initCenter();

        IMat_Model.getBackEnd().getShoppingCart().addShoppingCartListener(this);

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

        if (IMat_Model.getBackEnd().getShoppingCart().getItems().isEmpty()) {
            toCheckout.setDisable(true);
            removeAllFromBasket.setDisable(true);
            Text text1 = new Text("\n\n\n\n\n\n\n\nDin kundvagn är för närvarande tom.");
            text1.setFont(Font.font("System", 13));
            text1.setFontSmoothingType(FontSmoothingType.LCD);
            TextFlow textFlow = new TextFlow(text1);
            basketFlowPane.getChildren().add(textFlow);
            basketFlowPane.setAlignment(Pos.CENTER);
        } else {
            toCheckout.setDisable(false);
            removeAllFromBasket.setDisable(false);
        }
    }

    EventHandler<MouseEvent> removeButtonEnter
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    if (!removeAllFromBasket.isDisabled()) {
                        removeAllFromBasket.setStyle(BTN_DEFAULT_ENTER);
                    }
                }
            };

    EventHandler<MouseEvent> toCheckOutEnter
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    if (!toCheckout.isDisabled()) {
                        toCheckout.setStyle(BTN_DEFAULT_ENTER);
                    }
                }
            };
    EventHandler<MouseEvent> toCheckOutExit
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    if (!toCheckout.isDisabled()) {
                        toCheckout.setStyle(BTN_DEFAULT_EXIT);
                    }
                }
            };

    EventHandler<MouseEvent> removeButtonExit
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {

                    removeAllFromBasket.setStyle(BTN_DEFAULT_EXIT);

                }
            };

    EventHandler<MouseEvent> searchButtonEnter
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    searchButton.setStyle(BTN_DEFAULT_ENTER);
                }
            };

    EventHandler<MouseEvent> searchButtonExit
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    searchButton.setStyle(BTN_DEFAULT_EXIT);
                }
            };

    EventHandler<MouseEvent> historyButtonEnter
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    historyButton.setStyle(BTN_DEFAULT_ENTER);
                }
            };

    EventHandler<MouseEvent> historyButtonExit
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    historyButton.setStyle(BTN_DEFAULT_EXIT);
                }
            };

    EventHandler<MouseEvent> settingsButtonEnter
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    settingsButton.setStyle(BTN_DEFAULT_ENTER);
                }
            };

    EventHandler<MouseEvent> settingsButtonExit
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    settingsButton.setStyle(BTN_DEFAULT_EXIT);
                }
            };

    EventHandler<MouseEvent> setBasketEmpty
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    IMat_Model.getBackEnd().getShoppingCart().clear();
                    pres.updateBasket();
                }
            };

    EventHandler<MouseEvent> saveList
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    Order order = new Order();
                    order.setItems(IMat_Model.getBackEnd().getShoppingCart().getItems());
                }
            };

    EventHandler<MouseEvent> checkoutButtonClicked
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    try {
                        Parent start = FXMLLoader.load(getClass().getResource("IMat_CheckOut.fxml"));
                        IMat.getStage().setScene(new Scene(start, 1360, 768));
                    } catch (IOException ex) {
                        Logger.getLogger(IMat_StoreController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };

    EventHandler<MouseEvent> homeButtonClicked
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    try {
                        Parent start = FXMLLoader.load(getClass().getResource("IMat_Start.fxml"));
                        IMat.getStage().setScene(new Scene(start, 1360, 768));
                    } catch (IOException ex) {
                        Logger.getLogger(IMat_StoreController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };

    EventHandler<MouseEvent> historyButtonClicked
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    try {
                        historyButton.setStyle(BTN_DEFAULT_CLICK);
                        Parent start = FXMLLoader.load(getClass().getResource("IMat_History.fxml"));
                        IMat.getStage().setScene(new Scene(start, 1360, 768));
                    } catch (IOException ex) {
                        Logger.getLogger(IMat_StoreController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };

    EventHandler<MouseEvent> settingsButtonClicked
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    try {
                        settingsButton.setStyle(BTN_DEFAULT_CLICK);
                        Parent start = FXMLLoader.load(getClass().getResource("IMat_Settings.fxml"));
                        IMat.getStage().setScene(new Scene(start, 1360, 768));
                    } catch (IOException ex) {
                        Logger.getLogger(IMat_StoreController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };

    public void shoppingCartChanged(CartEvent evt) {
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

        if (IMat_Model.getBackEnd().getShoppingCart().getItems().isEmpty()) {
            toCheckout.setDisable(true);
            removeAllFromBasket.setDisable(true);
            Text text1 = new Text("\n\n\n\n\n\n\n\nDin kundvagn är för närvarande tom.");
            text1.setFont(Font.font("System", 13));
            text1.setFontSmoothingType(FontSmoothingType.LCD);
            TextFlow textFlow = new TextFlow(text1);
            basketFlowPane.getChildren().add(textFlow);
            basketFlowPane.setAlignment(Pos.CENTER);
        } else {
            toCheckout.setDisable(false);
            removeAllFromBasket.setDisable(false);
            //setSaveListButtonActive();
        }
    }

    private String getCurrentPane(MouseEvent t) {
        return ((Pane) t.getSource()).getId();
    }

    
    // Sets the text in the center-scrollPane when a search does not give any results.
    public void searchFeedback(){
        FlowPane flowPane = new FlowPane();
                            flowPane.setVgap(6);
                            flowPane.setHgap(6);
                            flowPane.setPrefWidth(700);

                            Text text1 = new Text("Sökningen gav inga träffar.");
                            text1.setFont(Font.font("System", 21));
                            text1.setTextAlignment(TextAlignment.JUSTIFY);
                            text1.setFontSmoothingType(FontSmoothingType.LCD);
                            Text text2 = new Text("\n\nTIPS: Du kan även använda kategorierna till vänster, samt filtreringen ovan, för att söka efter varor.");
                            text2.setFont(Font.font("System", 11));
                            text2.setTextAlignment(TextAlignment.JUSTIFY);
                            text2.setFontSmoothingType(FontSmoothingType.LCD);
                            TextFlow textFlow = new TextFlow(text1, text2);
                            flowPane.getChildren().add(textFlow);
                            flowPane.setAlignment(Pos.CENTER);
                            storeItemScrollPane.setContent(flowPane);

                            FlowPane flow = new FlowPane();
                            flow.setHgap(6);
                            flow.setPrefWidth(640);
                            flow.setPrefHeight(104);

                            Text text = new Text(" Din sökning");
                            text.setFont(Font.font("System", 42));
                            text.setFontSmoothingType(FontSmoothingType.LCD);
                            TextFlow tFlow = new TextFlow(text);
                            flow.getChildren().add(tFlow);
                            flow.setAlignment(Pos.BASELINE_CENTER);

                            subScrollPane.setContent(flow);
    }
    
    
    
    EventHandler<KeyEvent> searchEnterPressed
            = new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent ke) {

                    if (ke.getCode().equals(KeyCode.ENTER)) {
                        subNameList = new ArrayList<>();
                        List<Product> list = new ArrayList<>();
                        list = IMatDataHandler.getInstance().
                        findProducts(searchField.getText().replaceAll("\\s+", ""));

                        currentCategory = "search";

                        System.out.println("list size: " + list.size());
                        if (!list.isEmpty()) {
                            placeStoreItems(list);
                        } else {
                            searchFeedback();
                        }
                    }

                }
            };

    // Searchfunction
    EventHandler<MouseEvent> searchButtonClicked
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    searchButton.setStyle(BTN_DEFAULT_CLICK);
                    subNameList = new ArrayList<>();
                    List<Product> list = new ArrayList<>();
                    list = IMatDataHandler.getInstance().
                    findProducts(searchField.getText().replaceAll("\\s+", ""));

                    currentCategory = "search";
                     if (!list.isEmpty()) {
                            placeStoreItems(list);
                        } else {
                            searchFeedback();
                        }
                }
            };

    EventHandler<MouseEvent> menuButtonClicked
            = new EventHandler<MouseEvent>() {

                // Sets functionality on each of the menubuttons.
                @Override
                public void handle(MouseEvent t) {
                    System.out.println(t.getSource().toString());

                    activeSubindex = 0;
                    pres.clearSeachField();
                    pres.colorChangeOnClick(t);

                    // Getting the productlist
                    ProductCategory pC = null;

                    prodList = new ArrayList<>();
                    tempProdList = new ArrayList<>();
                    subNameList = new ArrayList<>();

                    // Collects those product that should be in the Dairysection.
                    if (getCurrentPane(t).equals("CategoryDairy")) {
                        pC = ProductCategory.DAIRIES;
                        prodList = IMat_Model.getBackEnd().getProducts(pC);
                        //subNameList.add("Allt");
                        subNameList.add("Mejeri");

                        // Collects those products mareked as favorites.
                    } else if (getCurrentPane(t).equals("topCategory")) {
                        prodList = IMat_Model.getBackEnd().favorites();

                        // Collects those product that should be in the candy-section.
                    } else if (getCurrentPane(t).equals("bottomCategory")) {
                        pC = ProductCategory.SWEET;
                        prodList = IMat_Model.getBackEnd().getProducts(pC);

                        pC = ProductCategory.NUTS_AND_SEEDS;
                        tempProdList = IMat_Model.getBackEnd().getProducts(pC);

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }

                        subNameList.add("Godis");
                        subNameList.add("Nötter/Frön");

                        /* Collects those product that 
                         should be in the drinks-section.
                         */
                    } else if (getCurrentPane(t).equals("CategoryDrinks")) {
                        pC = ProductCategory.COLD_DRINKS;
                        prodList = IMat_Model.getBackEnd().getProducts(pC);

                        pC = ProductCategory.HOT_DRINKS;
                        tempProdList = IMat_Model.getBackEnd().getProducts(pC);

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }

                        subNameList.add("Kalla drycker");
                        subNameList.add("Varma drycker");

                        /* Collects those product that 
                         should be in the dry goods-section.
                         */
                    } else if (getCurrentPane(t).equals("CategoryDryGoods")) {
                        pC = ProductCategory.FLOUR_SUGAR_SALT;
                        prodList = IMat_Model.getBackEnd().getProducts(pC);

                        pC = ProductCategory.PASTA;
                        tempProdList = IMat_Model.getBackEnd().getProducts(pC);

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }

                        pC = ProductCategory.NUTS_AND_SEEDS;
                        tempProdList.clear();
                        tempProdList = IMat_Model.getBackEnd().getProducts(pC);

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }

                        pC = ProductCategory.POD;
                        tempProdList.clear();
                        tempProdList = IMat_Model.getBackEnd().getProducts(pC);

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }

                        pC = ProductCategory.POTATO_RICE;
                        tempProdList.clear();
                        tempProdList = IMat_Model.getBackEnd().getProducts(pC);
                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }
                        subNameList.add("Mjöl/Socker/Salt");
                        subNameList.add("Pasta");
                        subNameList.add("Nötter/Frön");
                        subNameList.add("Baljväxter");
                        subNameList.add("Potatis/Ris");

                        /* Collects those product that should 
                         be in the fruit and berries-section.
                         */
                    } else if (getCurrentPane(t).equals("CategoryFruit_Berries")) {
                        pC = ProductCategory.EXOTIC_FRUIT;
                        prodList = IMat_Model.getBackEnd().getProducts(pC);

                        pC = ProductCategory.FRUIT;
                        tempProdList = IMat_Model.getBackEnd().getProducts(pC);

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }
                        tempProdList.clear();
                        pC = ProductCategory.MELONS;
                        tempProdList = IMat_Model.getBackEnd().getProducts(pC);

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }
                        tempProdList.clear();
                        pC = ProductCategory.BERRY;

                        tempProdList = IMat_Model.getBackEnd().getProducts(pC);
                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }
                        tempProdList.clear();
                        pC = ProductCategory.CITRUS_FRUIT;
                        tempProdList = IMat_Model.getBackEnd().getProducts(pC);

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }
                        subNameList.add("Exotisk frukt");
                        subNameList.add("Stenfrukt");
                        subNameList.add("Meloner");
                        subNameList.add("Bär");
                        subNameList.add("Citrusfrukt");
                        /* Collects those product that should 
                         be in the meat and fish-section.
                         */
                    } else if (getCurrentPane(t).equals("CategoryMeat_Fish_Shellfish")) {
                        pC = ProductCategory.MEAT;
                        prodList = IMat_Model.getBackEnd().getProducts(pC);

                        pC = ProductCategory.FISH;
                        tempProdList = IMat_Model.getBackEnd().getProducts(pC);

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }

                        subNameList.add("Kött/Kyckling");
                        subNameList.add("Fisk");
                        /* Collects those product that should 
                         be in the vegetables-section.
                         */
                    } else if (getCurrentPane(t).equals("CategoryVegetables")) {
                        pC = ProductCategory.CABBAGE;
                        prodList = IMat_Model.getBackEnd().getProducts(pC);

                        pC = ProductCategory.HERB;
                        tempProdList = IMat_Model.getBackEnd().getProducts(pC);

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }
                        pC = ProductCategory.ROOT_VEGETABLE;
                        tempProdList.clear();
                        tempProdList = IMat_Model.getBackEnd().getProducts(pC);

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }

                        pC = ProductCategory.VEGETABLE_FRUIT;
                        tempProdList.clear();
                        tempProdList = IMat_Model.getBackEnd().getProducts(pC);

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }
                        subNameList.add("Sallad");
                        subNameList.add("Örter");
                        subNameList.add("Rotfrukter");
                        subNameList.add("Grönsaksfrukt");

                    } else if (getCurrentPane(t).equals("CategoryBread")) {
                        pC = ProductCategory.BREAD;
                        prodList = IMat_Model.getBackEnd().getProducts(pC);
                        subNameList.add("Bröd");

                    }

                    // Placing the items on the centerstage.
                    System.out.println(getCurrentPane(t));
                    currentCategory = getCurrentPane(t);

                    placeStoreItems(prodList);
                }
            };

    // Place storeItems at the centerstage.
    public void placeStoreItems(List<Product> list) {
        FlowPane flowPane = new FlowPane();
        flowPane.setVgap(6);
        flowPane.setHgap(6);
        flowPane.setPrefWidth(700);

        for (Product p : list) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("IMat_StoreItem.fxml"));
                Node storeItem = loader.load();
                IMat_StoreItemController controller = loader.getController();
                controller.setItemNameLabel(p.getName());
                controller.setItemPriceLabel(p.getPrice());
                controller.setItemImage(IMatDataHandler.getInstance().getFXImage(p));
                controller.setItemQuantity(p.getUnit());
                controller.setItemID(p.getProductId());
                if (IMat_Model.getBackEnd().isFavorite(p)) {
                    controller.setFavPic(new Image("imat/images/golden_star_trans_new.png"));
                }
                controller.setScrollPane(basketScrollPane);
                flowPane.getChildren().add(storeItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (storeItemScrollPane == null) {
            System.out.println("null");
        }

        if (currentCategory.equals("topCategory") && list.isEmpty()) {
            Text text1 = new Text("Du har inte markerat någon produkt att vara favorit ännu.");
            text1.setFont(Font.font("System", 21));
            text1.setTextAlignment(TextAlignment.JUSTIFY);
            text1.setFontSmoothingType(FontSmoothingType.LCD);
            Text text2 = new Text("\n\nTIPS: Om du vill göra detta så klickar du bara på stjärnan, bredvid den knapp som lägger en vara i kundvagnen.");
            text2.setFont(Font.font("System", 11));
            text2.setTextAlignment(TextAlignment.JUSTIFY);
            text2.setFontSmoothingType(FontSmoothingType.LCD);
            TextFlow textFlow = new TextFlow(text1, text2);
            flowPane.getChildren().add(textFlow);
            flowPane.setAlignment(Pos.CENTER);
        }

        this.storeItemScrollPane.setContent(flowPane);

        /*
         System.out.println("subNameList toString: " + subNameList.toString() + "\t subNameList size: " + subNameList.size());
         System.out.println("currentCategory: " + currentCategory);
         */
        if (!subNameList.isEmpty()) {
            placeSubItems(subNameList);
        }

        if (subNameList.isEmpty() && currentCategory.equals("topCategory")) {
            FlowPane fPane = new FlowPane();
            fPane.setHgap(6);
            fPane.setPrefWidth(640);
            fPane.setPrefHeight(104);

            Text text1 = new Text(" Dina favoriter");
            text1.setFont(Font.font("System", 42));
            text1.setFontSmoothingType(FontSmoothingType.LCD);
            TextFlow textFlow = new TextFlow(text1);
            fPane.getChildren().add(textFlow);
            fPane.setAlignment(Pos.BASELINE_CENTER);

            subScrollPane.setContent(fPane);

        } else if (subNameList.isEmpty() && currentCategory.equals("search")) {
            FlowPane flow = new FlowPane();
            flow.setHgap(6);
            flow.setPrefWidth(640);
            flow.setPrefHeight(104);

            Text text1 = new Text(" Din sökning");
            text1.setFont(Font.font("System", 42));
            text1.setFontSmoothingType(FontSmoothingType.LCD);
            TextFlow textFlow = new TextFlow(text1);
            flow.getChildren().add(textFlow);
            flow.setAlignment(Pos.BASELINE_CENTER);

            subScrollPane.setContent(flow);
        }

    }

    public void initCenter() {
        FlowPane flowPane = new FlowPane();
        flowPane.setVgap(6);
        flowPane.setHgap(6);
        flowPane.setPrefWidth(700);
        flowPane.setStyle("-fx-background-color: #FFFFFF;");

        flowPane.getChildren().add(new ImageView("imat/images/shoppingCart2.jpg"));

        this.storeItemScrollPane.setContent(flowPane);
    }

    // Place storeItems at the centerstage.
    public void upDateCenter(ProductCategory prod) {
        FlowPane flowPane = new FlowPane();
        flowPane.setVgap(6);
        flowPane.setHgap(6);
        flowPane.setPrefWidth(700);

        List<Product> list = IMat_Model.getBackEnd().getProducts(prod);

        for (Product p : list) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("IMat_StoreItem.fxml"));
                Node storeItem = loader.load();
                IMat_StoreItemController controller = loader.getController();
                controller.setItemNameLabel(p.getName());
                controller.setItemPriceLabel(p.getPrice());
                controller.setItemImage(IMatDataHandler.getInstance().getFXImage(p));
                controller.setItemQuantity(p.getUnit());
                controller.setItemID(p.getProductId());
                if (IMat_Model.getBackEnd().isFavorite(p)) {
                    controller.setFavPic(new Image("imat/images/golden_star_trans_new.png"));
                }
                controller.setScrollPane(basketScrollPane);
                flowPane.getChildren().add(storeItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (storeItemScrollPane == null) {
            System.out.println("null");
        }

        this.storeItemScrollPane.setContent(flowPane);
        placeSubItems(subNameList);

    }

    public void placeSubItems(List<String> list) {
        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(6);
        flowPane.setPrefWidth(640);
        flowPane.setPrefHeight(104);

        if (!list.isEmpty()) {
            int i = 1;
            for (String p : list) {

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("IMat_Subcategory.fxml"));
                    Node storeItem = loader.load();
                    IMat_SubcategoryController controller = loader.getController();
                    controller.setSubName(p);
                    controller.setIndex(i);
                    controller.setSubImage(getSubImage(p));
                    if (i == activeSubindex) {
                        controller.getAnchorPane();
                    }
                    i++;
                    flowPane.getChildren().add(storeItem);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /*
         if (currentCategory.equals("topCategory")) {
         Text text1 = new Text(" Dina favoriter");
         text1.setFont(Font.font("System", 42));
         text1.setFontSmoothingType(FontSmoothingType.LCD);
         TextFlow textFlow = new TextFlow(text1);
         flowPane.getChildren().add(textFlow);
         flowPane.setAlignment(Pos.BASELINE_CENTER);
         }
         */
        if (storeItemScrollPane == null) {
            System.out.println("null");
        }
        flowPane.setAlignment(Pos.CENTER);

        this.subScrollPane.setContent(flowPane);

    }

    private Image getSubImage(String s) {
        if (s.equals("Mejeri")) {

            return (new Image("imat/images/sub/dairy.png"));

        } else if (s.equals("Godis")) {

            return (new Image("imat/images/sub/sweets.png"));

        } else if (s.equals("Nötter/Frön")) {

            return (new Image("imat/images/sub/nuts.png"));

        } else if (s.equals("Kalla drycker")) {

            return (new Image("imat/images/sub/cold-drinks.png"));

        } else if (s.equals("Varma drycker")) {

            return (new Image("imat/images/sub/hot-drinks.png"));

        } else if (s.equals("Mjöl/Socker/Salt")) {

            return (new Image("imat/images/sub/flour.png"));

        } else if (s.equals("Pasta")) {

            return (new Image("imat/images/sub/pasta.png"));

        } else if (s.equals("Baljväxter")) {

            return (new Image("imat/images/sub/balj.png"));

        } else if (s.equals("Potatis/Ris")) {

            return (new Image("imat/images/sub/potato.png"));

        } else if (s.equals("Exotisk frukt")) {

            return (new Image("imat/images/sub/exotic.png"));

        } else if (s.equals("Stenfrukt")) {

            return (new Image("imat/images/sub/sten.png"));

        } else if (s.equals("Meloner")) {

            return (new Image("imat/images/sub/melon.png"));

        } else if (s.equals("Bär")) {

            return (new Image("imat/images/sub/berries.png"));

        } else if (s.equals("Citrusfrukt")) {

            return (new Image("imat/images/sub/citrus.png"));

        } else if (s.equals("Kött/Kyckling")) {

            return (new Image("imat/images/sub/meat.png"));

        } else if (s.equals("Fisk")) {

            return (new Image("imat/images/sub/fish.png"));

        } else if (s.equals("Sallad")) {

            return (new Image("imat/images/sub/sallad.png"));

        } else if (s.equals("Örter")) {

            return (new Image("imat/images/sub/herbs.png"));

        } else if (s.equals("Rotfrukter")) {

            return (new Image("imat/images/sub/root.png"));

        } else if (s.equals("Grönsaksfrukt")) {

            return (new Image("imat/images/sub/cucumber.png"));

        } else if (s.equals("Bröd")) {

            return (new Image("imat/images/sub/bread.png"));

        }
        return (new Image("imat/images/sub/dairy.png"));
    }

    // Sets the totalSum in the GUI
    public void setTotal() {
        double d = Math.round(IMat_Model.getBackEnd().getShoppingCart().getTotal() * 100.0) / 100.0;
        totalPrice.setText(Double.toString(d) + " kr");
    }

    // Returns the presenter made in this class.
    public static IMat_presenter getPresenter() {
        return pres;
    }

    public static void activeSubIndex(int i) {
        activeSubindex = i;
    }
}
