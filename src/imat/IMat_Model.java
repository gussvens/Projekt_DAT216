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

/**
 *
 * @author Andreas
 */
public class IMat_Model {
   private final IMatDataHandler backEnd = IMatDataHandler.getInstance();
   
   public IMat_Model(){
       
   }
   
   public List<Product> getProducts(ProductCategory pc){
       for(Product p : backEnd.getProducts(pc)){
          System.out.println(p.getName());
       }
       return backEnd.getProducts(pc);
   }
   
}
