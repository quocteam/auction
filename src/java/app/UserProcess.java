/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author quoc95
 */
public class UserProcess {
    public boolean CheckLogin(String userName, String passWord){
        try {
            String sql = "select * from tbl_user where userName = ? and passWord = ?";
            
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, userName);
            prst.setString(2, passWord);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                return true;
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return false;
    }
    
    public boolean addNewUser(User user){
         int result = 0;
        String sql="INSERT INTO tbl_user VALUES(?,?,?,?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, NextID());
            prst.setString(2, user.getUserName());
            prst.setString(3, user.getPassWord());
            prst.setString(4, user.getEmail());
            prst.setInt(5, user.getPhoneNumber());
            prst.setString(6, user.getFullName());
            prst.setString(7, user.getIdentityNumber());
            prst.setString(8, user.getAddress());
            prst.setString(9, user.getStatus());
            prst.setString(10, user.getAvatars());
            result=prst.executeUpdate();
            prst.close();
        } catch (SQLException e) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE,null, e);
        }
       
        return result>0;
    }
    
    public String GetLastID()
{
            String sql = "SELECT userID FROM `tbl_user` ORDER by userID DESC LIMIT 1";
         try {
             PreparedStatement prst = Process.getConnection().prepareStatement(sql);
             ResultSet rs = prst.executeQuery();
             while (rs.next()) {                 
                 return rs.getString(1);
             }
         } catch (SQLException ex) {
             Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
         }
         return "";
            
}
    
     public String NextID()
        {
            String prefixID="uid";
            if(GetLastID().equals(""))
           {
               return prefixID+"00001";  // fixwidth default
           }
            int nextID = Integer.parseInt(GetLastID().split("d")[1]) + 1;
            int lengthNumerID = GetLastID().length()- prefixID.length();
            String zeroNumber = "";
            for (int i = 1; i <= lengthNumerID; i++)
            {
                if (nextID < Math.pow(10, i))
                {
                    for (int j = 1; j <= lengthNumerID - i; i++)
                    {
                        zeroNumber += "0";
                    }
                    return prefixID + zeroNumber + ""+nextID;
                }
            }
            return prefixID + nextID;
 
        }
    
    public static void main(String[] args) {
        UserProcess us = new UserProcess();
        User user = new User("a", "b", "c", "d", 0, "e", "f", "g", "h", "i");  
        boolean a = us.addNewUser(user);
        System.out.println(""+a);
    }
}
