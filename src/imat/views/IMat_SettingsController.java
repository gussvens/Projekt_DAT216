/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import imat.IMat;
import imat.IMat_Model;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.IMatDataHandler;

/**
 * FXML Controller class
 *
 * @author Group 12
 */
public class IMat_SettingsController implements Initializable {

    @FXML
    private Button homeButton;

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField city;
    @FXML
    private TextField address;
    @FXML
    private TextField postCode;
    @FXML
    private ComboBox payment;
    @FXML
    private ComboBox cardType;
    @FXML
    private TextField card1;
    @FXML
    private TextField card2;
    @FXML
    private TextField card3;
    @FXML
    private TextField card4;
    @FXML
    private TextField cvc;
    @FXML
    private ComboBox deliveryDay;
    @FXML
    private TextArea comment;
    @FXML
    private Button saveSettings;

    
    private static String staticCity;
    private static String staticCard1;
    private static String staticCard2;
    private static String staticCard3;
    private static String staticCard4;
    private static String staticCvc;

    private Customer c = IMat_Model.getBackEnd().getCustomer();

    /*
     Should try to connect all data to a single customer, which
     can be accessed from both here and from the checkout
     */
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        c = IMat_Model.getBackEnd().getCustomer();
        firstName.setText(c.getFirstName());
        lastName.setText(c.getLastName());
        address.setText(c.getAddress());
        postCode.setText(c.getPostCode());
        city.setText(staticCity);
        card1.setText(staticCard1);
        card2.setText(staticCard2);
        card3.setText(staticCard3);
        card4.setText(staticCard4);
        cvc.setText(staticCvc);
        saveSettings.setOnMouseClicked(onSaveButtonClick);
        
        
        /*
        List<String> list = new ArrayList<String>();
        list.add("Item A");
        list.add("Item B");
        list.add("Item C");
        ObservableList obList = FXCollections.observableList(list);
        cardType.getItems().clear();
        cardType.setItems(obList);
        //cardType.getItems().addAll("Visa");
        */
        
    }

    
    EventHandler<MouseEvent> onSaveButtonClick
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    setCity();
                    setCard1();
                    setCard2();
                    setCard3();
                    setCard4();
                    setCvc();
                }
            };
    
    
    
    
    @FXML
    private void homeButtonClicked() throws IOException {
        Parent start = FXMLLoader.load(getClass().getResource("IMat_Start_v2.fxml"));
        IMat.getStage().setScene(new Scene(start, 1360, 768));
    }

    @FXML
    public void setFirstName() {
        c.setFirstName(firstName.getText());
    }

    @FXML
    public void setLastName() {
        c.setLastName(lastName.getText());
    }

    @FXML
    public void setAddress() {
        c.setAddress(address.getText());
    }

    @FXML
    public void setPostCode() {
        c.setPostCode(postCode.getText());
    }

    @FXML
    public void setPayment() {
        //No payment-variable in backend
    }

    @FXML
    public void setCardType() {
        //No cardtype-variable in backend
    }


    public void setDeliveryDay() {
        //No delivery day variable in backend
    }

    public void setComment() {
        //No Comment variable in backend
    }

    
    
    
    
    
    
    
    
    // Setters for the static variables
    public void setCity() {
        staticCity = city.getText();
    }
    
    public void setCard1() {
        staticCard1 = card1.getText();
    }

    public void setCard2() {
        staticCard2 = card2.getText();
    }

    public void setCard3() {
        staticCard3 = card3.getText();
    }

    public void setCard4() {
        staticCard4 = card4.getText();
    }

    public void setCvc() {
        staticCvc = cvc.getText();
    }
    
    
    
    // Getters for the static variables.
    public static String getCity() {
        return staticCity;
    }

    public static String getCard1() {
        return staticCard1;
    }

    public static String getCard2() {
        return staticCard3;
    }

    public static String getCard3() {
        return staticCard3;
    }

    public static String getCard4() {
        return staticCard4;
    }

    public static String getCvc() {
        return staticCvc;
    }

}
