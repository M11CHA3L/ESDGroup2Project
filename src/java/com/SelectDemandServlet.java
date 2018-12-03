
package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.JDBC;

public class SelectDemandServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        JDBC dbBean = (JDBC)request.getSession().getAttribute("dbbean");
        
        String s = "";
        String demandID  = "";
        String demandDestination  = "";
        String customerID  = "";
        String demandDate  = "";
        String demandTime = "";
                
        s += "<form method=\"post\" action=\"assignDriver.do\">";
           
        try {
             
            //fetch demand info using demand ID from previous radio
            ResultSet demand = dbBean.getDemandByID((String)request.getParameter("demandRadio"));
            demand.next();
            
            //fetch available driver based in demand date
            ResultSet drivers = dbBean.getAvailableDrivers(demand.getString("DATE"));            
            
            //build new radio's with available drivers
            while (drivers.next()) {
                s += drivers.getString("Name") + " " + "<input type='radio' name='driverRadio' value='" + drivers.getString("ID") + "'><br>";
            }
            s += "<input type='submit' name='action' value='Assign Driver'><br><input type='hidden' name='demandID' value='" + demand.getString("ID") +"'>";
            request.setAttribute("demand", s);
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("drivers", s);

        
        request.getRequestDispatcher("/adminAssignDriver.jsp").forward(request, response);

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

}
