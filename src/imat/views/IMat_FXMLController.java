/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.views;

import imat.*;
import imat.IMat_StoreItemController;
import imat.IMat_presenter;
import java.awt.event.ActionEvent;
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
import javafx.scene.layout.AnchorPane;

import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ProductCategory;

public class IMat_FXMLController implements Initializable {

    private IMat_Model model;
    private IMat_presenter pres;

    // Imports from sceneBuilder.
    @FXML
    private Pane CategoryDariy;
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
    private Button searchButton;
    @FXML
    private Button homeButton;
    @FXML
    private ScrollPane storeItemScrollPane;
    @FXML
    private FlowPane storeFlowPane;

    private List<Product> prodList;
    private List<Product> tempProdList;
    private List<Pane> menuButtonList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = new IMat_Model();
        pres = new IMat_presenter(
                CategoryDariy,
                CategoryVegetables,
                CategoryFruit_Berries,
                CategoryDryGoods,
                CategoryMeat_Fish_Shellfish,
                CategoryDrinks,
                CategoryCandy_Snacks,
                searchButton
        );

        menuButtonList = new ArrayList<>();
        menuButtonList.add(CategoryCandy_Snacks);
        menuButtonList.add(CategoryDariy);
        menuButtonList.add(CategoryDrinks);
        menuButtonList.add(CategoryDryGoods);
        menuButtonList.add(CategoryFruit_Berries);
        menuButtonList.add(CategoryMeat_Fish_Shellfish);
        menuButtonList.add(CategoryVegetables);

        for (Pane p : menuButtonList) {
            p.setOnMouseClicked(menuButtonClicked);
        }
    }

    @FXML
    private void homeButtonClicked() throws IOException {
        Parent start = FXMLLoader.load(getClass().getResource("IMat_Start_v2.fxml"));
        IMat.getStage().setScene(new Scene(start, 1360, 768));
    }

    private String getCurrentPane(MouseEvent t) {
        return ((Pane) t.getSource()).getId();
    }

    EventHandler<MouseEvent> menuButtonClicked
            = new EventHandler<MouseEvent>() {

                // Sets functionality on each of the menubuttons.
                @Override
                public void handle(MouseEvent t) {
                    pres.colorChangeOnClick(t);
                    
                    
                    
                    // Getting the productlists
                    ProductCategory pC = null;

                    prodList = new ArrayList<>();
                    tempProdList = new ArrayList<>();

                    if (getCurrentPane(t).equals("CategoryDariy")) {
                        pC = ProductCategory.DAIRIES;
                        prodList = model.getProducts(pC);
                    } else if (getCurrentPane(t).equals("CategoryCandy_Snacks")) {
                        pC = ProductCategory.SWEET;
                        prodList = model.getProducts(pC);

                        pC = ProductCategory.NUTS_AND_SEEDS;
                        tempProdList = model.getProducts(pC);

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }
                    } else if (getCurrentPane(t).equals("CategoryDrinks")) {
                        pC = ProductCategory.COLD_DRINKS;
                        prodList = model.getProducts(pC);

                        pC = ProductCategory.HOT_DRINKS;
                        tempProdList = model.getProducts(pC);

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }
                    } else if (getCurrentPane(t).equals("CategoryDryGoods")) {
                        pC = ProductCategory.FLOUR_SUGAR_SALT;
                        prodList = model.getProducts(pC);

                        pC = ProductCategory.PASTA;
                        tempProdList = model.getProducts(pC);

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }

                        pC = ProductCategory.NUTS_AND_SEEDS;
                        tempProdList = model.getProducts(pC);

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }

                        pC = ProductCategory.POD;
                        tempProdList = model.getProducts(pC);

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }

                        pC = ProductCategory.POTATO_RICE;
                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }

                    } else if (getCurrentPane(t).equals("CategoryFruit_Berries")) {
                        pC = ProductCategory.EXOTIC_FRUIT;
                        prodList = model.getProducts(pC);

                        pC = ProductCategory.FRUIT;
                        tempProdList = model.getProducts(pC);

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }

                        pC = ProductCategory.MELONS;

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }

                        pC = ProductCategory.BERRY;

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }

                        pC = ProductCategory.CITRUS_FRUIT;

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }
                    } else if (getCurrentPane(t).equals("CategoryMeat_Fish_Shellfish")) {
                        pC = ProductCategory.MEAT;
                        prodList = model.getProducts(pC);

                        pC = ProductCategory.FISH;
                        tempProdList = model.getProducts(pC);

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }
                    } else if (getCurrentPane(t).equals("CategoryVegetables")) {
                        pC = ProductCategory.CABBAGE;
                        prodList = model.getProducts(pC);

                        pC = ProductCategory.HERB;
                        tempProdList = model.getProducts(pC);

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }
                        pC = ProductCategory.ROOT_VEGETABLE;

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }

                        pC = ProductCategory.VEGETABLE_FRUIT;
                    }

                    placeStoreItems();

                    // FOR TESTING... REMOVE WHEN DONE.
                    for (Product p : prodList) {
                        System.out.println(p.getName());
                    }

                    System.out.println("\n");
                }
            };

    public void placeStoreItems() {
        FlowPane flowPane = new FlowPane();
        flowPane.setVgap(6);
        flowPane.setHgap(6);
        flowPane.setPrefWidth(700);

        for (Product p : prodList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("IMat_StoreItem.fxml"));
                Node storeItem = loader.load();
                IMat_StoreItemController controller = loader.getController();
                controller.setItemNameLabel(p.getName());
                controller.setItemPriceLabel(p.getPrice());
                controller.setItemImage(IMatDataHandler.getInstance().getFXImage(p));
                flowPane.getChildren().add(storeItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.storeItemScrollPane.setContent(flowPane);
    }

}
