/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.views;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import imat.IMat;
import imat.IMat_Model;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import se.chalmers.ait.dat215.project.*;

/**
 * FXML Controller class
 *
 * @author Gustav
 */
public class IMat_HistoryController implements Initializable, ShoppingCartListener {

    @FXML
    private Pane storeButton;
    @FXML
    private Pane settingsButton;
    private List<IMat_HistoryCategoriesModel> models = new ArrayList<>();
    @FXML
    private Pane historyCategoryPane;

    @FXML
    private ScrollPane historyItemScrollPane;
    @FXML
    private ScrollPane basketScrollPane;
    @FXML
    private Button toCheckout;
    @FXML
    private Button removeAllFromBasket;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        IMat_Model.getBackEnd().getShoppingCart().addShoppingCartListener(this);

        storeButton.setOnMouseClicked(storeButtonClicked);
        settingsButton.setOnMouseClicked(settingsButtonClicked);

        FlowPane flowPane = new FlowPane();
        flowPane.setVgap(6);
        flowPane.setHgap(6);
        flowPane.setPrefWidth(250);

        initCenter();

        if(IMat_Model.getBackEnd().getOrders().isEmpty()){
            Label emptyLabel = new Label();
            emptyLabel.setText("Ingen historik tillgänglig");
            flowPane.getChildren().add(emptyLabel);
        }

        List<Order> orderList = IMat_Model.getBackEnd().getOrders();
        if (orderList.size() >= 9) {
            for (int i = orderList.size() - 1; i >= orderList.size() - 10;
                 i--) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("IMat_HistoryCategories.fxml"));
                    Node historyCategory = loader.load();
                    IMat_HistoryCategoriesController controller = loader.getController();
                    controller.setDateLabel(orderList.get(i).getDate().getYear() + 1900 + "-" + orderList.get(i).getDate().getMonth() + 1
                            + "-" + orderList.get(i).getDate().getDate());
                    //Should be a class for the content of the specific order
                    controller.setOrder(orderList.get(i));
                    controller.setCont(this);
                    flowPane.getChildren().add(historyCategory);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            int j = orderList.size() - 1;
            while (j >= 0) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("IMat_HistoryCategories.fxml"));
                    Node historyCategory = loader.load();
                    IMat_HistoryCategoriesController controller = loader.getController();
                    controller.setDateLabel(orderList.get(j).getDate().getYear() + 1900 + "-" + (orderList.get(j).getDate().getMonth() + 1)
                            + "-" + (orderList.get(j).getDate().getDate()));                    //Should be a class for the content of the specific order
                    controller.setOrder(orderList.get(j));
                    controller.setCont(this);
                    flowPane.getChildren().add(historyCategory);
                    j--;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        historyCategoryPane.getChildren().add(flowPane);

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



    public void setHistoryItemScrollPane(FlowPane flowPane){
        historyItemScrollPane.setContent(flowPane);
    }

    EventHandler<MouseEvent> storeButtonClicked
            = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            try {
                Parent start = FXMLLoader.load(getClass().getResource("IMat_Store_v2.fxml"));
                IMat.getStage().setScene(new Scene(start, 1360, 768));
            } catch (IOException ex) {
                Logger.getLogger(IMat_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    public void initCenter() {
        FlowPane flowPane = new FlowPane();
        flowPane.setVgap(6);
        flowPane.setHgap(6);
        flowPane.setPrefWidth(700);
        flowPane.setStyle("-fx-background-color: #FFFFFF;");

        flowPane.getChildren().add(new ImageView("imat/images/history2_big.png"));

        this.historyItemScrollPane.setContent(flowPane);
    }

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


        if (IMat_Model.getBackEnd().getShoppingCart().getItems().isEmpty()) {
            toCheckout.setDisable(true);
            removeAllFromBasket.setDisable(true);
            // setSaveListButtonInctive();
        } else {
            toCheckout.setDisable(false);
            removeAllFromBasket.setDisable(false);
            //setSaveListButtonActive();
        }
    }

}
