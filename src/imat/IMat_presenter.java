package imat;

import java.awt.Color;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ProductCategory;

/**
 *
 * @author Andreas
 */
public class IMat_presenter {

    private IMat_Model model;

    // MenuButton colors.
    private final String MENU_DEFAULT_COLOR = "-fx-background-color: #e2e2e2;";
    private final String MENU_ENTER_COLOR = "-fx-background-color: #d6eeff;";
    private final String MENU_CLICKED_COLOR = "-fx-background-color: #a6eafc;";
    
    // SearchButton colors.
    private final String SEARCH_BTN_DEFAULT = "-fx-background-color: #a6eafc;";
    private final String SEARCH_BTN_ENTER = "-fx-background-color: #95d9eb;";
    private final String SEARCH_BTN_DOWN = "-fx-background-color: #84c8da;";
    
    

    private List<Pane> menuButtonsList;

    private List<Product> prodList;
    private List<Product> tempProdList;

    private Pane CategoryDariy, CategoryVegetables, CategoryFruit_Berries,
            CategoryDryGoods, CategoryMeat_Fish_Shellfish, CategoryDrinks,
            CategoryCandy_Snacks;
    private Button searButton;

    public IMat_presenter(
            Pane CategoryDariy,
            Pane CategoryVegetables,
            Pane CategoryFruit_Berries,
            Pane CategoryDryGoods,
            Pane CategoryMeat_Fish_Shellfish,
            Pane CategoryDrinks,
            Pane CategoryCandy_Snacks,
            Button searchButton) {

        model = new IMat_Model();

        this.searButton = searchButton;
        this.CategoryDariy = CategoryDariy;
        this.CategoryCandy_Snacks = CategoryCandy_Snacks;
        this.CategoryDrinks = CategoryDrinks;
        this.CategoryDryGoods = CategoryDryGoods;
        this.CategoryFruit_Berries = CategoryFruit_Berries;
        this.CategoryMeat_Fish_Shellfish = CategoryMeat_Fish_Shellfish;
        this.CategoryVegetables = CategoryVegetables;

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

        // Sets mouseEvents and cursor to the searchButton
        for (Pane p : menuButtonsList) {
            p.setOnMouseClicked(menuButtonClicked);
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


    private void setSeachButtonColor(MouseEvent t, String s){
        ((Button) t.getSource()).setStyle(s);
    }    
    
     EventHandler<MouseEvent> searhButtonEnter
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    setSeachButtonColor(t,SEARCH_BTN_ENTER);
                }
            };
     
     EventHandler<MouseEvent> searhButtonExit
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    setSeachButtonColor(t,SEARCH_BTN_DEFAULT);
                }
            };
     EventHandler<MouseEvent> searhButtonPressed
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                   setSeachButtonColor(t,SEARCH_BTN_DOWN);
                }
            };
     EventHandler<MouseEvent> searhButtonReleased
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    setSeachButtonColor(t,SEARCH_BTN_ENTER);
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

    // The eventhandler. 
    EventHandler<MouseEvent> menuButtonClicked
            = new EventHandler<MouseEvent>() {

                // Sets functionality on each of the menubuttons.
                @Override
                public void handle(MouseEvent t) {
                    // Changes the color of the buttons
                    for (Pane p : menuButtonsList) {
                        p.setStyle(MENU_DEFAULT_COLOR);
                    }

                    ((Pane) t.getSource()).setStyle(MENU_CLICKED_COLOR);

                    // Getting the productlists
                    ProductCategory pC = null;

                    int i = 0;
                    if (((Pane) t.getSource()).getId().equals("CategoryDariy")) {
                        i = 1;
                    } else if (((Pane) t.getSource()).getId().equals("CategoryCandy_Snacks")) {
                        i = 2;
                    } else if (((Pane) t.getSource()).getId().equals("CategoryDrinks")) {
                        i = 3;
                    } else if (((Pane) t.getSource()).getId().equals("CategoryDryGoods")) {
                        i = 4;
                    } else if (((Pane) t.getSource()).getId().equals("CategoryFruit_Berries")) {
                        i = 5;
                    } else if (((Pane) t.getSource()).getId().equals("CategoryMeat_Fish_Shellfish")) {
                        i = 6;
                    } else if (((Pane) t.getSource()).getId().equals("CategoryVegetables")) {
                        i = 7;
                    }

                    // Gets the protocts.
                    prodList = new ArrayList<>();
                    tempProdList = new ArrayList<>();

                    // The products in the category "dairie".
                    if (i == 1) {
                        pC = ProductCategory.DAIRIES;
                        prodList = model.getProducts(pC);
                    } // The products in the category "Candy and snacks".
                    else if (i == 2) {
                        pC = ProductCategory.SWEET;
                        prodList = model.getProducts(pC);
                        pC = ProductCategory.NUTS_AND_SEEDS;
                        tempProdList = model.getProducts(pC);

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }
                    } // The products in the category "Drinks".
                    else if (i == 3) {
                        pC = ProductCategory.COLD_DRINKS;
                        prodList = model.getProducts(pC);
                        pC = ProductCategory.HOT_DRINKS;
                        tempProdList = model.getProducts(pC);

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }
                    } // The products in the category "Dry goods".
                    else if (i == 4) {
                        pC = ProductCategory.FLOUR_SUGAR_SALT;
                        prodList = model.getProducts(pC);

                        pC = ProductCategory.PASTA;
                        tempProdList = model.getProducts(pC);

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }

                        pC = ProductCategory.NUTS_AND_SEEDS;
                        tempProdList = model.getProducts(pC);

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }

                        pC = ProductCategory.POD;
                        tempProdList = model.getProducts(pC);

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }

                        pC = ProductCategory.POTATO_RICE;
                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }

                    } // The products in the category "Fruit and berries".
                    else if (i == 5) {
                        pC = ProductCategory.EXOTIC_FRUIT;
                        prodList = model.getProducts(pC);
                        pC = ProductCategory.FRUIT;
                        tempProdList = model.getProducts(pC);

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }

                        pC = ProductCategory.MELONS;

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }

                        pC = ProductCategory.BERRY;

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }

                        pC = ProductCategory.CITRUS_FRUIT;

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }
                    } // The products in the category "Meat,Fish and Shellfish".
                    else if (i == 6) {
                        pC = ProductCategory.MEAT;
                        prodList = model.getProducts(pC);
                        pC = ProductCategory.FISH;
                        tempProdList = model.getProducts(pC);

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }
                    } // The products in the category "Vegetables".
                    else if (i == 7) {
                        pC = ProductCategory.CABBAGE;
                        prodList = model.getProducts(pC);
                        pC = ProductCategory.HERB;
                        tempProdList = model.getProducts(pC);

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }
                        pC = ProductCategory.ROOT_VEGETABLE;

                        for (Product p : tempProdList) {
                            prodList.add(p);
                        }

                        pC = ProductCategory.VEGETABLE_FRUIT;
                    }

                    // FOR TESTING... REMOVE WHEN DONE.
                    for (Product p : prodList) {
                        System.out.println(p.getName());
                    }

                    System.out.println("\n");
                }
            };

}
