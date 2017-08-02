/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import app.UserProcess;
import javax.faces.context.FacesContext;
import model.User;

/**
 *
 * @author quoc95
 */
public class Login_Register_UserBean {

    /**
     * Creates a new instance of UserBean
     */
    private String userID;
    private String userName;
    private String passWord;
    private String email;
    private int phoneNumber;
    private String fullName;
    private String identityNumber;
    private String address;
    private String status;
    private String avatars;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvatars() {
        return avatars;
    }

    public void setAvatars(String avatars) {
        this.avatars = avatars;
    }
    private boolean showAlert;

    public boolean isShowAlert() {
        return showAlert;
    }

    public void setShowAlert(boolean showAlert) {
        this.showAlert = showAlert;
    }
    
    
    public Login_Register_UserBean() {
    }
    
    public String Login(){
        UserProcess userProcess = new UserProcess();
        if(userProcess.CheckLogin(this.userName, this.passWord)){
            User us = userProcess.getByUserName(this.userName);
            FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("user", us);
        return "infoUser";
        }else{
            this.showAlert = true;
            return "register";
        }
            
    }
    
    public String register(){
        UserProcess up = new UserProcess();  
        User user  = new User();
        user.setUserName(this.userName);
        user.setPassWord(this.passWord);
        user.setEmail(this.email);
        user.setPhoneNumber(this.phoneNumber);
        user.setFullName(this.fullName);
        user.setIdentityNumber(this.identityNumber);
        user.setAddress(this.address);
        user.setStatus("Inactive");
        user.setAvatars("...");
        if(up.addNewUser(user)){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("user", user);
        return "index";
        }
            
        else
            return "";
    }
    
    public String updateUserInfo(){
        return "infoUser";
    }
    
    public String LogOut(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("user");
        return "index";
    }
    
}
