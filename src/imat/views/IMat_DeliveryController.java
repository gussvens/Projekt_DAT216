package imat.views;

import imat.IMat;
import imat.IMat_Checkout_presenter;
import imat.IMat_Model;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import se.chalmers.ait.dat215.project.CartEvent;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingCartListener;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Emil on 09/03/2016.
 */
public class IMat_DeliveryController implements Initializable, ShoppingCartListener{
    @FXML
    private Button doneButton;
    @FXML
    private ScrollPane basketScrollPane;
    @FXML
    private Pane backButton;
    @FXML
    private Pane nextButton;
    @FXML
    private Pane backToStore;
    @FXML
    private Pane backToHistory;
    @FXML
    private Pane backToSettings;
    @FXML
    private TextField checkoutTotPrice;
    @FXML
    private DatePicker date;
    @FXML
    private TextArea message;
    @FXML
    private ToggleGroup timeGroup;
    @FXML
    private Label errorLabel;
    @FXML
    private RadioButton oneButton;
    @FXML
    private RadioButton threeButton;
    @FXML
    private RadioButton fiveButton;


    private static IMat_Checkout_presenter pres;

    private static String staticMessage;
    private static String staticDaytime;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pres = new IMat_Checkout_presenter(
                basketScrollPane,
                checkoutTotPrice
        );

        backToStore.setOnMouseClicked(backToStoreClicked);
        backToHistory.setOnMouseClicked(backToHistoryClicked);
        backToSettings.setOnMouseClicked(backToSettingsClicked);
        nextButton.setOnMouseClicked(nextButtonClicked);
        backButton.setOnMouseClicked(backButtonClicked);

        IMat_Model.getBackEnd().getShoppingCart().addShoppingCartListener(this);

        pres.updateScrollPane();
    }

    public static String getStaticDaytime(){
        return staticDaytime;
    }

    public static String getStaticMessage(){
        return staticMessage;
    }

    public void shoppingCartChanged(CartEvent event){
        pres.updateScrollPane();
    }

    EventHandler<MouseEvent> backButtonClicked
            = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            try {
                Parent start = FXMLLoader.load(getClass().getResource("IMat_CheckOut_v2.fxml"));
                IMat.getStage().setScene(new Scene(start, 1360, 768));
            } catch (IOException ex) {
                Logger.getLogger(IMat_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    EventHandler<MouseEvent>nextButtonClicked
            = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            errorLabel.setText("");

            System.out.println(IMat_Model.getBackEnd().getOrders().size());

            String error="noerror";
            try{  if(date.getEditor().getText().isEmpty()) {
                error = "Ni måste välja ett datum för leverans.";
            } else if(timeGroup.getSelectedToggle() == null){
                error = "Ni måste välja en tid för leverans.";
            } else {
                try {
                    String day = date.getEditor().getText();
                    String time = "";
                    if(timeGroup.getSelectedToggle().equals(oneButton)){
                        time = "13:00";
                    } else if(timeGroup.getSelectedToggle().equals(threeButton)){
                        time = "15:00";
                    } else if(timeGroup.getSelectedToggle().equals(fiveButton)){
                        time = "17:00";
                    }
                    String daytime = day + "\n" + time;
                    String msg = message.getText();

                    staticDaytime = daytime;
                    staticMessage = msg;

                    Parent start = FXMLLoader.load(getClass().getResource("IMat_FinishBuy.fxml"));
                    IMat.getStage().setScene(new Scene(start, 1360, 768));
                } catch (IOException ex) {
                    Logger.getLogger(IMat_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } catch(NullPointerException e) {
                error="Ni måste fylla i både datum och tid för leverans innan"+"\n"
                        + "ni betalar.";

            } finally {
                if (error=="noerror"){

                } else {
                    errorLabel.setTextFill(Color.RED);
                    errorLabel.setText(error + "\n" + "Var god kontrollera igen.");

                }
            }


        }
    };



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

    // Back to store
    EventHandler<MouseEvent> backToHistoryClicked
            = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            try {
                Parent start = FXMLLoader.load(getClass().getResource("IMat_History.fxml"));
                IMat.getStage().setScene(new Scene(start, 1360, 768));
            } catch (IOException ex) {
                Logger.getLogger(IMat_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    // Back to store
    EventHandler<MouseEvent> backToSettingsClicked
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
}
