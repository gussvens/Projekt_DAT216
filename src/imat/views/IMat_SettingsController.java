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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.IMatDataHandler;

/**
 * FXML Controller class
 *
 * @author Group 12
 */
public class IMat_SettingsController implements Initializable {

    private final String DEFAULT_COLOR = "-fx-background-color: #FDFDFD;";
    private final String ENTER_COLOR = "-fx-background-color:  #bdfbec;";
    private final String CLICKED_COLOR = "-fx-background-color:  #adebdc;";
    
    final static ObservableList<String> paymentOptions = FXCollections.observableArrayList("Faktura", "Kontant", "Kreditkort");
    final static ObservableList<String> cardTypeOptions = FXCollections.observableArrayList("VISA", "MasterCard");

//    @FXML
//    private Button homeButton;
    @FXML
    private Pane storeButton;
    @FXML
    private Pane historyButton;
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
    private ComboBox paymentBox;
    @FXML
    private ComboBox cardTypeBox;
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
    private ComboBox datePicker;
    @FXML
    private TextArea comment;
    @FXML
    private Button saveSettings;
    @FXML
    private Label saveFeedback;

    private static String staticCity;
    private static String staticCard1;
    private static String staticCard2;
    private static String staticCard3;
    private static String staticCard4;
    private static String staticCvc;
    private static String staticPayment;
    private static String staticCardType;

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
        paymentBox.setItems(paymentOptions);
        cardTypeBox.setItems(cardTypeOptions);
        paymentBox.setValue(staticPayment);
        cardTypeBox.setValue(staticCardType);

        // StoreButton
        storeButton.setOnMouseClicked(storeButtonClicked);
        storeButton.setOnMouseEntered(paneEnter);
        storeButton.setOnMouseExited(paneExit);
        storeButton.setCursor(Cursor.HAND);
        
        // HistoryButton
        historyButton.setOnMouseClicked(historyButtonClicked);
        historyButton.setOnMouseEntered(paneEnter);
        historyButton.setOnMouseExited(paneExit);
        historyButton.setCursor(Cursor.HAND);
        
        // SaveButton
        saveSettings.setOnMouseClicked(onSaveButtonClick);
        saveSettings.setCursor(Cursor.HAND);
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

    EventHandler<MouseEvent> storeButtonClicked
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    try {
                        Parent start = FXMLLoader.load(getClass().getResource("IMat_Store_v2.fxml"));
                        IMat.getStage().setScene(new Scene(start, 1360, 768));
                    } catch (IOException ex) {
                        Logger.getLogger(IMat_SettingsController.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(IMat_SettingsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
    
    EventHandler<MouseEvent> paneEnter
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                   ((Pane) t.getSource()).setStyle(ENTER_COLOR);
                }
            };
    EventHandler<MouseEvent> paneExit
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                   ((Pane) t.getSource()).setStyle(DEFAULT_COLOR);
                }
            };
    
    EventHandler<MouseEvent> buttonEnter
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                   ((Button) t.getSource()).setStyle(ENTER_COLOR);
                }
            };
    EventHandler<MouseEvent> buttonExit
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                   ((Button) t.getSource()).setStyle(DEFAULT_COLOR);
                }
            };

    EventHandler<MouseEvent> onSaveButtonClick
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    setAddress();
                    staticCard1 = card1.getText();
                    staticCard2 = card2.getText();
                    staticCard3 = card3.getText();
                    staticCard4 = card4.getText();
                    staticCardType = (String) cardTypeBox.getValue();
                    staticCity = city.getText();
                    staticCvc = cvc.getText();
                    setFirstName();
                    setLastName();
                    staticPayment = (String) paymentBox.getValue();
                    setPostCode();

                    saveFeedback.setText("Din information \nhar sparats!");
                }
            };

    @FXML
    private void handleComboBoxAction() {
        String selectedOption = (String) paymentBox.getSelectionModel().getSelectedItem();
        if (selectedOption == "Faktura" || selectedOption == "Kontant") {
            cardTypeBox.setDisable(true);
            cvc.setDisable(true);
            card1.setDisable(true);
            card2.setDisable(true);
            card3.setDisable(true);
            card4.setDisable(true);
        } else {
            cardTypeBox.setDisable(false);
            cvc.setDisable(false);
            card1.setDisable(false);
            card2.setDisable(false);
            card3.setDisable(false);
            card4.setDisable(false);
        }
    }

    /*
     @FXML
     private void homeButtonClicked() throws IOException {
     Parent start = FXMLLoader.load(getClass().getResource("IMat_Start_v2.fxml"));
     IMat.getStage().setScene(new Scene(start, 1360, 768));
     }

     */
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
    public static void setPayment(String s) {
        staticPayment = s;

    }

    public static void setCardType(String s) {
        staticCardType = s;
    }

    public static void setDeliveryDay() {
        //No delivery day variable in backend
    }

    public void setComment() {
        //No Comment variable in backend
    }

    // Setters for the static variables
    public static void setCity(String s) {
        staticCity = s;
    }

    public static void setCard1(String s) {
        staticCard1 = s;
    }

    public static void setCard2(String s) {
        staticCard2 = s;
    }

    public static void setCard3(String s) {
        staticCard3 = s;
    }

    public static void setCard4(String s) {
        staticCard4 = s;
    }

    public static void setCvc(String s) {
        staticCvc = s;
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

    public static String getPayment() {
        return staticPayment;
    }

    public static String getCardType() {
        return staticCardType;
    }

    public static ObservableList<String> getCardTypeOptions() {
        return cardTypeOptions;
    }

    public static ObservableList<String> getPaymentOptions() {
        return paymentOptions;
    }

}
