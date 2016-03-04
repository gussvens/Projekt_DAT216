package imat.views;

import imat.IMat_Model;
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
import se.chalmers.ait.dat215.project.Order;
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

    private Order order;

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
                    flowPane.setPrefWidth(250);

                    List<Order> orders = IMat_Model.getBackEnd().getOrders();
                    for(Order o : IMat_Model.getBackEnd().getOrders()){
                        if(o.equals(order)) {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("IMat_StoreItem.fxml"));
                                Node storeItem = loader.load();
                                IMat_HistoryCategoriesController controller = loader.getController();
                                //Should be a class for the content of the specific order
                                flowPane.getChildren().add(storeItem);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
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
