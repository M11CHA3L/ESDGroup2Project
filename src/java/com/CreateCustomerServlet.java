package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.JDBC;

/**
 *
 * @author michaelcraddock
 */
public class CreateCustomerServlet extends HttpServlet {

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

        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");

        JDBC dbBean = (JDBC)request.getSession().getAttribute("dbbean");
       
        String customerName = request.getParameter("customerName");
        String customerAddress = request.getParameter("customerAddress");
        String customerUsername = request.getParameter("customerUsername");
        String customerPassword = request.getParameter("customerPassword");

        String dateRegEx = "^\\d{4}-\\d{2}-\\d{2}$";
        String timeRegEx = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$";
        String postCodeRegEx = "^([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([AZa-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9]?[A-Za-z]))))[0-9][A-Za-z]{2})$";

        String errorMessage = "";
        //check all fields are complete
        if (customerPassword.equals("")
                || customerUsername.equals("")
                || customerAddress.equals("")
                || customerName.equals("")) {
            
            errorMessage = "Please complete all fields";            
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/adCreateNewCustomer.jsp").forward(request, response);
            

        } else {

            dbBean.createCustomer(customerPassword, customerAddress, customerUsername, customerName);
            request.setAttribute("message", "Customer Created!");
            request.getRequestDispatcher("/adCreateNewCustomer.jsp").forward(request, response);

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
