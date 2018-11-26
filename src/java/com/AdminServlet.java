/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.io.PrintWriter;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.JDBC;

/**
 *
 * @author patin_000
 */
public class AdminServlet extends HttpServlet {

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
        
        String option = request.getParameter("adminOption");
        
        if(option == null)
        {
            option = (String)request.getAttribute("adminOption");
        }
        
        switch(option)
        {
            case "View Drivers":
                request.getSession().setAttribute("driverOrCustomer", "driver");
                
                String drivers = dbBean.ToEditTable("Select * from DRIVERS", "USERNAME", "DRIVERS");
                request.setAttribute("drivers", drivers);
                request.getRequestDispatcher("/adminViewDrivers.jsp").forward(request, response);
                break;
            case "View Customers":
                request.getSession().setAttribute("driverOrCustomer", "customer");
                
                String customers = dbBean.ToEditTable("Select * from CUSTOMERS", "USERNAME", "CUSTOMERS");
                request.setAttribute("customers", customers);
                request.getRequestDispatcher("/adminViewCustomers.jsp").forward(request, response);
                break;
            case "View Driver Availability":
                
                break;
            case "View Turnover":
                
                break;
            case "View Bookings":
                String bookings = dbBean.getDrivers();
                request.setAttribute("drivers", bookings);
                request.getRequestDispatcher("/adminviewbooking.jsp").forward(request, response);
                break;
            case "Create Customer":
                request.getRequestDispatcher("/createCustomer.jsp").forward(request, response);
                break;
            case "Create Driver":
                request.getRequestDispatcher("/createDriver.jsp").forward(request, response);
                break;
        }
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
