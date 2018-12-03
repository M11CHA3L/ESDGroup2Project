/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.JDBC;

/**
 *
 * @author jl2-miles
 */
public class AdAssignDriverServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
                JDBC dbBean = (JDBC)request.getSession().getAttribute("dbbean");
               
        try {
            //fetch demand info using demand ID from previous radio
            ResultSet demand = dbBean.getDemandByID((String) request.getParameter("demandID"));
            demand.next();
            calcDistance();
            dbBean.createJourney((String)demand.getString("CUSTOMER_ID"), (String)demand.getString("DESTINATION"), "5", (String)request.getParameter("driverRadio"), (String)demand.getString("DATE"), (String)demand.getString("ID"), (String)demand.getString("TIME"));
            dbBean.setDemandStatus("ASSIGNED", (String)demand.getString("ID"));
            request.setAttribute("message", "Journey Created || Trip to " + (String)demand.getString("DESTINATION") + " on " + (String)demand.getString("DATE")+  " at " + (String)demand.getString("TIME") + " has been assigned a driver");
        } catch (SQLException ex) {
            Logger.getLogger(AdAssignDriverServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.getRequestDispatcher("/adAssignDriverJob.jsp").forward(request, response);
        
    }



    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void calcDistance() throws MalformedURLException, IOException{
        final String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=";
        final String key = "AIzaSyAjXON-EKXSwBEk2bqnwj9Nc7nBPcYqD8I";
        String start = "bs200hu";
        String end = "bs348jh";
        URL u = new URL(url + start +"&destinations=" + end + "&departure_time=now&key=" + key);
        HttpURLConnection con = (HttpURLConnection) u.openConnection();
        con.setRequestMethod("GET");
        
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while((inputLine = in.readLine()) != null){
            content.append(inputLine);
        }
        System.out.println(content);
        in.close();
    }

}
