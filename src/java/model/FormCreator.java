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
    
    public String CreateOutstandingJobsList(JDBC dbBean) {
        
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
    
    public String CreateDriversList(ResultSet rs){
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
    
    public String CreateDriverJobsList(ResultSet rs){
        
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
    
    public String CreateTableAsForm(ResultSet rs){
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
    
    public String CreateEditTableAsForm(ResultSet rs, String KeyColumn, String TableName){
        ResultSetMetaData rsmd = null;
        int columnCount = -1;
        int rowCount = 0;
        String tableAsForm = "";
        
                // Form for EDIT a row
        tableAsForm += "<form method=\"POST\" id=\"editForm\" action=\"AdUpdateCustomerDriverPopulateServlet.do\"><input type=\"hidden\" name=\"tableName\" value=\"" + TableName
                + "\"><input type=\"hidden\" name=\"columnName\" value=\"" + KeyColumn
                + "\"></form>";

        // Form for DELETE a row
        tableAsForm += "<form method=\"POST\" id=\"toggleActiveForm\" action=\"AdToggleActiveSelectionServlet.do\"><input type=\"hidden\" name=\"tableName\" value=\"" + TableName
                + "\"><input type=\"hidden\" name=\"columnName\" value=\"" + KeyColumn
                + "\"></form>";

        tableAsForm += "<P ALIGN='center'><TABLE style=\"text-align:center;\" style=\"border:none\">";

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
                    tableAsForm += "<TD style=\"padding:20px\">" + rs.getString(i + 1) + "</TD>";
                }
                tableAsForm += String.format("<TD><button name=\"editChoice\" value=\"%s\" form=\"editForm\" >EDIT</button></TD>", rs.getString(rs.findColumn(KeyColumn)));
                tableAsForm += String.format("<TD><button name=\"toggleActiveChoice\" value=\"%s\" form=\"toggleActiveForm\" >TOGGLE ACTIVE</button></TD>", rs.getString(rs.findColumn(KeyColumn)));
                tableAsForm += "</TR>";

            }
            tableAsForm += "</TABLE></P>";
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tableAsForm;
    }
    
        public String CreateTurnoverTable(ResultSet rs){
        
        String turnoverTable = "";
        int turnover = 0; 
            
                try {
            turnoverTable += "<table border='4'>";
            turnoverTable += "<tr>";
            int c = rs.getMetaData().getColumnCount();
            turnoverTable += "<th>Job No.</th>";
            for (int i = 1; i <= c; i++) {
                turnoverTable += "<th>" + rs.getMetaData().getColumnName(i) + "</th>";
            }
            turnoverTable += "</tr>";
            int count = 1;
            while (rs.next()) {
                turnover += rs.getInt("CHARGE");
                turnoverTable += "<tr>";
                c = rs.getMetaData().getColumnCount();
                turnoverTable += "<td>" + count + "</td>";
                for (int i = 1; i <= c; i++) {
                    if (i == c) {
                        turnoverTable += "<td>£" + rs.getString(i) + "</td>";
                    } else {
                        turnoverTable += "<td>" + rs.getString(i) + "</td>";
                    }
                }
                turnoverTable += "</tr>";
                count++;
            }
            turnoverTable += "<tr>";
            for (int i = 0; i < (c - 1); i++) {
                turnoverTable += "<td></td>";
            }
            turnoverTable += "<td><b>Total ex. VAT</b></td><td><b>£" + turnover + "</b></td></tr></table>";
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return turnoverTable;
        }
        
}
