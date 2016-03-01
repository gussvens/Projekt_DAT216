/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.views;

import imat.*;
import imat.IMat_StoreItemController;
import imat.IMat_presenter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import se.chalmers.ait.dat215.project.*;

public class IMat_FXMLController implements Initializable {

    private IMat_presenter pres;

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
    private ScrollPane storeItemScrollPane;
    @FXML
    private ScrollPane basketScrollPane;
    @FXML
    private TextField totalPrice;

    private List<Product> prodList;
    private List<Product> tempProdList;
    private List<Pane> menuButtonList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
                totalPrice
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

        for (Pane p : menuButtonList) {
            p.setOnMouseClicked(menuButtonClicked);
        }
        searchButton.setOnMouseClicked(searchButtonClicked);

    }

    @FXML
    private void homeButtonClicked() throws IOException {
        Parent start = FXMLLoader.load(getClass().getResource("IMat_Start_v2.fxml"));
        IMat.getStage().setScene(new Scene(start, 1360, 768));
    }

    private String getCurrentPane(MouseEvent t) {
        return ((Pane) t.getSource()).getId();
    }

    // Searchfunction
    EventHandler<MouseEvent> searchButtonClicked
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    List<Product> list = new ArrayList<>();
                    list = IMatDataHandler.getInstance().
                    findProducts(searchField.getText());

                    if (!list.isEmpty()) {
                        placeStoreItems(list);
                    }
                }
            };

    EventHandler<MouseEvent> menuButtonClicked
            = new EventHandler<MouseEvent>() {

                // Sets functionality on each of the menubuttons.
                @Override
                public void handle(MouseEvent t) {
                    pres.colorChangeOnClick(t);

                    // Getting the productlist
                    ProductCategory pC = null;

                    prodList = new ArrayList<>();
                    tempProdList = new ArrayList<>();

                    // Collects those product that should be in the Dairysection.
                    if (getCurrentPane(t).equals("CategoryDairy")) {
                        pC = ProductCategory.DAIRIES;
                        prodList = IMat_Model.getBackEnd().getProducts(pC);

                        // Collects those product that should be in the candy-section.
                    } else if (getCurrentPane(t).equals("CategoryCandy_Snacks")) {
                        pC = ProductCategory.SWEET;
                        prodList = IMat_Model.getBackEnd().getProducts(pC);

                        pC = ProductCategory.NUTS_AND_SEEDS;
                        tempProdList = IMat_Model.getBackEnd().getProducts(pC);

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }
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
                    } else if (getCurrentPane(t).equals("CategoryBread")) {
                        pC = ProductCategory.BREAD;
                        prodList = IMat_Model.getBackEnd().getProducts(pC);
                    }

                    // Placing the items on the centerstage.
                    placeStoreItems(prodList);

                    // FOR TESTING... REMOVE WHEN DONE.
                    for (Product p : prodList) {
                        System.out.println(p.getName());
                    }

                    System.out.println("\n");
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

    }

    /*    public void placeBasketItems(List<ShoppingItem> list) {
     FlowPane flowPane = new FlowPane();
     flowPane.setVgap(6);
     flowPane.setHgap(6);
     flowPane.setPrefWidth(250);

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

     if(basketScrollPane == null) {
     System.out.println("fek ye");
     }
     this.basketScrollPane.setContent(flowPane);
     System.out.println("Slut");
     }*/
    public void setTotal() {
        totalPrice.setText(Double.toString(IMat_Model.getBackEnd().getShoppingCart().getTotal()));
        System.out.println(totalPrice.getText());
    }
}
