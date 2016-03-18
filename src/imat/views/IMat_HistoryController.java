/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.views;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import imat.IMat;
import imat.IMat_Model;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import se.chalmers.ait.dat215.project.*;

/**
 * FXML Controller class
 *
 * @author Group 12
 */
public class IMat_HistoryController implements Initializable, ShoppingCartListener, Observer {

    private final String BTN_DEFAULT_ENTER = "-fx-background-color: #bdfbec;";
    private final String BTN_DEFAULT_EXIT = "-fx-background-color: #FFFFFF;";
    private final String BTN_DEFAULT_CLICK = "-fx-background-color: #9bd1c4;";

    private final String MENU_DEFAULT_COLOR = "-fx-background-color: #FDFDFD;";
    private final String MENU_ENTER_COLOR = "-fx-background-color:  #bdfbec;";
    private final String MENU_CLICKED_COLOR = "-fx-background-color:  #adebdc;";

    @FXML
    private Pane storeButton;
    @FXML
    private Pane settingsButton;
    private List<Node> categories = new ArrayList<>();
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
    @FXML
    private Button searchButton;
    @FXML
    private TextField totalPrice;
    @FXML
    private ScrollPane subScrollPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FlowPane fPane = new FlowPane();
        fPane.setHgap(6);
        fPane.setPrefWidth(640);
        fPane.setPrefHeight(104);

        Text header = new Text("\t\t" + "   Din historik");
        header.setFont(Font.font("System", 42));
        header.setFontSmoothingType(FontSmoothingType.LCD);
        TextFlow flow = new TextFlow(header);
        fPane.getChildren().add(flow);
        fPane.setAlignment(Pos.BASELINE_CENTER);

        subScrollPane.setContent(flow);

        IMat_Model.getBackEnd().getShoppingCart().addShoppingCartListener(this);
        setTotal();
        if (IMat_Model.getBackEnd().getShoppingCart().getItems().isEmpty()) {
            toCheckout.setDisable(true);
            removeAllFromBasket.setDisable(true);

            FlowPane basketFlowPane = new FlowPane();
            basketFlowPane.setVgap(3);
            basketFlowPane.setPrefWidth(255);

            Text text1 = new Text("\n\n\n\n\n\n\n\nDin kundvagn är för närvarande tom.");
            text1.setFont(Font.font("System", 13));
            text1.setFontSmoothingType(FontSmoothingType.LCD);
            TextFlow textFlow = new TextFlow(text1);
            basketFlowPane.getChildren().add(textFlow);
            basketFlowPane.setAlignment(Pos.CENTER);

            basketScrollPane.setContent(basketFlowPane);
        }

        if (!IMat_Model.getBackEnd().getShoppingCart().getItems().isEmpty()) {
            toCheckout.setDisable(false);
            removeAllFromBasket.setDisable(false);
        }

        // StoreButton
        storeButton.setOnMouseClicked(storeButtonClicked);
        storeButton.setOnMouseEntered(paneEnter);
        storeButton.setOnMouseExited(paneExit);
        storeButton.setCursor(Cursor.HAND);

        // SettingsButton
        settingsButton.setOnMouseClicked(settingsButtonClicked);
        settingsButton.setOnMouseEntered(paneEnter);
        settingsButton.setOnMouseExited(paneExit);
        settingsButton.setCursor(Cursor.HAND);

        // CheckoutButton
        toCheckout.setOnMouseEntered(buttonEnter);
        toCheckout.setOnMouseExited(buttonExit);
        toCheckout.setCursor(Cursor.HAND);
        toCheckout.setOnMouseClicked(toCheckoutClicked);

        // EmptyBasket
        removeAllFromBasket.setOnMouseClicked(removeButtonClicked);
        removeAllFromBasket.setOnMouseEntered(buttonEnter);
        removeAllFromBasket.setOnMouseExited(buttonExit);
        removeAllFromBasket.setCursor(Cursor.HAND);

        // SearchButton
        searchButton.setOnMouseEntered(buttonEnter);
        searchButton.setOnMouseExited(buttonExit);
        searchButton.setCursor(Cursor.HAND);

        FlowPane flowPane = new FlowPane();
        flowPane.setVgap(3);
        flowPane.setHgap(6);
        flowPane.setPrefWidth(250);

        initCenter();

        if (IMat_Model.getBackEnd().getOrders().isEmpty()) {
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

                    categories.add(historyCategory);


                    if (i == orderList.size() - 1) {
                        historyCategory.setStyle("-fx-border-radius: 5 5 0 0;");
                        historyCategory.setStyle("-fx-background-radius: 5 5 0 0; ");
                    }
                    if (i == orderList.size() - 10) {
                        historyCategory.setStyle("-fx-border-radius: 0 0 5 5;");
                        historyCategory.setStyle("-fx-background-radius: 0 0 5 5; ");
                    }
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

                    categories.add(historyCategory);

                    if (j == orderList.size() - 1) {
                        historyCategory.getStyleClass().add("-fx-border-radius: 5 5 0 0;");
                        historyCategory.getStyleClass().add("-fx-background-radius: 5 5 0 0; ");
                    }
                    if (j == 0) {
                        historyCategory.getStyleClass().add("-fx-border-radius: 0 0 5 5;");
                        historyCategory.getStyleClass().add("-fx-background-radius: 0 0 5 5; ");
                    }

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

        if (!IMat_Model.getBackEnd().getShoppingCart().getItems().isEmpty()) {
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
        } else {
            Text text1 = new Text("\n\n\n\n\n\n\n\nDin kundvagn är för närvarande tom.");
            text1.setFont(Font.font("System", 13));
            text1.setFontSmoothingType(FontSmoothingType.LCD);
            TextFlow textFlow = new TextFlow(text1);
            basketFlowPane.getChildren().add(textFlow);
            basketFlowPane.setAlignment(Pos.CENTER);

        }
        basketScrollPane.setContent(basketFlowPane);
        setTotal();

    }

    public void setHistoryItemScrollPane(FlowPane flowPane) {
        historyItemScrollPane.setContent(flowPane);
    }

    public void update(Observable o, Object arg){
        for(Node c : categories){
            c.setStyle(MENU_DEFAULT_COLOR);
        }
    }

    EventHandler<MouseEvent> paneEnter
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    ((Pane) t.getSource()).setStyle(BTN_DEFAULT_ENTER);

                }
            };
    EventHandler<MouseEvent> paneExit
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    ((Pane) t.getSource()).setStyle(BTN_DEFAULT_EXIT);

                }
            };

    EventHandler<MouseEvent> buttonEnter
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    if (!((Button) t.getSource()).isDisabled()) {
                        ((Button) t.getSource()).setStyle(BTN_DEFAULT_ENTER);
                    }

                }
            };
    EventHandler<MouseEvent> buttonExit
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    if (!((Button) t.getSource()).isDisabled()) {
                        ((Button) t.getSource()).setStyle(BTN_DEFAULT_EXIT);
                    }

                }
            };

    EventHandler<MouseEvent> toCheckoutClicked
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    try {
                        Parent start = FXMLLoader.load(getClass().getResource("IMat_CheckOut.fxml"));
                        IMat.getStage().setScene(new Scene(start, 1360, 768));
                    } catch (IOException ex) {
                        Logger.getLogger(IMat_StoreController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };

    EventHandler<MouseEvent> removeButtonClicked
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    IMat_Model.getBackEnd().getShoppingCart().clear();
                    System.out.println("Cart size: " + IMat_Model.getBackEnd().getShoppingCart().getItems().size());
                    IMat_Model.getBackEnd().getShoppingCart().fireShoppingCartChanged(new ShoppingItem(null), true);

                    setTotal();
                }
            };

    EventHandler<MouseEvent> storeButtonClicked
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    try {
                        Parent start = FXMLLoader.load(getClass().getResource("IMat_Store.fxml"));
                        IMat.getStage().setScene(new Scene(start, 1360, 768));
                    } catch (IOException ex) {
                        Logger.getLogger(IMat_StoreController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };

    public void initCenter() {
        FlowPane flowPane = new FlowPane();
        flowPane.setVgap(6);
        flowPane.setHgap(6);
        flowPane.setPrefWidth(700);
        flowPane.setStyle("-fx-background-color: #FFFFFF;");

        flowPane.getChildren().add(new ImageView("imat/images/bigHistory.jpg"));
        flowPane.setAlignment(Pos.CENTER);
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
                        Logger.getLogger(IMat_StoreController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };

    public void shoppingCartChanged(CartEvent evt) {
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
            Text text1 = new Text("\n\n\n\n\n\n\n\nDin kundvagn är för närvarande tom.");
            text1.setFont(Font.font("System", 13));
            text1.setFontSmoothingType(FontSmoothingType.LCD);
            TextFlow textFlow = new TextFlow(text1);
            basketFlowPane.getChildren().add(textFlow);
            basketFlowPane.setAlignment(Pos.CENTER);
            totalPrice.setText(Double.toString(IMat_Model.getBackEnd().getShoppingCart().getTotal()) + " kr");
        } else {
            toCheckout.setDisable(false);
            removeAllFromBasket.setDisable(false);
            setTotal();
        }
    }

    public void setTotal() {
        totalPrice.setText(Double.toString(IMat_Model.getBackEnd().getShoppingCart().getTotal()) + " kr");
    }
}
