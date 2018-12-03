/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.io.PrintWriter;
import javax.jms.Session;
import javax.servlet.RequestDispatcher;
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

        //JDBC dbBean = (JDBC)request.getSession().getAttribute("dbbean");
        //String option = (String)request.getParameter("adminOption");
        RequestDispatcher rd;

        switch ((String) request.getParameter("adminOption")) {
            case "View Drivers":
                rd = request.getRequestDispatcher("/AdViewDriversServlet.do");
                rd.forward(request, response);

                //response.sendRedirect("AdViewDriversServlet.do");
                //response.sendRedirect(request.getContextPath() + "/adViewDriversServlet");
//                request.getSession().setAttribute("driverOrCustomer", "driver");
//                
//                String drivers = dbBean.ToEditTable("Select * from DRIVERS", "USERNAME", "DRIVERS");
//                request.setAttribute("drivers", drivers);
//                request.getRequestDispatcher("/adminViewDrivers.jsp").forward(request, response);
                break;
            case "View Customers":
                rd = request.getRequestDispatcher("/AdViewCustomersServlet.do");
                rd.forward(request, response);

//                request.getSession().setAttribute("driverOrCustomer", "customer");
//                
//                String customers = dbBean.ToEditTable("Select * from CUSTOMERS", "USERNAME", "CUSTOMERS");
//                request.setAttribute("customers", customers);
//                request.getRequestDispatcher("/adminViewCustomers.jsp").forward(request, response);
                break;
            case "View New Demands":
//                rd = request.getRequestDispatcher("/AdViewNewDemandsServlet.do");
//                rd.forward(request, response);
                request.getRequestDispatcher("/adViewNewDemands.jsp").forward(request, response);
                break;
            case "View Turnover":
//                rd = request.getRequestDispatcher("/AdViewTurnoverServlet.do");
//                rd.forward(request, response);
                request.getRequestDispatcher("/adViewTurnover.jsp").forward(request, response);
                //response.sendRedirect("adViewTurnover.jsp");
                break;
            case "View Driver Bookings":
                rd = request.getRequestDispatcher("/AdViewDriverBookingsServlet.do");
                rd.forward(request, response);
                
//                String bookings = dbBean.getDrivers();
//                request.setAttribute("drivers", bookings);
//                request.getRequestDispatcher("/adminviewbooking.jsp").forward(request, response);
                break;
            case "Get Driver Journeys":
                rd = request.getRequestDispatcher("/AdGetDriverJourneysServlet.do");
                rd.forward(request, response);
                break;
            case "Create New Customer":
                request.getRequestDispatcher("/adCreateNewCustomer.jsp").forward(request, response);
                break;
            case "Create New Driver":
                request.getRequestDispatcher("/adCreateNewDriver.jsp").forward(request, response);
                break;
            case "Add New Customer":
                rd = request.getRequestDispatcher("/AdCreateNewCustomerServlet.do");
                rd.forward(request, response);
                break;
            case "Add New Driver":
                rd = request.getRequestDispatcher("/AdCreateNewDriverServlet.do");
                rd.forward(request, response);
                break;
            default:
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
