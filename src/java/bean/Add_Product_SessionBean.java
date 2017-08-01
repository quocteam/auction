/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import app.SessionProcess;
import model.Session;

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
    private String startTime;
    private String endTime;
    private String status;

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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
        SessionProcess up = new SessionProcess();
        Session session = new Session();
        session.setUserCreateID(this.userCreateID);
        session.setProductName(this.productName);
        session.setProductType(this.productType);
        session.setProductInformation(this.productInformation);
        session.setStartPrice(this.startPrice);
        session.setStepPrice(this.stepPrice);
        session.setBid(0);
        session.setLastPrice(0);
        session.setUserWinID(null);
        session.setStartTime(this.startTime);
        session.setEndTime(this.endTime);
        session.setStatus("Inactive");
        if(up.AddNewSession(session)){
        return "index";
        }
            
        else
            return "";
    }
}
