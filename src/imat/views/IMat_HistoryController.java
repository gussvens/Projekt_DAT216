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

import imat.IMat;
import imat.IMat_Model;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
    private Button homeButton;
    private List<IMat_HistoryCategoriesModel> models = new ArrayList<>();
    @FXML
    private Pane historyCategoryPane;

  /* Hitting a wall with presenting
    the history
    @FXML
    private Label dateLabel1;
    @FXML
    private Label dateLabel2;
    @FXML
    private Label dateLabel3;
    @FXML
    private Label dateLabel4;
    @FXML
    private Label dateLabel5;
    @FXML
    private Label dateLabel6;
    @FXML
    private Label dateLabel7;
    @FXML
    private Label dateLabel8;
    @FXML
    private Label dateLabel9;

    private List<Label> labelList = new ArrayList<>();*/

    @FXML
    private ScrollPane historyItemScrollPane;
    @FXML
    private ScrollPane basketScrollPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        IMat_Model.getBackEnd().getShoppingCart().addShoppingCartListener(this);

        FlowPane flowPane = new FlowPane();
        flowPane.setVgap(6);
        flowPane.setHgap(6);
        flowPane.setPrefWidth(250);

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
    }



    public void setHistoryItemScrollPane(FlowPane flowPane){
        historyItemScrollPane.setContent(flowPane);
    }

    @FXML
    private void homeButtonClicked() throws IOException {
        Parent start = FXMLLoader.load(getClass().getResource("IMat_Start_v2.fxml"));
        IMat.getStage().setScene(new Scene(start, 1360, 768));
    }

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

}
