/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.io.PrintWriter;
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
public class ZNewBookingGuestServlet extends HttpServlet {

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

        HttpSession session = request.getSession();
        JDBC dbBean = (JDBC)request.getSession().getAttribute("dbbean");
       
        String customerName = request.getParameter("customerName");
        String customerAddress = request.getParameter("customerHomeHouse") + "," + request.getParameter("customerHomeCity") + "," + request.getParameter("customerHomePostcode");

        String currentAddress = request.getParameter("customerStartHouse") + "," + request.getParameter("customerStartCity") + "," + request.getParameter("customerStartPostcode");
        String destinationAddress = request.getParameter("customerDestHouse") + "," + request.getParameter("customerDestCity") + "," + request.getParameter("customerDestPostcode");
        String dateRequired = request.getParameter("customerJournDate");
        String timeRequired = request.getParameter("customerJournTime");
        String dateRegEx = "^\\d{4}-\\d{2}-\\d{2}$";
        String timeRegEx = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
        String postCodeRegEx = "^([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([AZa-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9]?[A-Za-z]))))[0-9][A-Za-z]{2})$";

        String errorMessage = "";

        //check all fields are complete
        if (customerAddress.equals("")
                || timeRequired.equals("")
                || dateRequired.equals("")
                || destinationAddress.equals("")
                || currentAddress.equals("")
                || customerName.equals("")) {
            
            errorMessage = "Please complete all fields";            
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/zNewBookingGuest.jsp").forward(request, response);
            

        } else if (!Pattern.matches(dateRegEx, dateRequired) || !Pattern.matches(timeRegEx, timeRequired) || !Pattern.matches(postCodeRegEx, request.getParameter("customerHomePostcode")) || !Pattern.matches(postCodeRegEx, request.getParameter("customerStartPostcode")) || !Pattern.matches(postCodeRegEx, request.getParameter("customerDestPostcode"))) {

            request.setAttribute("customerName", customerName);
            
            if (!Pattern.matches(dateRegEx, dateRequired)) {
                errorMessage += "Incorrect date format,   ";
            }
            
            if (!Pattern.matches(timeRegEx, timeRequired)) {
                errorMessage += "Incorrect time format,   ";
            } 
            
            if (!Pattern.matches(postCodeRegEx, request.getParameter("customerHomePostcode"))) {
                errorMessage += "Incorrect Home postcode format,   ";
            }
            
            if (!Pattern.matches(postCodeRegEx, request.getParameter("customerStartPostcode"))) {
                errorMessage += "Incorrect journey start postcode format,   ";
            }
            
            if (!Pattern.matches(postCodeRegEx, request.getParameter("customerDestPostcode"))) {
                errorMessage += "Incorrect journey end postcode format,   ";
            }
            
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/zNewBookingGuest.jsp").forward(request, response);

        } else {
//            try {
//                dbBean.createDemand(dbBean.getCustomerID(userName), timeRequired, dateRequired, destinationAddress, currentAddress, customerName);
//                request.setAttribute("message", "Request Sent!");
//                request.getRequestDispatcher("/cuNewJob.jsp").forward(request, response);
//            } catch (SQLException ex) {
//                Logger.getLogger(ZNewBookingGuestServlet.class.getName()).log(Level.SEVERE, null, ex);
//            }
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
