/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import app.SessionProcess;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import model.Session;
import model.User;

/**
 *
 * @author Khanh
 */
public class Add_Product_SessionBean {
private String sessionId;
    private String userCreateID;
    private String productName;
    private String productType;
    private String productInformation;
    private float startPrice;
    private float stepPrice;
    private int bid;
    private float lastPrice;
    private String userWinID;
    private String startDay;
    private String startTime;
    private String status;

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserCreateID() {
        return userCreateID;
    }

    public void setUserCreateID(String userCreateID) {
        this.userCreateID = userCreateID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductInformation() {
        return productInformation;
    }

    public void setProductInformation(String productInformation) {
        this.productInformation = productInformation;
    }

    public float getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(float startPrice) {
        this.startPrice = startPrice;
    }

    public float getStepPrice() {
        return stepPrice;
    }

    public void setStepPrice(float stepPrice) {
        this.stepPrice = stepPrice;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public float getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(float lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getUserWinID() {
        return userWinID;
    }

    public void setUserWinID(String userWinID) {
        this.userWinID = userWinID;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    private boolean showAlert;

    public boolean isShowAlert() {
        return showAlert;
    }

    public void setShowAlert(boolean showAlert) {
        this.showAlert = showAlert;
    }
    /**
     * Creates a new instance of Add_Product_SessionBean
     */
    public Add_Product_SessionBean() {
    }
    
    public String addNew(){
    try {
       
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(this.startDay);
        Date end = new Date(date.getTime() + (1000 * 60 * 60 * 24));
        String endDate = end.getYear()+"-"+end.getMonth()+"-"+end.getDay();
        FacesContext context = FacesContext.getCurrentInstance();
        User us =  (User) context.getExternalContext().getSessionMap().get("user");
        SessionProcess up = new SessionProcess();
        Session session = new Session();
        session.setUserCreateID(us.getUserID());
        session.setProductName(this.productName);
        session.setProductType(this.productType);
        session.setProductInformation(this.productInformation);
        session.setStartPrice(this.startPrice);
        session.setStepPrice(this.stepPrice);
        session.setBid(0);
        session.setLastPrice(0);
        session.setUserWinID(null);
        session.setStartTime(this.startDay+" "+this.startTime);
        session.setEndTime(endDate+" "+this.startTime);
        session.setStatus("Inactive");
        if(up.AddNewSession(session)){
            return "index";
        }
            
        else
            return "";
    } catch (ParseException ex) {
        Logger.getLogger(Add_Product_SessionBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return "";
    }
    
    public String returnIndex(){
        return "index";
    }
}
