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
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Cursor;

/**
 * Created by Group 12
 */
public class IMat_DeliveryController implements Initializable, ShoppingCartListener {

    private final String PANE_DEFAULT_COLOR = "-fx-background-color: #FDFDFD;";
    private final String PANE_ENTER_COLOR = "-fx-background-color:  #bdfbec;";
    private final String PANE_CLICKED_COLOR = "-fx-background-color:  #adebdc;";

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
    @FXML
    private Pane seqOne;
    @FXML
    private Pane seqThree;

    private static IMat_Checkout_presenter pres;

    private static String staticMessage;
    private static String staticDaytime;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pres = new IMat_Checkout_presenter(
                basketScrollPane,
                checkoutTotPrice
        );

        // StoreButton
        backToStore.setOnMouseClicked(backToStoreClicked);
        backToStore.setOnMouseEntered(paneEnter);
        backToStore.setOnMouseExited(paneExit);
        backToStore.setCursor(Cursor.HAND);

        // HistoryButton
        backToHistory.setOnMouseClicked(backToHistoryClicked);
        backToHistory.setOnMouseEntered(paneEnter);
        backToHistory.setOnMouseExited(paneExit);
        backToHistory.setCursor(Cursor.HAND);

        // SettingsButton
        backToSettings.setOnMouseClicked(backToSettingsClicked);
        backToSettings.setOnMouseEntered(paneEnter);
        backToSettings.setOnMouseExited(paneExit);
        backToSettings.setCursor(Cursor.HAND);

        // BackButton
        backButton.setOnMouseClicked(backButtonClicked);
        backButton.setOnMouseEntered(paneEnter);
        backButton.setOnMouseExited(paneExit);
        backButton.setCursor(Cursor.HAND);

        // NextButton
        nextButton.setOnMouseClicked(nextButtonClicked);
        nextButton.setOnMouseEntered(paneEnter);
        nextButton.setOnMouseExited(paneExit);
        nextButton.setCursor(Cursor.HAND);

        seqOne.setOnMouseClicked(backButtonClicked);
        seqThree.setOnMouseClicked(nextButtonClicked);

        IMat_Model.getBackEnd().getShoppingCart().addShoppingCartListener(this);

        pres.updateScrollPane();
    }

    EventHandler<MouseEvent> paneEnter
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    ((Pane) t.getSource()).setStyle(PANE_ENTER_COLOR);
                }
            };

    EventHandler<MouseEvent> paneExit
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    ((Pane) t.getSource()).setStyle(PANE_DEFAULT_COLOR);
                }
            };

    public static String getStaticDaytime() {
        return staticDaytime;
    }

    public static String getStaticMessage() {
        return staticMessage;
    }

    public void shoppingCartChanged(CartEvent event) {
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

    EventHandler<MouseEvent> nextButtonClicked
            = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    errorLabel.setText("");

                    System.out.println(IMat_Model.getBackEnd().getOrders().size());

                    String error = "noerror";
                    try {
                        if (date.getEditor().getText().isEmpty()) {
                            error = "Ni måste välja ett datum för leverans.";
                        } else if (timeGroup.getSelectedToggle() == null) {
                            error = "Ni måste välja en tid för leverans.";
                        } else if(IMat_Model.getBackEnd().getShoppingCart().getItems().isEmpty()) {
                            error = "Er kundvagn kan inte vara \ntom när ni går vidare.";
                        }else {
                            try {
                                String day = date.getEditor().getText();
                                String time = "";
                                if (timeGroup.getSelectedToggle().equals(oneButton)) {
                                    time = "13:00";
                                } else if (timeGroup.getSelectedToggle().equals(threeButton)) {
                                    time = "15:00";
                                } else if (timeGroup.getSelectedToggle().equals(fiveButton)) {
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

                    } catch (NullPointerException e) {
                        error = "Ni måste fylla i både datum och tid för leverans innan" + "\n"
                        + "ni betalar.";

                    } finally {
                        if (error == "noerror") {

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
