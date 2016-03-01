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
   private static final IMatDataHandler backEnd = IMatDataHandler.getInstance();

   public synchronized static IMatDataHandler getBackEnd(){
      return backEnd;
   }
   
   public List<Product> getProducts(ProductCategory pc){
       return backEnd.getProducts(pc);
   }
   
}
