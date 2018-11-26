/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.JDBC;

/**
 *
 * @author patin_000
 */
public class DeleteSelectionServlet extends HttpServlet {

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
        
        String driverOrCustomer = (String)request.getSession().getAttribute("driverOrCustomer");
        String deleteChoice = (String)request.getParameter("deleteConfirmation");
        
        String columnValue = (String)request.getSession().getAttribute("deleteChoice");
        String tableName = (String)request.getSession().getAttribute("tableName");
        String columnName = (String)request.getSession().getAttribute("columnName");
        
        if(deleteChoice == null)
        {
            request.getRequestDispatcher("/deleteSelection.jsp").forward(request, response);
        }
        else if (deleteChoice.equals("YES"))
        {
            
            dbBean.DeleteRowFromTable(tableName, columnName, columnValue);
            
            if(driverOrCustomer == "driver")
            {
                request.setAttribute("adminOption", "View Drivers");
                request.getRequestDispatcher("/AdminServlet").forward(request, response);
            }
            else if(driverOrCustomer == "customer")
            {
                request.setAttribute("adminOption", "View Customers");
                request.getRequestDispatcher("/AdminServlet.jsp").forward(request, response);
            }
        }
        else if (deleteChoice.equals("NO"))
        {
            
            if(driverOrCustomer == "driver")
            {
                request.setAttribute("adminOption", "View Drivers");
                request.getRequestDispatcher("/AdminServlet").forward(request, response);
            }
            else if(driverOrCustomer == "customer")
            {
                request.setAttribute("adminOption", "View Customers");
                request.getRequestDispatcher("/AdminServlet.jsp").forward(request, response);
            }
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
