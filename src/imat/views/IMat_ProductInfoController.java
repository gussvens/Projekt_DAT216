/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Andreas
 */
public class IMat_ProductInfoController implements Initializable {

    @FXML
    private ImageView prodPic;
    @FXML
    private Label prodName;
    @FXML
    private Label prodPrice;
    @FXML
    private Button prodFav;
    @FXML
    private Button prodBuy;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
