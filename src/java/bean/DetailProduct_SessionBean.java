/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import app.SessionProcess;
import java.util.ArrayList;
import model.Session;

/**
 *
 * @author quoc95
 */
public class DetailProduct_SessionBean {

    /**
     * Creates a new instance of DetailProduct_SessionBean
     */
    private Session session;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    
    private ArrayList<String> images;

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }
    
    public DetailProduct_SessionBean() {
    }
    
    public String detail(String sid){
       SessionProcess sp = new SessionProcess();
       this.session = sp.getSessionByID(sid);
       this.images = sp.getImagesByID(sid);
        return "product_detail";
    }
    
}
