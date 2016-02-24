/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat;

import java.awt.Color;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
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
    
    private List<Pane> menuButtonsList;
    
    private List<Product> prodList;

    private Pane CategoryDariy, CategoryVegetables, CategoryFruit_Berries,
            CategoryDryGoods, CategoryMeat_Fish_Shellfish, CategoryDrinks,
            CategoryCandy_Snacks;

    public IMat_presenter(
            Pane CategoryDariy,
            Pane CategoryVegetables,
            Pane CategoryFruit_Berries,
            Pane CategoryDryGoods,
            Pane CategoryMeat_Fish_Shellfish,
            Pane CategoryDrinks,
            Pane CategoryCandy_Snacks) {
        
        model = new IMat_Model();
        
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

        // Sets listeners for the menuButtons
        for (Pane p : menuButtonsList) {
            p.setOnMouseClicked(menuButtonClicked);
        }

    }

    public void setMenuButtonToActive() {

    }

    
    
    // The eventhandler. 
    EventHandler<MouseEvent> menuButtonClicked
            = new EventHandler<MouseEvent>() {

                
                // Sets functionality on each of the menubuttons.
                @Override
                public void handle(MouseEvent t) {
                    for (Pane p : menuButtonsList) {
                        p.setStyle("-fx-background-color: #DDDDDD;");
                    }

                    ProductCategory pC = ProductCategory.MELONS;
                    
                    if(((Pane) t.getSource()).getId().equals("CategoryDariy")){
                        pC = ProductCategory.DAIRIES;
                    }
                    
                    
                    
                    
                    
                    
                    
                    ((Pane) t.getSource()).setStyle("-fx-background-color: #91e8fe;");
                    prodList = new ArrayList<>();
                    prodList = model.getProducts(pC);
                   
                    System.out.print(prodList.size());
                    
                    for(Product p : prodList){
                        System.out.println(p.getName());
                    }
                }
            };

}
