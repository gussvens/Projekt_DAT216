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
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
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
import se.chalmers.ait.dat215.project.*;

/**
 *
 * @author Group 12
 */
public class IMat_FXMLController implements Initializable, ShoppingCartListener {

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
    private Button homeButton;
    @FXML
    private Button historyButton;
    @FXML
    private Button settingsButton;
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

    private List<Product> prodList;
    private List<Product> tempProdList;
    private List<Pane> menuButtonList;
    private List<String> subNameList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        totalPrice.setText(Double.toString(IMat_Model.getBackEnd().getShoppingCart().getTotal()));

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

        // Sets listeners to the buttons
        searchButton.setOnMouseClicked(searchButtonClicked);
        toCheckout.setOnMouseClicked(checkoutButtonClicked);
        removeAllFromBasket.setOnMouseClicked(setBasketEmpty);
//        saveAsListButton.setOnMouseClicked(saveList);
        searchField.setOnKeyPressed(searchEnterPressed);
//        homeButton.setOnMouseClicked(homeButtonClicked);
        historyButton.setOnMouseClicked(historyButtonClicked);
        settingsButton.setOnMouseClicked(settingsButtonClicked);

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
    }


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
                        Parent start = FXMLLoader.load(getClass().getResource("IMat_CheckOut_v2.fxml"));
                        IMat.getStage().setScene(new Scene(start, 1360, 768));
                    } catch (IOException ex) {
                        Logger.getLogger(IMat_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };


    EventHandler<MouseEvent> homeButtonClicked
            = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            try {
                Parent start = FXMLLoader.load(getClass().getResource("IMat_Start_v2.fxml"));
                IMat.getStage().setScene(new Scene(start, 1360, 768));
            } catch (IOException ex) {
                Logger.getLogger(IMat_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    EventHandler<MouseEvent> historyButtonClicked
            = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            try {
                Parent start = FXMLLoader.load(getClass().getResource("IMat_History.fxml"));
                IMat.getStage().setScene(new Scene(start, 1360, 768));
            } catch (IOException ex) {
                Logger.getLogger(IMat_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    EventHandler<MouseEvent> settingsButtonClicked
            = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            try {
                Parent start = FXMLLoader.load(getClass().getResource("IMat_Settings.fxml"));
                IMat.getStage().setScene(new Scene(start, 1360, 768));
            } catch (IOException ex) {
                Logger.getLogger(IMat_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    public void shoppingCartChanged(CartEvent evt){
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

    private String getCurrentPane(MouseEvent t) {
        return ((Pane) t.getSource()).getId();
    }

    EventHandler<KeyEvent> searchEnterPressed
            = new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent ke) {

                    if (ke.getCode().equals(KeyCode.ENTER)) {
                        subNameList = new ArrayList<>();
                        List<Product> list = new ArrayList<>();
                        list = IMatDataHandler.getInstance().
                        findProducts(searchField.getText());

                        placeStoreItems(list);

                        // TODO: if the list is empty, add text that displays this.
                    }

                }
            };

    // Searchfunction
    EventHandler<MouseEvent> searchButtonClicked
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    subNameList = new ArrayList<>();
                    List<Product> list = new ArrayList<>();
                    list = IMatDataHandler.getInstance().
                    findProducts(searchField.getText());

                    placeStoreItems(list);

                    // TODO: if the list is empty, add text that displays this.
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
                        System.out.println("i fav ifen");


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
                    controller.setFavPic(new Image("imat/images/golden_star.jpg"));
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
                    controller.setFavPic(new Image("imat/images/golden_star.jpg"));
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

        int i = 1;
        for (String p : list) {
            
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("IMat_Subcategory.fxml"));
                Node storeItem = loader.load();
                IMat_SubcategoryController controller = loader.getController();
                controller.setSubName(p);
                controller.setIndex(i);
                controller.setSubImage(getSubImage(p));
                if(i == activeSubindex){
                    controller.getAnchorPane();
                }
                i++;
                flowPane.getChildren().add(storeItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (storeItemScrollPane == null) {
            System.out.println("null");
        }
        flowPane.setOrientation(Orientation.HORIZONTAL);

        this.subScrollPane.setContent(flowPane);

    }

    private Image getSubImage(String s){
        if(s.equals("Mejeri")){

            return(new Image("imat/images/iconer/dairy.png"));

        } else if(s.equals("Godis")){

            return(new Image("imat/images/iconer/sweets.png"));

        } else if(s.equals("Nötter/Frön")){

            return(new Image("imat/images/iconer/nuts_seeds.png"));

        } else if(s.equals("Kalla drycker")){

            return(new Image("imat/images/iconer/cold_drinks.png"));

        } else if(s.equals("Varma drycker")){

            return(new Image("imat/images/sub/hot-drinks.png"));

        } else if(s.equals("Mjöl/Socker/Salt")){

            return(new Image("imat/images/sub/flour.png"));

        } else if(s.equals("Pasta")){

            return(new Image("imat/images/sub/pasta.png"));

        } else if(s.equals("Baljväxter")){

            return(new Image("imat/images/sub/balj.png"));

        } else if(s.equals("Potatis/Ris")){

            return(new Image("imat/images/sub/potato.png"));

        } else if(s.equals("Exotisk frukt")){

            return(new Image("imat/images/sub/exotic.png"));

        } else if(s.equals("Stenfrukt")){

            return(new Image("imat/images/sub/sten.png"));

        } else if(s.equals("Meloner")){

            return(new Image("imat/images/sub/melon.png"));

        } else if(s.equals("Bär")){

            return(new Image("imat/images/sub/berries.png"));

        } else if(s.equals("Citrusfrukt")){

            return(new Image("imat/images/sub/citrus.png"));

        } else if(s.equals("Kött/Kyckling")){

            return(new Image("imat/images/sub/meat.png"));

        } else if(s.equals("Fisk")){

            return(new Image("imat/images/sub/fish.png"));

        } else if(s.equals("Sallad")){

            return(new Image("imat/images/sub/sallad.png"));

        } else if(s.equals("Örter")){

            return(new Image("imat/images/sub/herbs.png"));

        } else if(s.equals("Rotfrukter")){

            return(new Image("imat/images/sub/root.png"));

        } else if(s.equals("Grönsaksfrukt")){

            return(new Image("imat/images/sub/cucumber.png"));

        } else if(s.equals("Bröd")){

            return(new Image("imat/images/sub/bread.png"));

        }
        return(new Image("imat/images/sub/dairy.png"));
    }



    // Sets the totalSum in the GUI
    public void setTotal() {
        totalPrice.setText(Double.toString(IMat_Model.getBackEnd().getShoppingCart().getTotal()));
        System.out.println(totalPrice.getText());
    }

    // Returns the presenter made in this class.
    public static IMat_presenter getPresenter() {
        return pres;
    }
    
    public static void activeSubIndex(int i){
        activeSubindex = i;
    }
}
