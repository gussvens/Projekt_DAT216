/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat;

import java.awt.Image;
import javafx.scene.layout.Pane;

/**
 *
 * @author Andreas
 */
public class IMat_presenter {
    private Pane CategoryDariy,CategoryVegetables,CategoryFruit_Berries,
            CategoryDryGoods,CategoryMeat_Fish_Shellfish,CategoryDrinks,
            CategoryCandy_Snacks;
            
    
    
    
    public IMat_presenter(
            Pane CategoryDariy,
            Pane CategoryVegetables,
            Pane CategoryFruit_Berries,
            Pane CategoryDryGoods,
            Pane CategoryMeat_Fish_Shellfish,
            Pane CategoryDrinks,
            Pane CategoryCandy_Snacks) {
        
        this.CategoryDariy = CategoryDariy;
        this.CategoryCandy_Snacks = CategoryCandy_Snacks;
        this.CategoryDrinks = CategoryDrinks;
        this.CategoryDryGoods = CategoryDryGoods;
        this.CategoryFruit_Berries = CategoryFruit_Berries;
        this.CategoryMeat_Fish_Shellfish = CategoryMeat_Fish_Shellfish;
        this.CategoryVegetables = CategoryVegetables;
        

    }
}
