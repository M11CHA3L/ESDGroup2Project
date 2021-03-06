/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.io.FileNotFoundException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
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
    FormCreator formCreator = new FormCreator();

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
        for (Map.Entry<String, String> entry : newValues.entrySet()) {
            String columnName = entry.getKey();
            String columnValue = entry.getValue();

            if (counter == numberOfColumns) {
                query += columnName + " = '" + columnValue + "' WHERE " + whereColumn + " = " + whereValue;
            } else if (columnName.equals("ID") || columnName.equals("USERNAME")) {
                //DO nothing
            } else {
                query += columnName + " = '" + columnValue + "', ";
            }
            counter++;
        }

        update(query);

    }

    public String ToTable(String query) {
        String output = "";

        select(query);

        
         output = formCreator.CreateTableAsForm(rs);
        
        return output;
    }

    public String ToEditTable(String query, String KeyColumn, String TableName) {
        String output = "";

        select(query);
        output = formCreator.CreateEditTableAsForm(rs, KeyColumn, TableName);

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
        String id = "";

        //get registration of logged in user
        select("select * from drivers where username='" + username + "' AND ACTIVE = true");
        try {
            while (rs.next()) {
                id = rs.getString("id");
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //get driver jobs
        select("SELECT DEMANDS.ID, DEMANDS.NAME, DEMANDS.ADDRESS, DEMANDS.DESTINATION, DEMANDS.DATE, DEMANDS.TIME "
                + "FROM JOURNEY "
                + "INNER JOIN DEMANDS ON JOURNEY.DEMANDS_ID = DEMANDS.ID "
                + "WHERE JOURNEY.DRIVER_ID = " + id + " AND DEMANDS.STATUS != 'COMPLETE'");

        //create driver jobs list      
        formCreator.CreateDriverJobsList(rs);

        return s;
    }

    public String getDrivers() {
        String s = "";

        select("select * from drivers where ACTIVE = true");
        
        formCreator.CreateDriversList(this.rs);

        return s;
    }

    public String getJourneys(String name, String date) {
        String s = "";
        String id = null;
        int turnover = 0;
        ResultSetMetaData rsmd = null;

        select("select * from drivers where name='" + name + "'");

        try {
            while (rs.next()) {
                id = rs.getString("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        select("select * from journey where DRIVER_ID=" + id + " and date='" + date + "'");

        try {
            s += "<table style=\"border:none\">";
            s += "<tr>";
            int c = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= c; i++) {
                s += "<th>" + rs.getMetaData().getColumnName(i) + "</th>";
            }
            s += "</tr>";
            while (rs.next()) {
                turnover += rs.getInt("CHARGE");
                s += "<tr>";
                c = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= c; i++) {
                    if (i == c) {
                        s += "<td>£" + rs.getString(i) + "</td>";
                    } else {
                        s += "<td>" + rs.getString(i) + "</td>";
                    }
                }
                s += "</tr>";
            }
            s += "<tr>";
            for (int i = 0; i < (c - 2); i++) {
                s += "<td></td>";
            }
            s += "<td><b>Total ex. VAT</b></td><td><b>£" + turnover + "</b></td></tr></table>";
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return s;
    }

    public String getTurnover(String date) {

        select("select C.NAME, D.DESTINATION, J.DISTANCE, J.TIME, J.CHARGE from JOURNEY as J inner join DEMANDS as D on D.ID = J.DEMANDS_ID inner join customers as C on C.ID = J.CUSTOMER_ID where D.STATUS = 'COMPLETE' AND J.DATE = '" + date + "' order by J.TIME");

        String s = formCreator.CreateTurnoverTable(rs);

        return s;
    }

    public void createDemand(String customerID, String timeRequired, String dateRequired, String destinationAddress, String currentAddress, String customerName) {

        String insertDemandSQL = "INSERT INTO DEMANDS"
                + "(CUSTOMER_ID, TIME, DATE, DESTINATION, ADDRESS, NAME, STATUS) VALUES"
                + "(?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertDemandSQL);
            preparedStatement.setInt(1, Integer.parseInt(customerID));
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
    public String update(String statement) {
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

    public String setJourneyComplete(String demandId) throws FileNotFoundException {
        String s;
        s = update("UPDATE DEMANDS SET STATUS = 'COMPLETE' WHERE ID = " + demandId);
        if (!"".equals(s)) {

            s = update("UPDATE JOURNEY SET CHARGE = '" + calculateJourneyCost(demandId) + "' WHERE DEMANDS_ID = " + demandId);
        }

        return s;
    }

    private String calculateJourneyCost(String demandID) throws FileNotFoundException {
        //hardcoded needs to be called from elsewhere
        int price = 0;
        int distance;
        int pricePerMile = 1;

        Price p = new Price();
        price = p.getBasePrice();
        
        select("select * from journey where DEMANDS_ID=" + demandID);
        try {
            if (rs.next()) {
                distance = rs.getInt("DISTANCE");
                if (distance <= 5) {
                    return String.valueOf(price);
                } else {
                    return String.valueOf(price + ((distance - 5) * pricePerMile));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return String.valueOf(0);
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
    
    public boolean createCustomer(String address, String name){
        
        String insertCustomerSQL = "INSERT INTO CUSTOMERS"
                + "(ADDRESS, NAME) VALUES"
                + "(?,?)";       
        try{
            PreparedStatement preparedStatement2 = connection.prepareStatement(insertCustomerSQL);
            preparedStatement2.setString(1, address);
            preparedStatement2.setString(2, name);
            preparedStatement2.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean createGuestBooking(String address, String name, String timeReq, String dateReq, String destAddress, String curAddress){
        String ID = null;
        
        boolean test = createCustomer(address, name);
        
        if (test){
            select("select * from customers");
            try {
                while (rs.next()) {
                    ID = rs.getString("ID");
                }
                if (ID != null) {
                    createDemand(ID, timeReq, dateReq, destAddress, curAddress, name);
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return false;
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

    public void SetDriverActive(String driverID, boolean active) {
        if (active) {
            update("UPDATE DRIVERS SET ACTIVE=TRUE WHERE ID=" + driverID);
        } else {
            update("UPDATE DRIVERS SET ACTIVE=FALSE WHERE ID=" + driverID);
        }
    }

    public void SetCustomerActive(String customerID, boolean active) {
        if (active) {
            update("UPDATE CUSTOMERS SET ACTIVE=TRUE WHERE ID=" + customerID);
        } else {
            update("UPDATE CUSTOMERS SET ACTIVE=FALSE WHERE ID=" + customerID);
        }
    }

    public String[] returnInvoice(String dID) {
        String[] s = new String[2];
        double price = 10;
        double pricepermile = 1;
        double VAT = 1.2;
        double priceVAT;

        select("SELECT D.ADDRESS, D.DESTINATION, D.\"DATE\", D.\"TIME\", J.DISTANCE, C.\"NAME\", D.ID\n"
                + "FROM JOURNEY AS J \n"
                + "INNER JOIN DEMANDS AS D ON D.ID = J.DEMANDS_ID\n"
                + "INNER JOIN CUSTOMERS AS C ON C.ID = J.CUSTOMER_ID\n"
                + "WHERE D.ID =" + dID);

        try {
            while (rs.next()) {
                if (Integer.parseInt(rs.getString("DISTANCE")) > 5) {
                    // £10
                    price = 10 + pricepermile * (Double.parseDouble(rs.getString("DISTANCE")) - 5);
                }
                priceVAT = price * VAT;
                s[0] = "Invoice for " + rs.getString("NAME") + "\n\nDate: " + rs.getString("DATE") + "\nTime: " + rs.getString("TIME") + "\nStart: " + rs.getString("ADDRESS")
                        + "\nEnd:" + rs.getString("DESTINATION") + "\nPrice without VAT: £"
                        + String.format("%.2f", price) + "\nPrice including VAT @ %" + ((VAT * 100) - 100) + ": £"
                        + String.format("%.2f", priceVAT);

                s[1] = rs.getString("DATE") + rs.getString("NAME");

            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return s;
    }

    public ResultSet getOutstandingDemands() {
        ResultSet dbResult = select("select * from DEMANDS where STATUS = 'OUTSTANDING'");

        return dbResult;
    }

    public ResultSet getDemandByID(String ID) {
        String temp = "select * from DEMANDS where ID = " + ID;
        ResultSet dbResult = select(temp);

        return dbResult;
    }

    public void setDemandStatus(String status, String demandID) {
        String temp = "UPDATE Demands SET STATUS = '" + status + "' WHERE ID =" + demandID;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(temp);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ResultSet getAvailableDrivers(String date) {

        ResultSet dbResult = select("SELECT DRIVERS.ID, DRIVERS.NAME, DRIVERS.REGISTRATION FROM Drivers where id NOT IN (SELECT distinct DRIVERS.ID FROM DRIVERS JOIN JOURNEY ON DRIVERS.ID = JOURNEY.DRIVER_ID WHERE DRIVERS.ACTIVE = true AND JOURNEY.DATE = '" + date + "')");


        return dbResult;
    }

    public void createJourney(String customerID, String destination, String distance, String driver_id, String date, String demands_id, String time) {

        String insertJourneySQL = "INSERT INTO JOURNEY"
                + "(CUSTOMER_ID, DESTINATION, DISTANCE, DRIVER_ID, DATE, DEMANDS_ID, TIME) VALUES"
                + "(?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertJourneySQL);
            preparedStatement.setInt(1, Integer.parseInt(customerID));
            preparedStatement.setString(2, destination);
            preparedStatement.setInt(3, Integer.parseInt(distance));
            preparedStatement.setInt(4, Integer.parseInt(driver_id));
            preparedStatement.setString(5, date);
            preparedStatement.setInt(6, Integer.parseInt(demands_id));
            preparedStatement.setString(7, time);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }
}
