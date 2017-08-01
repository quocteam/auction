/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import app.SessionProcess;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import model.Session;

/**
 *
 * @author quoc95
 */
@Named(value = "index_SessionBean")
@Dependent
public class Index_SessionBean {

    /**
     * Creates a new instance of Index_SessionBean
     */
    private ArrayList<Session> lastestProduct = new SessionProcess().getLastestProductIndex();
    private ArrayList<Session> bestProduct = new SessionProcess().getHighestProductPriceIndex();

    public ArrayList<Session> getBestProduct() {
        return bestProduct;
    }

    public void setBestProduct(ArrayList<Session> bestProduct) {
        this.bestProduct = bestProduct;
    }
    

    public ArrayList<Session> getLastestProduct() {
        return lastestProduct;
    }

    public void setLastestProduct(ArrayList<Session> lastestProduct) {
        this.lastestProduct = lastestProduct;
    }
    
    
    
    public Index_SessionBean() {
        
    }
    
}
