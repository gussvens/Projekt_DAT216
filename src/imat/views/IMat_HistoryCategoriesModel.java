package imat.views;

import se.chalmers.ait.dat215.project.Order;

/**
 * Created by Emil on 03/03/2016.
 */
public class IMat_HistoryCategoriesModel {
    private Order order;

    public IMat_HistoryCategoriesModel(Order order){
        this.order = order;
    }

    public Order getOrder(){
        return this.order;
    }

}
