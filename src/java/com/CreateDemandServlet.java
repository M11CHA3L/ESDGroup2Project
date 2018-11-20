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
public class CreateDemandServlet extends HttpServlet {

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

        JDBC dbBean = new JDBC();
        dbBean.connect((Connection) request.getServletContext().getAttribute("connection"));
        session.setAttribute("dbbean", dbBean);

        String customerName = request.getParameter("customerName");
        String currentAddress = request.getParameter("currentAddress");
        String destinationAddress = request.getParameter("destinationAddress");
        String dateRequired = request.getParameter("dateRequired");
        String timeRequired = request.getParameter("timeRequired");
        String userName = (String) session.getAttribute("userName");
        String dateRegEx = "^\\d{4}-\\d{2}-\\d{2}$";
        String timeRegEx = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$";
        String postCodeRegEx = "^([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([AZa-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9]?[A-Za-z]))))[0-9][A-Za-z]{2})$";

        String errorMessage = "";
        //check all fields are complete
        if (userName.equals("")
                || timeRequired.equals("")
                || dateRequired.equals("")
                || destinationAddress.equals("")
                || currentAddress.equals("")
                || customerName.equals("")) {
            
            errorMessage = "Please complete all fields";            
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/welcome.jsp").forward(request, response);
            

        } else if (!Pattern.matches(dateRegEx, dateRequired) || !Pattern.matches(timeRegEx, timeRequired) || !Pattern.matches(postCodeRegEx, currentAddress) || !Pattern.matches(postCodeRegEx, destinationAddress)) {

            if (!Pattern.matches(dateRegEx, dateRequired)) {
                errorMessage += "incorrect date format,   ";
            }
            if (!Pattern.matches(timeRegEx, timeRequired)) {
                errorMessage += "incorrect time format,   ";
            }            
            if (!Pattern.matches(postCodeRegEx, currentAddress)) {
                errorMessage += "incorrect current postcode format,   ";
            }
            if (!Pattern.matches(postCodeRegEx, destinationAddress)) {
                errorMessage += "incorrect destination postcode format,   ";
            }
            
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/welcome.jsp").forward(request, response);

        } else {
            try {
                dbBean.createDemand(dbBean.getCustomerID(userName), timeRequired, dateRequired, destinationAddress, currentAddress, customerName);
            } catch (SQLException ex) {
                Logger.getLogger(CreateDemandServlet.class.getName()).log(Level.SEVERE, null, ex);
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
