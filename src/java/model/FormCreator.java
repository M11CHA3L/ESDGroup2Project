/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.AdminServlet;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RyanCoole
 */
public class FormCreator {
    
    public String createOutstandingJobsList(JDBC dbBean) {
        
        //initiate job list
        String jobsList = "";
        
        try {
            //call job list from db
            ResultSet dbResult = dbBean.getOutstandingDemands();

            //build form from results as a String
            jobsList += "<form method=\"post\" action=\"AdSelectDemandServlet.do\">";
            while (dbResult.next()) {
                jobsList += dbResult.getString("Date") + " at " + dbResult.getString("Time") + " to " + dbResult.getString("Destination") + " " + "<input type='radio' name='demandRadio' value='" + dbResult.getString("ID") + "'><br>";
            }
            jobsList += "<input type='submit' name='action' value='Assign Driver'>";
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        //return the form
        return jobsList;
    }
    
    public String createDriversList(ResultSet rs){
        //initiate drivers list
        String driversList = "";
        
        
        try {
            driversList += "<form method=\"post\" action=\"AdminServlet.do\">";

            while (rs.next()) {

                driversList += "<input type='radio' name='driver' value='" + rs.getString("name") + "'>  " + rs.getString("name") + "<br>";
            }

            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(new java.util.Date());

            driversList += "<br><b>Select Date:</b><br> <input type='date' name='date' value='" + date + "'>  <input type='submit' name='adminOption' value='Get Driver Journeys'>"
                    + "</form>";

        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return driversList;
        
    }
    
    public String createDriverJobsList(ResultSet rs){
        
        String jobsList = "";
        
                try {

                jobsList = "<form method=\"post\" action=\"DrViewJobsServlet.do\">";
                while (rs.next()) {

                    jobsList += "<input type='radio' name='selectedJob' value='" + rs.getString("ID") + "'>  CustomerName: " + rs.getString("NAME") + "<br>Customer Address: " + rs.getString("ADDRESS")
                            + "<br>Customer Destination: " + rs.getString("DESTINATION")
                            + "<br>Date: " + rs.getString("DATE") + "<br>Time: : " + rs.getString("Time")
                            + "<br><br>";
                              
            }
            
                jobsList += "<br><input type='submit' name='complete' value='Complete'></form><br>";

        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return jobsList;
    }
    
    public String CraeteTableAsForm(ResultSet rs){
        String tableAsForm = "";
        ResultSetMetaData rsmd = null;
        int columnCount = -1;
        tableAsForm += "<P ALIGN='center'><TABLE BORDER=1>";

        try {
            rsmd = rs.getMetaData();
            columnCount = rsmd.getColumnCount();

            // table header
            tableAsForm += "<TR>";
            for (int i = 0; i < columnCount; i++) {
                tableAsForm += "<TH>" + rsmd.getColumnLabel(i + 1) + "</TH>";
            }
            tableAsForm += "</TR>";
            // the data
            while (rs.next()) {
                tableAsForm += "<TR>";
                for (int i = 0; i < columnCount; i++) {
                    tableAsForm += "<TD>" + rs.getString(i + 1) + "</TD>";
                }
                tableAsForm += "</TR>";
            }
            tableAsForm += "</TABLE></P>";
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tableAsForm;
    }
}
