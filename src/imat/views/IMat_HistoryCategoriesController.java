package imat.views;

import imat.IMat;
import imat.IMat_Model;
import imat.IMat_StoreItemController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.ShoppingItem;
import sun.plugin.javascript.navig.Anchor;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.scene.layout.Pane;

/**
 * Created by Group 12
 */
public class IMat_HistoryCategoriesController extends Observable implements Initializable {

    private final String BTN_DEFAULT_ENTER = "-fx-background-color: #bdfbec;";
    private final String BTN_DEFAULT_EXIT = "-fx-background-color: #FFFFFF;";
    private final String BTN_DEFAULT_CLICK = "-fx-background-color: #9bd1c4;";

    private final String MENU_DEFAULT_COLOR = "-fx-background-color: #FDFDFD;";
    private final String MENU_ENTER_COLOR = "-fx-background-color:  #bdfbec;";
    private final String MENU_CLICKED_COLOR = "-fx-background-color:  #adebdc;";

    @FXML
    private Label dateLabel;
    @FXML
    private AnchorPane categoryPane;

    private IMat_HistoryController cont;

    private Order order;

    public void setCont(IMat_HistoryController cont) {
        this.cont = cont;
        addObserver(cont);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoryPane.setOnMouseClicked(categoryPaneClicked);
        categoryPane.setOnMouseEntered(categoryPaneEntered);
        categoryPane.setOnMouseExited(categoryPaneExited);

        notifyObservers();
        setChanged();
    }

    EventHandler<MouseEvent> categoryPaneEntered
            = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (!((Pane) event.getSource()).getStyle().equals(MENU_CLICKED_COLOR)) {
                        if (((Pane) event.getSource()).getStyle().contains("-fx-background-radius: 5 5 0 0;")) {
                            ((Pane) event.getSource()).setStyle(MENU_ENTER_COLOR + "-fx-background-radius: 5 5 0 0;" + "-fx-border-radius: 5 5 0 0;");
                        } else if (((Pane) event.getSource()).getStyle().contains("-fx-background-radius: 0 0 5 5;")) {
                            ((Pane) event.getSource()).setStyle(MENU_ENTER_COLOR + "-fx-background-radius: 0 0 5 5;" + "-fx-border-radius: 0 0 5 5;");
                        } else {
                            ((Pane) event.getSource()).setStyle(MENU_ENTER_COLOR);
                        }
                    }
                }
            };

    EventHandler<MouseEvent> categoryPaneExited
            = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (!((Pane) event.getSource()).getStyle().contains(MENU_CLICKED_COLOR)) {
                        if (((Pane) event.getSource()).getStyle().contains("-fx-background-radius: 5 5 0 0;")) {
                            ((Pane) event.getSource()).setStyle(MENU_DEFAULT_COLOR + "-fx-background-radius: 5 5 0 0;" + "-fx-border-radius: 5 5 0 0;");
                        } else if (((Pane) event.getSource()).getStyle().contains("-fx-background-radius: 0 0 5 5;")) {
                            ((Pane) event.getSource()).setStyle(MENU_DEFAULT_COLOR + "-fx-background-radius: 0 0 5 5;" + "-fx-border-radius: 0 0 5 5;");
                        } else {
                            ((Pane) event.getSource()).setStyle(MENU_DEFAULT_COLOR);
                        }
                    }
                }
            };

    EventHandler<MouseEvent> categoryPaneClicked
            = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    FlowPane flowPane = new FlowPane();
                    flowPane.setVgap(6);
                    flowPane.setHgap(6);
                    flowPane.setPrefWidth(700);

                    notifyObservers();
                    setChanged();

                    if (((Pane) event.getSource()).getStyle().contains("-fx-background-radius: 5 5 0 0;")) {
                        ((Pane) event.getSource()).setStyle(MENU_CLICKED_COLOR + "-fx-background-radius: 5 5 0 0;" + "-fx-border-radius: 5 5 0 0;");
                    } else if (((Pane) event.getSource()).getStyle().contains("-fx-background-radius: 0 0 5 5;")) {
                        ((Pane) event.getSource()).setStyle(MENU_CLICKED_COLOR + "-fx-background-radius: 0 0 5 5;" + "-fx-border-radius: 0 0 5 5;");
                    } else {
                        ((Pane) event.getSource()).setStyle(MENU_CLICKED_COLOR);
                    }

                    for (ShoppingItem s : order.getItems()) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("IMat_StoreItem.fxml"));
                            Node storeItem = loader.load();
                            IMat_StoreItemController controller = loader.getController();
                            //Should be a class for the content of the specific order
                            controller.setItemNameLabel(s.getProduct().getName());
                            controller.setItemPriceLabel(s.getProduct().getPrice());
                            controller.setItemImage(IMatDataHandler.getInstance().getFXImage(s.getProduct()));
                            controller.setItemQuantity(s.getProduct().getUnit());
                            controller.setItemID(s.getProduct().getProductId());
                            flowPane.getChildren().add(storeItem);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        cont.setHistoryItemScrollPane(flowPane);
                    }
                }
            };

    public void setDateLabel(String date) {
        this.dateLabel.setText(date);
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Pane getCategoryPane() {
        return this.categoryPane;
    }

    /*
     EventHandler<MouseEvent> paneEnter
     = new EventHandler<MouseEvent>() {

     @Override
     public void handle(MouseEvent t) {
     System.out.println(((AnchorPane) t.getSource()).getStyle().toString());
     if (categoryPane.getStyle().toString().contains("-fx-background-radius: 5 5 0 0;")) {

                       
     ((AnchorPane) t.getSource()).setStyle("-fx-background-color: #bdfbec;");
     //categoryPane.setStyle("-fx-background-radius: 5 5 0 0;");
     System.out.println("hej");
     } else if (((AnchorPane) t.getSource()).getStyle().toString().contains("-fx-background-radius: 0 0 5 5;")) {
     ((AnchorPane) t.getSource()).setStyle(BTN_DEFAULT_ENTER);
     ((AnchorPane) t.getSource()).setStyle("-fx-border-radius: 0 0 5 5;");
     ((AnchorPane) t.getSource()).setStyle("-fx-background-radius: 0 0 5 5;");
     } else {
     ((AnchorPane) t.getSource()).setStyle(BTN_DEFAULT_ENTER);
     }
     ((AnchorPane) t.getSource()).setStyle("-fx-background-radius: 5 5 0 0;");
     ((AnchorPane) t.getSource()).setStyle("-fx-border-radius: 5 5 0 0;");
     }
     };
     EventHandler<MouseEvent> paneExit
     = new EventHandler<MouseEvent>() {

     @Override
     public void handle(MouseEvent t) {
     if (((AnchorPane) t.getSource()).getStyle().toString().equals("-fx-background-radius: 5 5 0 0;")) {

     ((AnchorPane) t.getSource()).setStyle(BTN_DEFAULT_EXIT);
     ((AnchorPane) t.getSource()).setStyle("-fx-border-radius: 5 5 0 0;");
     ((AnchorPane) t.getSource()).setStyle("-fx-background-radius: 5 5 0 0;");
     } else if (((AnchorPane) t.getSource()).getStyle().toString().equals("-fx-background-radius: 0 0 5 5;")) {
     ((AnchorPane) t.getSource()).setStyle(BTN_DEFAULT_EXIT);
     ((AnchorPane) t.getSource()).setStyle("-fx-border-radius: 0 0 5 5;");
     ((AnchorPane) t.getSource()).setStyle("-fx-background-radius: 0 0 5 5;");
     } else {
     ((AnchorPane) t.getSource()).setStyle(BTN_DEFAULT_EXIT);
     }
     }
     };
     */
}
