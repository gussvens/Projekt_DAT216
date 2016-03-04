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
import java.util.ResourceBundle;

/**
 * Created by Emil on 03/03/2016.
 */
public class IMat_HistoryCategoriesController implements Initializable {

    @FXML
    private Label dateLabel;
    @FXML
    private AnchorPane categoryPane;

    private IMat_HistoryController cont;

    private Order order;

    public void setCont(IMat_HistoryController cont){
        this.cont = cont;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoryPane.setOnMouseClicked(categoryPaneClicked);
    }

    EventHandler<MouseEvent> categoryPaneClicked =
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    FlowPane flowPane = new FlowPane();
                    flowPane.setVgap(6);
                    flowPane.setHgap(6);
                    flowPane.setPrefWidth(700);

                    for(ShoppingItem s : order.getItems()){
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
                        } catch(IOException e){
                            e.printStackTrace();
                        }
                    cont.setHistoryItemScrollPane(flowPane);
                    }
                }
            };

    public void setDateLabel(String date){
        this.dateLabel.setText(date);
    }

    public void setOrder(Order order){
        this.order = order;
    }
}