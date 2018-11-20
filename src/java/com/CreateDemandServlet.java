package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        String userName = (String)session.getAttribute("userName");

        try {
            dbBean.createDemand(dbBean.getCustomerID(userName), timeRequired, dateRequired, destinationAddress, currentAddress, customerName);
        } catch (SQLException ex) {
            Logger.getLogger(CreateDemandServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 



//        String errorMessage;
//        // Check username was entered
//        if (username.equals("")) {
//            errorMessage = "No username Entered";
//            request.setAttribute("errorMessage", errorMessage);
//            request.getRequestDispatcher("/index.jsp").forward(request, response);
//        }
//
//        // Check if password was entered
//        if (password.equals("")) {
//            errorMessage = "No password entered";
//            request.setAttribute("errorMessage", errorMessage);
//            request.getRequestDispatcher("/index.jsp").forward(request, response);
//        }
//
//        // Check user exists
//        if (dbBean.userExists(username)) {
//            
//            // Check password matches
//            if (dbBean.passwordMatches(username, password)) {
//                // If password matches send to welcome page
//
//                int user = 2;
//
//                try {
//                    user = dbBean.getRole(username);
//
//                } catch (SQLException ex) {
//                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//                switch (user) {
//                    case 3:
//                        session.setAttribute("userRole", "admin");
//                        break;
//                    case 2:
//                        session.setAttribute("userRole", "driver");
//                        break;
//                    case 1:
//                        session.setAttribute("userRole", "customer");
//                        break;
//                }
//                request.getRequestDispatcher("/welcome.jsp").forward(request, response);
//
//            } else {
//                //password does not match, send to index.jsp with error message
//                errorMessage = "Invalid password, please try again";
//                request.setAttribute("errorMessage", errorMessage);
//                request.getRequestDispatcher("/index.jsp").forward(request, response);
//            }
//
//        } else {
//            //user does not exist, send to index.jsp with error message
//            errorMessage = "Invalid username, please try again";
//            request.setAttribute("errorMessage", errorMessage);
//            request.getRequestDispatcher("/index.jsp").forward(request, response);
//        } 



//        String errorMessage;
//        // Check username was entered
//        if (username.equals("")) {
//            errorMessage = "No username Entered";
//            request.setAttribute("errorMessage", errorMessage);
//            request.getRequestDispatcher("/index.jsp").forward(request, response);
//        }
//
//        // Check if password was entered
//        if (password.equals("")) {
//            errorMessage = "No password entered";
//            request.setAttribute("errorMessage", errorMessage);
//            request.getRequestDispatcher("/index.jsp").forward(request, response);
//        }
//
//        // Check user exists
//        if (dbBean.userExists(username)) {
//            
//            // Check password matches
//            if (dbBean.passwordMatches(username, password)) {
//                // If password matches send to welcome page
//
//                int user = 2;
//
//                try {
//                    user = dbBean.getRole(username);
//
//                } catch (SQLException ex) {
//                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//                switch (user) {
//                    case 3:
//                        session.setAttribute("userRole", "admin");
//                        break;
//                    case 2:
//                        session.setAttribute("userRole", "driver");
//                        break;
//                    case 1:
//                        session.setAttribute("userRole", "customer");
//                        break;
//                }
//                request.getRequestDispatcher("/welcome.jsp").forward(request, response);
//
//            } else {
//                //password does not match, send to index.jsp with error message
//                errorMessage = "Invalid password, please try again";
//                request.setAttribute("errorMessage", errorMessage);
//                request.getRequestDispatcher("/index.jsp").forward(request, response);
//            }
//
//        } else {
//            //user does not exist, send to index.jsp with error message
//            errorMessage = "Invalid username, please try again";
//            request.setAttribute("errorMessage", errorMessage);
//            request.getRequestDispatcher("/index.jsp").forward(request, response);
//        }



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
