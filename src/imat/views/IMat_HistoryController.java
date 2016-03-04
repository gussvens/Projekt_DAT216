/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.views;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import imat.IMat;
import imat.IMat_Model;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import se.chalmers.ait.dat215.project.Order;
import java.util.Collections;

/**
 * FXML Controller class
 *
 * @author Gustav
 */
public class IMat_HistoryController implements Initializable {

    @FXML
    private Button homeButton;
    private List<IMat_HistoryCategoriesModel> models = new ArrayList<>();
    @FXML
    private Pane historyCategoryPane;

  /* Hitting a wall with presenting
    the history
    @FXML
    private Label dateLabel1;
    @FXML
    private Label dateLabel2;
    @FXML
    private Label dateLabel3;
    @FXML
    private Label dateLabel4;
    @FXML
    private Label dateLabel5;
    @FXML
    private Label dateLabel6;
    @FXML
    private Label dateLabel7;
    @FXML
    private Label dateLabel8;
    @FXML
    private Label dateLabel9;

    private List<Label> labelList = new ArrayList<>();*/

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FlowPane flowPane = new FlowPane();
        flowPane.setVgap(6);
        flowPane.setHgap(6);
        flowPane.setPrefWidth(250);

        List<Order> orderList = IMat_Model.getBackEnd().getOrders();
        if(orderList.size() >= 9){
            for(int i = orderList.size()-1; i >= orderList.size()-10;
                i--){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("IMat_HistoryCategories.fxml"));
                    Node historyCategory = loader.load();
                    IMat_HistoryCategoriesController controller = loader.getController();
                    controller.setDateLabel(orderList.get(i).getDate().toString());
                    //Should be a class for the content of the specific order
                    /*IMat_HistoryCategoriesModel model = new IMat_HistoryCategoriesModel(orderList.get(i));
                    models.add(model);
                    controller.setOrder(orderList.get(i));*/
                    flowPane.getChildren().add(historyCategory);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        } else {
            int j = orderList.size() - 1;
            while(j >= 0) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("IMat_HistoryCategories.fxml"));
                    Node historyCategory = loader.load();
                    IMat_HistoryCategoriesController controller = loader.getController();
                    controller.setDateLabel(orderList.get(j).getDate().toString());
                    //Should be a class for the content of the specific order
                    /*IMat_HistoryCategoriesModel model = new IMat_HistoryCategoriesModel(orderList.get(i));
                    models.add(model);
                    controller.setOrder(orderList.get(i));*/
                    flowPane.getChildren().add(historyCategory);
                    j--;
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        /*
        for(Order o : IMat_Model.getBackEnd().getOrders()){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("IMat_HistoryCategories.fxml"));
                Node historyCategory = loader.load();
                IMat_HistoryCategoriesController controller = loader.getController();
                controller.setDateLabel(o.getDate().toString());
                //Should be a class for the content of the specific order
                IMat_HistoryCategoriesModel model = new IMat_HistoryCategoriesModel(o);
                models.add(model);
                controller.setOrder(o);
                flowPane.getChildren().add(historyCategory);
            } catch (IOException e){
                e.printStackTrace();
            }
        }*/
        historyCategoryPane.getChildren().add(flowPane);

    }



    @FXML
    private void homeButtonClicked() throws IOException {
        Parent start = FXMLLoader.load(getClass().getResource("IMat_Start_v2.fxml"));
        IMat.getStage().setScene(new Scene(start, 1360, 768));
    }


}
