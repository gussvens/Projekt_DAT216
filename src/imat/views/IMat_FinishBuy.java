/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.views;

import imat.IMat_Checkout_presenter;
import imat.IMat_Model;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;


/**
 * FXML Controller class
 *
 */
public class IMat_FinishBuy implements Initializable {
    @FXML
    private TextField checkoutTotPrice1;
    @FXML
    private ScrollPane basketScrollPane;
    @FXML
    private Label adressnchill;
    String personalInfo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        checkoutTotPrice1.setText(Double.toString(IMat_Model.getBackEnd().getShoppingCart().getTotal()) + " kr");

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
        this.adressnchill.setText(IMat_CheckOut_v2Controller.getPersonalInfo());

    }
    
}
