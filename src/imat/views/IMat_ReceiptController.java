package imat.views;

import imat.IMat_Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Emil on 09/03/2016.
 */
public class IMat_ReceiptController implements Initializable {

    @FXML
    private Label nrOfReceiptItems;
    @FXML
    private Label receiptProdName;
    @FXML
    private Label receiptProdPrice;
    @FXML
    private Label receiptProdQuant;



    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setNrOfReceiptItems(double d){
        this.nrOfReceiptItems.setText(d + "");
    }

    public void setReceiptProdName(String s){
        this.receiptProdName.setText(s);
    }

    public void setReceiptProdPrice(double d){
        this.receiptProdPrice.setText(d + "");
    }

    public void setReceiptProdQuant(String s){
        this.receiptProdQuant.setText(s);
    }

}
