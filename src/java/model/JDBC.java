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
import java.util.ArrayList;
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
    
    
    private ArrayList rsToList() throws SQLException {
        ArrayList aList = new ArrayList();

        int cols = rs.getMetaData().getColumnCount();
        while (rs.next()) { 
          String[] s = new String[cols];
          for (int i = 1; i <= cols; i++) {
            s[i-1] = rs.getString(i);
          } 
          aList.add(s);
        } // while    
        return aList;
    } //rsToList
 
    private String makeTable(ArrayList list) {
        StringBuilder b = new StringBuilder();
        String[] row;
        b.append("<table border=\"3\">");
        for (Object s : list) {
          b.append("<tr>");
          row = (String[]) s;
            for (String row1 : row) {
                b.append("<td>");
                b.append(row1);
                b.append("</td>");
            }
          b.append("</tr>\n");
        } // for
        b.append("</table>");
        return b.toString();
    }//makeHtmlTable
    
    //User exists
    //user & pass exists
      private void select(String query){
        //Statement statement = null;
        
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            //statement.close();
        }
        catch(SQLException e) {
            System.out.println("way way"+e);
            //results = e.toString();
        }
    }
       public String retrieve(String query) throws SQLException {
        String results="";
        select(query);
        try {
            
            if (rs==null)
                System.out.println("rs is null");
            else
                results = makeTable(rsToList());
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return makeTable(rsToList());//results;
    }
       
     public boolean userExists(String user) {
        boolean bool = false;
        try  {
            select("select username from users where username='"+user+"'");
            if(rs.next()) {
                System.out.println("TRUE");     
                makeTable(rsToList());
                bool = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
    }
     
     public boolean passwordMatches(String user, String pass){
        boolean matches = false;
         select("select username from users where username='"+user+"' AND password='" + pass +"'");
        try {
            if (rs.next()) {
                matches = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
         return matches;
     }
     
     public int getID(String user) throws SQLException{
         int id = 0;
         select("select USERID from users where username='"+user+"'");
         while(rs.next()){
              id = rs.getInt("USERID");
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
