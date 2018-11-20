/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author michaelcraddock
 */
public class JDBC {

    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;

    private void select(String query) {
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);

        } catch (SQLException ex) {
            System.out.println(ex);

        }

    }   

    
    
    public HashMap<String, String> GetRowByColumnName(String query)
    {
        HashMap<String, String> columns = new HashMap<>();
        select(query);
        ResultSetMetaData rsmd;
        
        try {
            rsmd = rs.getMetaData();
            rs.next();
            for (int i = 1; i <= rsmd.getColumnCount(); i++)
            {
                columns.put(rsmd.getColumnName(i), rs.getString(i));
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        return columns;
    }
    
    /**
     *
     * @param query
     * @param editable
     * @return
     */
    public String ToTable(String query)
    {
        String output = "";
        ResultSetMetaData rsmd = null;
        int columnCount = -1;
        int rowCount = 0;
        
        select(query);
        
        output += "<P ALIGN='center'><TABLE BORDER=1>";
        
        try {
            rsmd = rs.getMetaData();
            columnCount = rsmd.getColumnCount();
            
            // table header
            output +="<TR>";
            for (int i = 0; i < columnCount; i++) {
             output += "<TH>" + rsmd.getColumnLabel(i + 1) + "</TH>";
              }
            output += "</TR>";
            // the data
            while (rs.next()) {
             output += "<TR>";
             for (int i = 0; i < columnCount; i++) {
               output += "<TD>" + rs.getString(i + 1) + "</TD>";
               }
             output += "</TR>";
             }
            output += "</TABLE></P>";
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return output;
    }
    
        public String ToEditTable(String query, String KeyColumn, String TableName)
    {
        String output = "";
        ResultSetMetaData rsmd = null;
        int columnCount = -1;
        int rowCount = 0;
        
        select(query);
        
        output += "<form method=\"POST\" id=\"myForm\" action=\"UpdateSelectionServlet.do\"><input type=\"hidden\" name=\"tableName\" value=\"" + TableName
                + "\"><input type=\"hidden\" name=\"columnName\" value=\"" + KeyColumn
                + "\"></form><P ALIGN='center'><TABLE BORDER=1>";
        
        try {
            rsmd = rs.getMetaData();
            columnCount = rsmd.getColumnCount();
            
            // table header
            output +="<TR>";
            for (int i = 0; i < columnCount; i++) {
             output += "<TH>" + rsmd.getColumnLabel(i + 1) + "</TH>";
              }
            output += "</TR>";
            // the data
            while (rs.next()) {
             output += "<TR>";
             for (int i = 0; i < columnCount; i++) {
               output += "<TD>" + rs.getString(i + 1) + "</TD>";
               }
             output += String.format("<TD><button name=\"editChoice\" value=\"%s\" form=\"myForm\" >EDIT</button></TD>",rs.getString(rs.findColumn(KeyColumn)));
             output += "</TR>";
             
             }
            output += "</TABLE></P>";
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return output;
    }


    public boolean userExists(String user) {
       
        try {
            select("select username from users where username='" + user + "'");
            if (rs.next()) {

                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean passwordMatches(String username, String pass) {

        select("select username from users where username='" + username + "' AND password='" + pass + "'");
        try {
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int getRole(String user) throws SQLException {
        int id = 0;
        select("select USERROLE from users where username='" + user + "'");
        while (rs.next()) {
            id = rs.getInt("USERROLE");
        }

        return id;
    }

    public void connect(Connection connection) {
        this.connection = connection;
        if (connection == null) {
            System.out.println("connection failed");
        }
    }
    
    public String getCustomerID(String userName) throws SQLException {
       
         String id = "";

        select("select ID from CUSTOMERS where USERNAME='" + userName + "'");
        while (rs.next()) {
            id = rs.getString("ID");
        }

        return id;
    }

    public void createDemand(String customerID, String timeRequired, String dateRequired, String destinationAddress, String currentAddress, String customerName) {
               
        String insertDemandSQL = "INSERT INTO DEMANDS" 
                              + "(CUSTOMER_ID, TIME, DATE, DESTINATION, ADDRESS, NAME, STATUS) VALUES"
                              + "(?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertDemandSQL);
            preparedStatement.setString(1, customerID);
            preparedStatement.setString(2, timeRequired);
            preparedStatement.setString(3, dateRequired);
            preparedStatement.setString(4, destinationAddress);
            preparedStatement.setString(5, currentAddress);
            preparedStatement.setString(6, customerName);
            preparedStatement.setString(7, "outstanding");
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
    }
}

