/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.views;

import imat.IMat;
import imat.IMat_Checkout_presenter;
import imat.IMat_Model;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;


/**
 * FXML Controller class
 *
 */
public class IMat_FinishBuy implements Initializable {
    @FXML
    private TextField checkoutTotPrice;
    @FXML
    private ScrollPane basketScrollPane;
    @FXML
    private Label adressnchill;
    @FXML
    private Label timenchill;
    @FXML
    private Label message;
    @FXML
    private Pane confirmButton;
    @FXML
    private Pane backButton;
    @FXML
    private Pane backToStore;
    @FXML
    private Pane backToHistory;
    @FXML
    private Pane backToSettings;
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        checkoutTotPrice.setText(Double.toString(IMat_Model.getBackEnd().getShoppingCart().getTotal()) + " kr");

        FlowPane receiptFlowPane = new FlowPane();
        receiptFlowPane.setVgap(3);
        receiptFlowPane.setPrefWidth(255);

        for (ShoppingItem s : IMat_Model.getBackEnd().getShoppingCart().getItems()) {
            try {
                Product p = s.getProduct();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("IMat_Receipt.fxml"));
                Node storeItem = loader.load();
                IMat_ReceiptController controller = loader.getController();
                controller.setReceiptProdName(p.getName());
                controller.setReceiptProdPrice(p.getPrice() * s.getAmount());
                controller.setReceiptProdQuant("kr");
                controller.setNrOfReceiptItems(s.getAmount());
                receiptFlowPane.getChildren().add(storeItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        basketScrollPane.setContent(receiptFlowPane);

        Customer c = IMat_Model.getBackEnd().getCustomer();
        
        System.out.print(IMat_CheckOut_v2Controller.getPersonalInfo());
        this.adressnchill.setText(IMat_CheckOut_v2Controller.getPersonalInfo());
        this.timenchill.setText(IMat_DeliveryController.getStaticDaytime());
        this.message.setText(IMat_DeliveryController.getStaticMessage());

        backToStore.setOnMouseClicked(backToStoreClicked);
        backToHistory.setOnMouseClicked(backToHistoryClicked);
        backToSettings.setOnMouseClicked(backToSettingsClicked);
        confirmButton.setOnMouseClicked(confirmButtonClicked);
        backButton.setOnMouseClicked(backButtonClicked);

    }

    EventHandler<MouseEvent> backButtonClicked
            = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            try {
                Parent start = FXMLLoader.load(getClass().getResource("IMat_Delivery.fxml"));
                IMat.getStage().setScene(new Scene(start, 1360, 768));
            } catch (IOException ex) {
                Logger.getLogger(IMat_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    EventHandler<MouseEvent> backToStoreClicked
            = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            try {
                Parent start = FXMLLoader.load(getClass().getResource("IMat_Store_v2.fxml"));
                IMat.getStage().setScene(new Scene(start, 1360, 768));
            } catch (IOException ex) {
                Logger.getLogger(IMat_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    EventHandler<MouseEvent> backToHistoryClicked
            = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            try {
                Parent start = FXMLLoader.load(getClass().getResource("IMat_History.fxml"));
                IMat.getStage().setScene(new Scene(start, 1360, 768));
            } catch (IOException ex) {
                Logger.getLogger(IMat_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    EventHandler<MouseEvent> backToSettingsClicked
            = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            try {
                Parent start = FXMLLoader.load(getClass().getResource("IMat_Settings.fxml"));
                IMat.getStage().setScene(new Scene(start, 1360, 768));
            } catch (IOException ex) {
                Logger.getLogger(IMat_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    EventHandler<MouseEvent> confirmButtonClicked
            = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            try {
                IMat_Model.getBackEnd().placeOrder();
                IMat_Model.getBackEnd().getShoppingCart().clear();
                Parent start = FXMLLoader.load(getClass().getResource("IMat_Store_v2.fxml"));
                IMat.getStage().setScene(new Scene(start, 1360, 768));
            } catch (IOException ex) {
                Logger.getLogger(IMat_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };
 
    
}
