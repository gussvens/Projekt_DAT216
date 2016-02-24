/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

public class IMat_FXMLController implements Initializable {

    private IMat_presenter pres;

    // Imports from sceneBuilder.
    @FXML
    private Pane CategoryDariy, CategoryVegetables, CategoryFruit_Berries,
            CategoryDryGoods, CategoryMeat_Fish_Shellfish, CategoryDrinks, CategoryCandy_Snacks;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pres = new IMat_presenter(
                CategoryDariy,
                CategoryVegetables,
                CategoryFruit_Berries,
                CategoryDryGoods,
                CategoryMeat_Fish_Shellfish,
                CategoryDrinks,
                CategoryCandy_Snacks);
    }

    @FXML
    public void labelClickedEvent(ActionEvent event){
        
    }

}
