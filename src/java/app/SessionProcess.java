/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Session;
import model.User;

/**
 *
 * @author quoc95
 */
public class SessionProcess {
    public boolean AddNewSession(Session session){
         int result = 0;
        String sql="INSERT INTO tbl_session VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, NextID());
            prst.setString(2, session.getUserCreateID());
            prst.setString(3, session.getProductName());
            prst.setString(4, session.getProductType());
            prst.setString(5, session.getProductInformation());
            prst.setFloat(6, session.getStartPrice());
            prst.setFloat(7, session.getStepPrice());
            prst.setInt(8, session.getBid());
            prst.setFloat(9, session.getLastPrice());
            prst.setString(10, session.getUserWinID());
            prst.setString(11, session.getStartTime());
            prst.setString(12, session.getEndTime());
            prst.setString(13, session.getStatus());
            result=prst.executeUpdate();
            prst.close();
        } catch (SQLException e) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE,null, e);
        }
       
        return result>0;
    }
    
    public String GetLastID()
{
            String sql = "SELECT sessionId FROM `tbl_session` ORDER by sessionId DESC LIMIT 1";
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
            String prefixID="sid";
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
     // Lấy 8 phiên đấu giá mới nhất cho trang chủ
     public ArrayList<Session> getLastestProductIndex(){
        ArrayList<Session> arr = new ArrayList<>();
         try {
            String sql = "SELECT * FROM `tbl_session` ORDER BY sessionId DESC LIMIT 8";
            
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                Session ss = new Session();
                ss.setSessionId(rs.getString(1));
                ss.setUserCreateID(rs.getString(2));
                ss.setProductName(rs.getString(3));
                ss.setProductType(rs.getString(4));
                ss.setProductInformation(rs.getString(5));
                ss.setStartPrice(rs.getFloat(6));
                ss.setStepPrice(rs.getFloat(7));
                ss.setBid(rs.getInt(8));
                ss.setStartTime(rs.getDate(11).toString());
                ss.setStatus(rs.getString(13));
                String sql2 ="SELECT * FROM `tbl_image` WHERE sid=? LIMIT 1";
                PreparedStatement prst2 = Process.getConnection().prepareStatement(sql2);
                prst2.setString(1, rs.getString(1));
                ResultSet rs2 = prst2.executeQuery();
                while (rs2.next()) {                    
                    ss.setAvatar(rs2.getString(3));
                }
                rs2.close();
                arr.add(ss);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return arr;
    }
     // Lấy 8 phiên đấu giá có giá cao nhất cho trang chủ
     public ArrayList<Session> getHighestProductPriceIndex(){
        ArrayList<Session> arr = new ArrayList<>();
         try {
            String sql = "SELECT * FROM `tbl_session` ORDER by startPrice DESC LIMIT 8";
            
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                Session ss = new Session();
                ss.setSessionId(rs.getString(1));
                ss.setUserCreateID(rs.getString(2));
                ss.setProductName(rs.getString(3));
                ss.setProductType(rs.getString(4));
                ss.setProductInformation(rs.getString(5));
                ss.setStartPrice(rs.getFloat(6));
                ss.setStepPrice(rs.getFloat(7));
                ss.setBid(rs.getInt(8));
                ss.setStartTime(rs.getDate(11).toString());
                ss.setStatus(rs.getString(13));
                String sql2 ="SELECT * FROM `tbl_image` WHERE sid=? LIMIT 1";
                PreparedStatement prst2 = Process.getConnection().prepareStatement(sql2);
                prst2.setString(1, rs.getString(1));
                ResultSet rs2 = prst2.executeQuery();
                while (rs2.next()) {                    
                    ss.setAvatar(rs2.getString(3));
                }
                rs2.close();
                arr.add(ss);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return arr;
    }
    // Lấy Phiên đấu giá theo id
     public Session getSessionByID(String sID){
         try {
            String sql = "SELECT * FROM `tbl_session` WHERE sessionId=?";
            
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, sID);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                Session ss = new Session();
                ss.setSessionId(rs.getString(1));
                ss.setUserCreateID(rs.getString(2));
                ss.setProductName(rs.getString(3));
                ss.setProductType(rs.getString(4));
                ss.setProductInformation(rs.getString(5));
                ss.setStartPrice(rs.getFloat(6));
                ss.setStepPrice(rs.getFloat(7));
                ss.setBid(rs.getInt(8));
                ss.setStartTime(rs.getDate(11).toString());
                ss.setStatus(rs.getString(13));
                return ss;
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return null;
    }
    // Lấy Danh sách ảnh theo id
    public ArrayList<String> getImagesByID(String sID){
        ArrayList<String> arr = new ArrayList<>();
         try {
            String sql = "SELECT * FROM `tbl_image` WHERE sid=?";
            PreparedStatement prst = Process.getConnection().prepareStatement(sql);
            prst.setString(1, sID);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                arr.add(rs.getString(3));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return arr;
    } 
     
     
     public static void main(String[] args) {
        SessionProcess ss = new SessionProcess();
        ArrayList<Session> arr = ss.getLastestProductIndex();
         System.out.println(""+arr.get(0).getStartTime());
    }
    
}
