/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    
        private void insert(String query) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
         

        } catch (SQLException ex) {
            System.out.println(ex);

        }
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

    public void createDemand(String customerID, String timeRequired, String dateRequired, String destinationAddress, String currentAddress, String name) {
        int ID = Integer.parseInt(customerID);
        
        insert("INSERT INTO DEMANDS (CUSTOMER_ID, TIME, DATE, DESTINATION, ADDRESS, NAME) VALUES ('" + ID + "','"+ timeRequired + "','" + dateRequired + "','"+ destinationAddress + "','" + currentAddress + "','" + name +"')");
    }
    
}
