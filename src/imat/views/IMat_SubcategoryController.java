package imat.views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import se.chalmers.ait.dat215.project.ProductCategory;

/**
 * FXML Controller class
 *
 * @author Andreas
 */
public class IMat_SubcategoryController implements Initializable {

    // Colors. !! CAN BE CHANGED IN CSS INSTEAD, I GUESS. !!
    private final String MENU_DEFAULT_BORDER_COLOR = "-fx-border-color: #DDDDDD;";

    private final String MENU_DEFAULT_COLOR = "-fx-background-color: #FFFFFF;";

    private final String MENU_ENTER_COLOR = "-fx-background-color: #ffcc80;";
    private final String MENU_CLICKED_COLOR = "-fx-background-color: #ff9900;";

    @FXML
    private Label subName;
    @FXML
    private AnchorPane subAnchor;
    @FXML
    private ImageView subImage;

    private int indexNr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        subAnchor.setOnMouseClicked(onSubPanelClicked);
        subAnchor.setOnMouseEntered(subCatEnter);
        subAnchor.setOnMouseExited(subCatExit);
        subAnchor.setCursor(Cursor.HAND);
    }

    // Filter on click.
    EventHandler<MouseEvent> onSubPanelClicked
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    ProductCategory prod = null;

                    if (subName.getText().equals("Mejeri")) {
                        prod = ProductCategory.DAIRIES;
                    } else if (subName.getText().equals("Godis")) {
                        prod = ProductCategory.SWEET;
                    } else if (subName.getText().equals("Nötter/Frön")) {
                        prod = ProductCategory.NUTS_AND_SEEDS;
                    } else if (subName.getText().equals("Kalla drycker")) {
                        prod = ProductCategory.COLD_DRINKS;
                    } else if (subName.getText().equals("Varma drycker")) {
                        prod = ProductCategory.HOT_DRINKS;
                    } else if (subName.getText().equals("Mjöl/Socker/Salt")) {
                        prod = ProductCategory.FLOUR_SUGAR_SALT;
                    } else if (subName.getText().equals("Pasta")) {
                        prod = ProductCategory.PASTA;
                    } else if (subName.getText().equals("Baljväxter")) {
                        prod = ProductCategory.POD;
                    } else if (subName.getText().equals("Potatis/Ris")) {
                        prod = ProductCategory.POTATO_RICE;
                    } else if (subName.getText().equals("Exotisk frukt")) {
                        prod = ProductCategory.EXOTIC_FRUIT;
                    } else if (subName.getText().equals("Stenfrukt")) {
                        prod = ProductCategory.FRUIT;
                    } else if (subName.getText().equals("Meloner")) {
                        prod = ProductCategory.MELONS;
                    } else if (subName.getText().equals("Bär")) {
                        prod = ProductCategory.BERRY;
                    } else if (subName.getText().equals("Citrusfrukt")) {
                        prod = ProductCategory.CITRUS_FRUIT;
                    } else if (subName.getText().equals("Kött/Kyckling")) {
                        prod = ProductCategory.MEAT;
                    } else if (subName.getText().equals("Fisk")) {
                        prod = ProductCategory.FISH;
                    } else if (subName.getText().equals("Sallad")) {
                        prod = ProductCategory.CABBAGE;
                    } else if (subName.getText().equals("Örter")) {
                        prod = ProductCategory.HERB;
                    } else if (subName.getText().equals("Rotfrukter")) {
                        prod = ProductCategory.ROOT_VEGETABLE;
                    } else if (subName.getText().equals("Grönsaksfrukt")) {
                        prod = ProductCategory.VEGETABLE_FRUIT;
                    } else if (subName.getText().equals("Bröd")) {
                        prod = ProductCategory.BREAD;
                    } else if (subName.getText().equals("Allt")) {
                        prod = ProductCategory.DAIRIES;
                    }

                    IMat_FXMLController.activeSubIndex(indexNr);
                    //getButton(t).setStyle(MENU_CLICKED_COLOR);
                    IMat_FXMLController.getPresenter().getFXMLCont().upDateCenter(prod);
                    //getButton(t).setStyle(MENU_CLICKED_COLOR);
                }
            };

    // Resets the color of the menuButton on mouseExit. 
    EventHandler<MouseEvent> subCatExit
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    if (subName.getStyle().equals(MENU_ENTER_COLOR)) {
                        //subName.setStyle(MENU_DEFAULT_COLOR);
                        getButton(t).setStyle(MENU_DEFAULT_BORDER_COLOR);
                        subName.setStyle(MENU_DEFAULT_COLOR);

                    }
                }
            };

    // Changes the color of the menuButton on mouseEnter.
    EventHandler<MouseEvent> subCatEnter
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    if (!subName.getStyle().equals(MENU_CLICKED_COLOR)) {
                        getButton(t).setStyle(MENU_ENTER_COLOR);
                        getButton(t).setStyle(MENU_DEFAULT_BORDER_COLOR);
                        subName.setStyle(MENU_ENTER_COLOR);
                    }
                }
            };

    // Helper to get the right menuButton.
    private Pane getButton(MouseEvent t) {
        return ((AnchorPane) t.getSource());
    }

    // Sets the label of the filterButton.
    public void setSubName(String name) {
        subName.setText(name);
    }

    public void setSubImage(Image image) {
        subImage.setImage(image);
    }

    public void setIndex(int i) {
        indexNr = i;
    }

    public void getAnchorPane() {
        subName.setStyle(MENU_CLICKED_COLOR);
    }

}
