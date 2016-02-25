/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.views;

import imat.IMat;
import imat.IMat_StoreItemController;
import imat.IMat_presenter;
import imat.IMat_presenter;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import se.chalmers.ait.dat215.project.Product;

public class IMat_FXMLController implements Initializable {

    private IMat_presenter pres;
    //private List<Pane> menuButtonsList;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pres = new IMat_presenter(
                CategoryDariy,
                CategoryVegetables,
                CategoryFruit_Berries,
                CategoryDryGoods,
                CategoryMeat_Fish_Shellfish,
                CategoryDrinks,
                CategoryCandy_Snacks,
                searchButton);
        
        
    }


    @FXML
    private void homeButtonClicked() throws IOException {
        Parent start = FXMLLoader.load(getClass().getResource("IMat_Start_v2.fxml"));
        IMat.getStage().setScene(new Scene(start, 1360, 768));
    }

    public void placeStoreItems(List<Product> list){
        FlowPane flowPane = new FlowPane();
        for(Product p : list){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("IMat_StoreItem.fxml"));
                Node storeItem = loader.load();
                IMat_StoreItemController controller = loader.getController();
                controller.setItemNameLabel(p.getName());
                controller.setItemPriceLabel(p.getPrice());
                flowPane.getChildren().add(storeItem);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        this.storeItemScrollPane.getChildrenUnmodifiable().add(flowPane);
    }
}
