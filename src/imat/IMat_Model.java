/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat;

import java.util.List;
import com.sun.org.apache.xpath.internal.SourceTree;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ProductCategory;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingCartListener;

/**
 *
 * @author Andreas
 */
public class IMat_Model {
   private final IMatDataHandler backEnd;
   private static ShoppingCart sC;
   private static IMat_presenter pres;
   
   public IMat_Model(){
       backEnd = IMatDataHandler.getInstance();
       sC = backEnd.getShoppingCart();
   }
   
   public List<Product> getProducts(ProductCategory pc){
       return backEnd.getProducts(pc);
   }
   
   public Double getTotalSum(){
      return sC.getTotal();
   }
   
   public ShoppingCart getShoppingCart(){
       return sC;
   }
   
   public IMatDataHandler getBackEnd(){
       return backEnd;
   }
   
   public void setPresenter(IMat_presenter pres){
       this.pres = pres;
   }
   public static IMat_presenter getPresenter(){
       return pres;
   }
}
