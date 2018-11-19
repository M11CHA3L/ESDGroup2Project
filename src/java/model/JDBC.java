/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
        select("select ROLELEVEL from users where username='" + user + "'");
        while (rs.next()) {
            id = rs.getInt("ROLELEVEL");
        }

        return id;
    }

    public void connect(Connection connection) {
        this.connection = connection;
        if (connection == null) {
            System.out.println("connection failed");
        }
    }

}
