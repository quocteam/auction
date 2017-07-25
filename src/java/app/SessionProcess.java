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
import model.Session;

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
     
     public static void main(String[] args) {
        SessionProcess ss = new SessionProcess();
        Session session = new Session("", "uid00001", "", "", "", 0, 0, 0, 0, "uid00001", "2017-07-13 03:16:21", "2017-07-12 03:16:21", "");
        ss.AddNewSession(session);
    }
    
}
