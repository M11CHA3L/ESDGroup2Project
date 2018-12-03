/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

    private ResultSet select(String query) {
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);

        } catch (SQLException ex) {
            System.out.println(ex);

        }
        
        return rs;
    }

    public HashMap<String, String> GetRowByColumnName(String query) {
        HashMap<String, String> columns = new HashMap<>();
        select(query);
        ResultSetMetaData rsmd;

        try {
            rsmd = rs.getMetaData();
            rs.next();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                columns.put(rsmd.getColumnName(i), rs.getString(i));
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return columns;
    }
    
    public void UpdateRowByColumnName(HashMap<String, String> newValues, String tableName, String whereColumn, String whereValue) {
        

        
        String query = "UPDATE " + tableName + " SET ";
        
        int numberOfColumns = newValues.entrySet().size();
        
        int counter = 1;
        for(Map.Entry<String, String> entry: newValues.entrySet())
        {
            String columnName = entry.getKey();
            String columnValue = entry.getValue();
            
            if (counter == numberOfColumns)
            {
                query += columnName + " = '" + columnValue + "' WHERE " + whereColumn + " = '" + whereValue + "'";
            }
            else if (columnName.equals("ID") || columnName.equals("USERNAME"))
            {
                //DO nothing
            }
            else
            {
                query += columnName + " = '" + columnValue + "', ";
            }
            counter++;
        }
        
        update(query);
       
    }

    /**
     *
     * @param query
     * @param editable
     * @return
     */
    public String ToTable(String query) {
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
            output += "<TR>";
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

    public String ToEditTable(String query, String KeyColumn, String TableName) {
        String output = "";
        ResultSetMetaData rsmd = null;
        int columnCount = -1;
        int rowCount = 0;

        select(query);

        // Form for EDIT a row
        output += "<form method=\"POST\" id=\"editForm\" action=\"UpdateSelectionServlet.do\"><input type=\"hidden\" name=\"tableName\" value=\"" + TableName
                + "\"><input type=\"hidden\" name=\"columnName\" value=\"" + KeyColumn
                + "\"></form>";
        
        // Form for DELETE a row
        output += "<form method=\"POST\" id=\"deleteForm\" action=\"DeleteSelectionServlet.do\"><input type=\"hidden\" name=\"tableName\" value=\"" + TableName
                + "\"><input type=\"hidden\" name=\"columnName\" value=\"" + KeyColumn
                + "\"></form>";
        
        output += "<P ALIGN='center'><TABLE BORDER=1>";

        try {
            rsmd = rs.getMetaData();
            columnCount = rsmd.getColumnCount();

            // table header
            output += "<TR>";
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
                output += String.format("<TD><button name=\"editChoice\" value=\"%s\" form=\"editForm\" >EDIT</button></TD>", rs.getString(rs.findColumn(KeyColumn)));
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
    
    public String getDriverJobs(String username) {
        String s = "";
        String registration = "";
        
        //get registration of logged in user
        select("select * from drivers where username='" + username + "'");
        try {
            while (rs.next()) {
                registration = rs.getString("registration");
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        //get driver jobs
        select("SELECT DEMANDS.ID, DEMANDS.NAME, DEMANDS.ADDRESS, DEMANDS.DESTINATION, DEMANDS.DATE, DEMANDS.TIME FROM JOURNEY INNER JOIN DEMANDS ON JOURNEY.DEMANDS_ID = DEMANDS.ID WHERE JOURNEY.REGISTRATION = '" + registration + "' AND DEMANDS.STATUS != 'COMPLETE'");
        try {
            s = "<form method=\"post\" action=\"driver.do\">";
            while (rs.next()){
                s += "<input type='radio' name='selectedJob' value='" + rs.getString("ID") + "'>  CustomerName: " + rs.getString("NAME") + "<br>Customer Address: " + rs.getString("ADDRESS") 
                        + "<br>Customer Destination: " + rs.getString("DESTINATION") 
                        + "<br>Date: " + rs.getString("DATE") + "<br>Time: : " + rs.getString("Time")
                        + "<br>";
                
            }
            s += "<br><input type='submit' name='complete' value='Complete'></form><br>";
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            return s;
    }

    public String getDrivers() {
        String s = "";
        ResultSetMetaData rsmd = null;

        select("select * from drivers");

        s += "<form method=\"post\" action=\"results.do\">";

        try {
            while (rs.next()) {

                s += rs.getString("name") + "<input type='radio' name='driver' value='" + rs.getString("name") + "'><br>";
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(new java.util.Date());
        s += "<input type='date' name='date' value='" + date + "'><br><input type='submit' name='action' value='Get Journeys'>"
                + "<input type='submit' name='action' value='Get Turnover'></form>";

        return s;
    }

    public String getJourneys(String name, String date) {
        String s = "";
        String registration = null;
        ResultSetMetaData rsmd = null;

        select("select * from drivers where name='" + name + "'");

        try {
            while (rs.next()) {
                registration = rs.getString("registration");
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        select("select * from journey where registration='" + registration + "' and date='" + date + "'");

        try {
            s += "<table border='4'>";
            s += "<tr>";
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                s += "<th>" + rs.getMetaData().getColumnName(i) + "</th>";
            }
            s += "</tr>";
            while (rs.next()) {
                s += "<tr>";
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    s += "<td>" + rs.getString(i) + "</td>";
                }
                s += "</tr>";
            }
            s += "</table>";
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return s;
    }

    public int getTurnover(String name, String date) {
        String registration = "";
       int turnover = 0;
        select("select * from drivers where name='" + name + "'");

        try {
            while (rs.next()) {
                registration = rs.getString("registration");
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        select("select * from journey where registration='" + registration + "' and date='" + date + "'");

        try {
            while (rs.next()) {
                turnover += rs.getInt("distance") * 2;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return turnover;
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
            preparedStatement.setString(7, "OUTSTANDING");
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    //update table with statement
    public String update(String statement){
        String s = "";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.executeUpdate();
            s = "Updated";
          } catch (SQLException ex) {
            Logger.getLogger(JDBC.class
                    .getName()).log(Level.SEVERE, null, ex);
          }
          return s;
       }
          

    public void createCustomer(String password, String address, String username, String name) {

        String insertUserSQL = "INSERT INTO USERS"
                + "(PASSWORD, USERROLE, USERNAME) VALUES"
                + "(?,?,?)";
        
        String insertCustomerSQL = "INSERT INTO CUSTOMERS"
                + "(ADDRESS, NAME, USERNAME) VALUES"
                + "(?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, "1"); //1 = customer
            preparedStatement.setString(3, username);
            preparedStatement.executeUpdate();
            
            PreparedStatement preparedStatement2 = connection.prepareStatement(insertCustomerSQL);
            preparedStatement2.setString(1, address);
            preparedStatement2.setString(2, name);
            preparedStatement2.setString(3, username);
            preparedStatement2.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
        public void createDriver(String password, String registration, String username, String name) {

        String insertUserSQL = "INSERT INTO USERS"
                + "(PASSWORD, USERROLE, USERNAME) VALUES"
                + "(?,?,?)";
        
        String insertDriverSQL = "INSERT INTO DRIVERS"
                + "(REGISTRATION, NAME, USERNAME) VALUES"
                + "(?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, "2"); //2 = Driver
            preparedStatement.setString(3, username);
            preparedStatement.executeUpdate();
            
            PreparedStatement preparedStatement2 = connection.prepareStatement(insertDriverSQL);
            preparedStatement2.setString(1, registration);
            preparedStatement2.setString(2, name);
            preparedStatement2.setString(3, username);
            preparedStatement2.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }
        
    public ResultSet getOutstandingDemands() {
    ResultSet dbResult = select("select * from DEMANDS where STATUS = 'COMPLETE'");

    return dbResult;
    }
    
    public ResultSet getDemandByID(String ID) {
        String temp = "select * from DEMANDS where ID = " + ID;
        ResultSet dbResult = select(temp);
        
        return dbResult;
    }
    
    public ResultSet getAvailableDrivers(String date) {

        ResultSet dbResult = select("SELECT distinct ID, NAME, REGISTRATION FROM Drivers JOIN JOURNEY ON Drivers.ID = DRIVER_ID WHERE DATE != '" + date + "'");       

        
        return dbResult;
    }
    
    public void createJourney(String customerID, String destination, String distance, String driver_id, String date, String demands_id) {

//        String insertJourneySQL = "INSERT INTO JOURNEY"
//                + "(CUSTOMER_ID, DESTINATION, DISTANCE, DRIVER_ID, DATE, DEMANDS_ID) VALUES"
//                + "(?,?,?,?,?,?)";
        
                String insertJourneySQL = "INSERT INTO JOURNEY"
                + "(DESTINATION, DISTANCE, DATE, ) VALUES"
                + "(?,?,?,?,?,?)";
        
        System.out.println(customerID + " " + destination + " " + distance + " " + driver_id + " " + date + " " + demands_id);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertJourneySQL);
            preparedStatement.setString(1, customerID);
            preparedStatement.setString(2, destination);
            preparedStatement.setString(3, distance);
            preparedStatement.setString(4, driver_id);
            preparedStatement.setString(5, date);
            preparedStatement.setString(6, demands_id);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }
}
