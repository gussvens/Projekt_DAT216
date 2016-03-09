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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;

/**
 * FXML Controller class
 *
 * @author Andreas
 */
public class IMat_CheckOut_v2Controller implements Initializable {

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
    private Label errorLabel;
    @FXML
    private ComboBox paymentBox;
    @FXML
    private ComboBox cardTypeBox;
    @FXML
    private ScrollPane basketScrollPane;
    @FXML
    private Button homeButton;
    @FXML
    private Button backToStore;
    @FXML
    private TextField checkoutTotPrice;
    @FXML
    private Button changeInfo;
    @FXML
    private Button doneButton;
    @FXML
    private Button saveButton;
    @FXML 
    private DatePicker datePicker;

    // Had to have this to be able to delete products from this view.
    private static IMat_Checkout_presenter pres;

    private Customer c;

    private List<TextField> cardList = new ArrayList<TextField>();

    private static String personalInfo;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pres = new IMat_Checkout_presenter(
                basketScrollPane,
                checkoutTotPrice      
        );
        cardList.add(card1);
        cardList.add(card2);
        cardList.add(card3);
        cardList.add(card4);
        for(TextField c : cardList){
            c.addEventFilter(KeyEvent.KEY_RELEASED, sectionFinish(c,3));
        }
        card4.addEventFilter(KeyEvent.KEY_TYPED, numberMax(card4,3));
        postCode.addEventFilter(KeyEvent.KEY_TYPED, numberMax(postCode,4));
        cvc.addEventFilter(KeyEvent.KEY_TYPED, numberMax(cvc,2));
        
        c = IMat_Model.getBackEnd().getCustomer();
        
        backToStore.setOnMouseClicked(backToStoreClicked);
        doneButton.setOnMouseClicked(doneButtonClicked);
        pres.updateScrollPane();

        firstName.setText(c.getFirstName());
        lastName.setText(c.getLastName());
        address.setText(c.getAddress());
        postCode.setText(c.getPostCode());
        city.setText(c.getPostAddress());
        if(IMat_SettingsController.getCard1()!=null){
        card1.setText(IMat_SettingsController.getCard1());
        }
        if(IMat_SettingsController.getCard2()!=null){
        card2.setText(IMat_SettingsController.getCard2());
        }
        if(IMat_SettingsController.getCard3()!=null){
        card3.setText(IMat_SettingsController.getCard3());
        }
        if(IMat_SettingsController.getCard4()!=null){
        card4.setText(IMat_SettingsController.getCard4());
        }
        if(IMat_SettingsController.getCvc()!=null){
        cvc.setText(IMat_SettingsController.getCvc());
        }
        
        paymentBox.setItems(IMat_SettingsController.getPaymentOptions());
        paymentBox.setValue(IMat_SettingsController.getPayment());
        
        cardTypeBox.setItems(IMat_SettingsController.getCardTypeOptions());
        cardTypeBox.setValue(IMat_SettingsController.getCardType());
        
        changeInfo.setOnMouseClicked(changeInfoButtonPressed);
    }
    
    @FXML
    private void handleComboBoxAction() {
        String selectedOption = (String) paymentBox.getSelectionModel().getSelectedItem();
        if(selectedOption=="Faktura" || selectedOption=="Kontant"){
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
    
    public void updateTotPrice() {
        checkoutTotPrice.setText(Double.toString(IMat_Model.getBackEnd().getShoppingCart().getTotal()) + " kr");
    }
    
    EventHandler<MouseEvent> changeInfoButtonPressed
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    firstName.setDisable(false);
                    lastName.setDisable(false);
                    city.setDisable(false);
                    address.setDisable(false);
                    postCode.setDisable(false);
                    paymentBox.setDisable(false);
                    if((String)paymentBox.getValue()=="Faktura"||(String)paymentBox.getValue()=="Kontant"){
                        //shthahph!!
                    } else {
                    card1.setDisable(false);
                    card2.setDisable(false);
                    card3.setDisable(false);
                    card4.setDisable(false);
                    cvc.setDisable(false);
                    cardTypeBox.setDisable(false);
                    
                    }
                    saveButton.setDisable(false);
                    changeInfo.setDisable(true);  
                }
            };
    
    
    /*
    // Sets the basket.
    public void updateBasket() {
        FlowPane flowPane = new FlowPane();
        flowPane.setVgap(6);
        flowPane.setHgap(6);
        flowPane.setPrefWidth(255);

        for (ShoppingItem s : IMat_Model.getBackEnd().getShoppingCart().getItems()) {
            try {
                Product p = s.getProduct();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("IMat_BasketItem.fxml"));
                Node storeItem = loader.load();
                IMat_BasketItemController controller = loader.getController();
                controller.setItemNameLabel(p.getName());
                controller.setItemPriceLabel(p.getPrice());
                controller.setItemQuantity(p.getUnit());
                controller.setShoppingItem(s);
                flowPane.getChildren().add(storeItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        basketScrollPane.setContent(flowPane);
        checkoutTotPrice.setText(Double.toString(IMat_Model.getBackEnd().getShoppingCart().getTotal()) + " kr");
    }
*/
    EventHandler<MouseEvent>doneButtonClicked
            = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            errorLabel.setText("");

            System.out.println(IMat_Model.getBackEnd().getOrders().size());
            
            String error="noerror";
          try{  if(isAlpha(firstName.getText())==false || firstName.getText().length() == 0) {
              error = "Ert förnamn kan bara innehålla"
                      + "\n bokstäver.";
           } else if(!isAlphaNumeric(lastName.getText()) || lastName.getText().length() == 0){
                  error="Ert efternamn kan bara innehålla bokstäver "
                          + "\n och siffror.";
              }
           else if(!isAlpha(city.getText()) || city.getText().length() == 0){
                error="Er stad kan bara innehålla bokstäver.";
            } else if(!isAlphaNumeric(address.getText()) || address.getText().length() == 0){
                error="Er gatuadress kan bara innehålla bokstäver "
                        + "\n och siffror.";
            }else if( postCode.getText().length()!=5 || !postCode.getText().matches("[0-9]+")) {
                error="Ert postnummer kan bara innehålla siffror" + "\n"
                        + "och måste vara 5 siffror långt.";
            } else if (paymentBox.getValue()==null){
                error="Ni måste välja ert betalningssätt.";
            } else if(paymentBox.getValue()=="Kreditkort"){
                if(!isCardNumberValid() ){
                    
                    error="Ert kreditkortsnummer får bara innehålla" +"\n"+
                            "och måste vara 16 siffror långt";
                } else if(cardTypeBox.getValue()==null){
                    error="Ni måste välja er korttyp.";
                } else if(!isNumeric(cvc.getText())||cvc.getText().length()!=3){
                    error="Er cvc-kod kan bara innehålla siffror" + "\n" 
                            + "och måste vara 3 siffror lång.";
                } else {
                    try {
                        personalInfo = firstName.getText() + " " + lastName.getText() + "\n" +
                        address.getText() + "\n"  + postCode.getText() + "\n" + city.getText();
                        Parent start = FXMLLoader.load(getClass().getResource("IMat_Delivery.fxml"));
                        IMat.getStage().setScene(new Scene(start, 1360, 768));
                    } catch (IOException ex) {
                        Logger.getLogger(IMat_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }                
            } else {
                try {
                    personalInfo = firstName.getText() + " " + lastName.getText() + "\n" +
                     address.getText() + "\n"  + postCode.getText() + "\n" + city.getText(); 
                    Parent start = FXMLLoader.load(getClass().getResource("IMat_Delivery.fxml"));
                    IMat.getStage().setScene(new Scene(start, 1360, 768));
                } catch (IOException ex) {
                    Logger.getLogger(IMat_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
          
          } catch(NullPointerException e) {
              error="Ni måste fylla i alla era uppgifter innan"+"\n"
                      + "ni betalar.";
              
          } finally {
                if (error=="noerror"){
                    
                } else {
                    errorLabel.setTextFill(Color.RED);
                    errorLabel.setText(error + "\n" + "Var god kontrollera igen.");
                
                }
          } 
          
        String pI = firstName.getText() + " " + lastName.getText() + "\n" +
                address.getText() + "\n"  + postCode.getText() + "\n" + city.getText();
        

        personalInfo = firstName.getText() + " " + lastName.getText() + "\n" +
                address.getText() + "\n"  + postCode.getText() + "\n" + city.getText();
        System.out.println(getPersonalInfo());
        
        
        }
    };

    public static String getPersonalInfo(){
        return personalInfo;
    }


    // Back to store
    EventHandler<MouseEvent> backToStoreClicked
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

    public static IMat_Checkout_presenter getPresenter(){
        return pres;
    }
    
    public boolean isCardNumberValid(){
        boolean result=false;
        for (TextField c:cardList){
            if(isNumeric(c.getText())&&c.getText().length()==4){
                result=true;
            } else {
                result=false;
                break;
            }  
        }  
        return result;
    }
    
    public boolean isAlpha(String name) {
    char[] chars = name.toCharArray();

    for (char c : chars) {
        if(!Character.isLetter(c)) {
            return false;
        }
    }

    return true;
}
    public boolean isAlphaNumeric(String s){
    String pattern= "^[a-zA-Z0-9]*$";
        if(s.matches(pattern)){
            return true;
        }
        return false;   
}
    public boolean isNumeric(String s){
    String pattern= "[0-9]+";
        if(s.matches(pattern)){
            return true;
        }
        return false;   
}
        
    @FXML
    private EventHandler<KeyEvent> numberMax(TextField t, int i){
        return new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event){       
             if (t.getText().length()>i){
              event.consume();
             } if (t.getText()==null){
                 //not a single fuck is given
             }
            }
            
        };
    }
     
        @FXML
        private EventHandler<KeyEvent> sectionFinish(TextField t, int i){
        return new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event){  
            
             if (t.getText().length()>i){
                if(cardList != null){
                    
                    int r=cardList.indexOf(t);
                    if(r!=cardList.size()-1){
                    cardList.get(r+1).requestFocus();
                    }
                 } 
             };
         }        
    };                
    }
        @FXML
        private void saveButtonAction(){
            c.setAddress(address.getText());
            c.setPostCode(postCode.getText());
            c.setFirstName(firstName.getText());
            c.setLastName(lastName.getText());
            c.setPostAddress(city.getText());
            IMat_SettingsController.setCard1(card1.getText());
            IMat_SettingsController.setCard2(card2.getText());
            IMat_SettingsController.setCard3(card3.getText());
            IMat_SettingsController.setCard4(card4.getText());
            IMat_SettingsController.setCardType((String) cardTypeBox.getSelectionModel().getSelectedItem());
            IMat_SettingsController.setPayment((String) paymentBox.getSelectionModel().getSelectedItem());
            
            IMat_SettingsController.setCvc(cvc.getText());
            saveButton.setDisable(true);
            changeInfo.setDisable(false);
                    firstName.setDisable(true);
                    lastName.setDisable(true);
                    city.setDisable(true);
                    address.setDisable(true);
                    postCode.setDisable(true);
                    paymentBox.setDisable(true);
                    card1.setDisable(true);
                    card2.setDisable(true);
                    card3.setDisable(true);
                    card4.setDisable(true);
                    cvc.setDisable(true);
                    cardTypeBox.setDisable(true); 
        } 
}