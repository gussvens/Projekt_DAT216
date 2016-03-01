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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.IMatDataHandler;

/**
 * FXML Controller class
 *
 * @author Gustav
 */
public class IMat_SettingsController implements Initializable {


    @FXML private Button homeButton;

    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField city;
    @FXML private TextField address;
    @FXML private TextField zipCode;
    @FXML private ComboBox payment;
    @FXML private ComboBox cardType;
    @FXML private TextField card1;
    @FXML private TextField card2;
    @FXML private TextField card3;
    @FXML private TextField card4;
    @FXML private TextField cvc;
    @FXML private ComboBox deliveryDay;
    @FXML private TextArea comment;

    private Customer c = IMat_Model.getBackEnd().getCustomer();

    /*
    Should try to connect all data to a customer, which
    can be accessed from both here and from the checkout
     */

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        c = IMatDataHandler.getInstance().getCustomer();
    }

    @FXML
    private void homeButtonClicked() throws IOException {
        Parent start = FXMLLoader.load(getClass().getResource("IMat_Start_v2.fxml"));
        IMat.getStage().setScene(new Scene(start, 1360, 768));
    }

    @FXML
    public void setFirstName(String s){
        c.setFirstName(s);
        firstName.setText(c.getFirstName());
    }
    @FXML
    public void setLastName(String s){
        lastName.setText(s);
    }
    @FXML
    public void setCity(String s){
        city.setText(s);
    }
    @FXML
    public void setAddress(String s){
        address.setText(s);
    }
    @FXML
    public void setZipCode(String s){
        zipCode.setText(s);
    }
    @FXML
    public void setPayment(String s){
        //TODO
    }
    @FXML
    public void setCardType(String s){
        //TODO
    }@FXML
    public void setCard1(String s){
        card1.setText(s);
    }
    @FXML
    public void setCard2(String s){
        card2.setText(s);
    }
    @FXML
    public void setCard3(String s){
        card3.setText(s);
    }
    @FXML
    public void setCard4(String s){
        card4.setText(s);
    }
    @FXML
    public void setCvc(String s){
        cvc.setText(s);
    }

    public void setDeliveryDay(ComboBox deliveryDay) {
        //TODO
    }

    public void setComment(String comment) {
        //TODO
    }
}
