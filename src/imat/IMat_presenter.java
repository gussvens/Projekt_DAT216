package imat;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Andreas
 */
public class IMat_presenter extends Observable {

    private final IMat_Model model;

    // MenuButton colors.
    private final String MENU_DEFAULT_COLOR = "-fx-background-color: #e2e2e2;";
    private final String MENU_ENTER_COLOR = "-fx-background-color: #d6eeff;";
    private final String MENU_CLICKED_COLOR = "-fx-background-color: #a6eafc;";

    // SearchButton colors.
    private final String SEARCH_BTN_DEFAULT = "-fx-background-color: #a6eafc;";
    private final String SEARCH_BTN_ENTER = "-fx-background-color: #95d9eb;";
    private final String SEARCH_BTN_DOWN = "-fx-background-color: #84c8da;";

    private final List<Pane> menuButtonsList;

    private final Pane CategoryDariy,
            CategoryVegetables,
            CategoryFruit_Berries,
            CategoryDryGoods,
            CategoryMeat_Fish_Shellfish,
            CategoryDrinks,
            CategoryCandy_Snacks,
            CategoryBread;

    private final Button searchButton;

    public IMat_presenter(
            Pane CategoryDariy,
            Pane CategoryVegetables,
            Pane CategoryFruit_Berries,
            Pane CategoryDryGoods,
            Pane CategoryMeat_Fish_Shellfish,
            Pane CategoryDrinks,
            Pane CategoryCandy_Snacks,
            Pane CategoryBread,
            Button searchButton) {

        model = new IMat_Model();

        
        this.searchButton = searchButton;
        this.CategoryDariy = CategoryDariy;
        this.CategoryCandy_Snacks = CategoryCandy_Snacks;
        this.CategoryDrinks = CategoryDrinks;
        this.CategoryDryGoods = CategoryDryGoods;
        this.CategoryFruit_Berries = CategoryFruit_Berries;
        this.CategoryMeat_Fish_Shellfish = CategoryMeat_Fish_Shellfish;
        this.CategoryVegetables = CategoryVegetables;
        this.CategoryBread = CategoryBread;
        
        /*
         Puts all menuButtonObjects into a list so the listernerbinding
         will be more effective.
         */
        menuButtonsList = new ArrayList<>();
        menuButtonsList.add(CategoryDariy);
        menuButtonsList.add(CategoryCandy_Snacks);
        menuButtonsList.add(CategoryDryGoods);
        menuButtonsList.add(CategoryFruit_Berries);
        menuButtonsList.add(CategoryMeat_Fish_Shellfish);
        menuButtonsList.add(CategoryVegetables);
        menuButtonsList.add(CategoryDrinks);
        menuButtonsList.add(CategoryBread);

        // Sets mouseEvents and cursor to the searchButton
        for (Pane p : menuButtonsList) {
            p.setOnMouseEntered(menuButtonEnter);
            p.setOnMouseExited(menuButtonExit);
            p.setCursor(Cursor.HAND);
        }

        // Sets mouseEvents and cursor to the searchButton.
        searchButton.setOnMouseEntered(searhButtonEnter);
        searchButton.setOnMouseExited(searhButtonExit);
        searchButton.setOnMousePressed(searhButtonPressed);
        searchButton.setOnMouseReleased(searhButtonReleased);
        searchButton.setCursor(Cursor.HAND);
    }

    private void setSeachButtonColor(MouseEvent t, String s) {
        ((Button) t.getSource()).setStyle(s);
    }

    EventHandler<MouseEvent> searhButtonEnter
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    setSeachButtonColor(t, SEARCH_BTN_ENTER);
                }
            };

    EventHandler<MouseEvent> searhButtonExit
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    setSeachButtonColor(t, SEARCH_BTN_DEFAULT);
                }
            };
    EventHandler<MouseEvent> searhButtonPressed
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    setSeachButtonColor(t, SEARCH_BTN_DOWN);
                }
            };
    EventHandler<MouseEvent> searhButtonReleased
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    setSeachButtonColor(t, SEARCH_BTN_ENTER);
                }
            };

    // Helper to get the right menuButton.
    private Pane getButton(MouseEvent t) {
        return ((Pane) t.getSource());
    }

    // Resets the color of the menuButton on mouseExit. 
    EventHandler<MouseEvent> menuButtonExit
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    if (getButton(t).getStyle().equals(MENU_ENTER_COLOR)) {
                        getButton(t).setStyle(MENU_DEFAULT_COLOR);
                    }
                }
            };

    // Changes the color of the menuButton on mouseEnter.
    EventHandler<MouseEvent> menuButtonEnter
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    if (!getButton(t).getStyle().equals(MENU_CLICKED_COLOR)) {
                        getButton(t).setStyle(MENU_ENTER_COLOR);
                    }
                }
            };

    public void colorChangeOnClick(MouseEvent t) {
        for (Pane p : menuButtonsList) {
            p.setStyle(MENU_DEFAULT_COLOR);
        }

        getButton(t).setStyle(MENU_CLICKED_COLOR);
    }

    // NOT NEEDED NOW... CAN BE REMOVED WHEN DONE
    public static void setStage(Stage stage, Parent parent) {
        Scene scene = new Scene(parent);
        stage.setScene(scene);
    }
}
