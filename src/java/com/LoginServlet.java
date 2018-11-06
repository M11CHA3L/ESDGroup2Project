/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.io.PrintWriter;
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
public class LoginServlet extends HttpServlet {

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

        String user = request.getParameter("uName");
        String password = request.getParameter("pass");

        String s;
        // Check username was entered
        if (user.equals("")) {
            s = "No username Entered";
            request.setAttribute("status", s);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

        // Check if password was entered
        if (password.equals("")) {
            s = "No password entered";
            request.setAttribute("status", s);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

        // Check user exists
        if (dbBean.userExists(user)) {

            // Check password matches
            if (dbBean.passwordMatches(user, password)) {
                // If password matches send to loggedin page

                int priv = 1;

                try {
                    priv = dbBean.getID(user);
                } catch (SQLException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                session.setAttribute("priv", priv);
                switch (priv) {
                    case 0:
                        
                        request.setAttribute("userpage", "admin");
                        request.getRequestDispatcher("/welcome.jsp").forward(request, response);
                        break;
                    case 1:
                        request.setAttribute("userpage", "driver");
                        request.getRequestDispatcher("/welcome.jsp").forward(request, response);
                        break;
                    case 2:
                        request.setAttribute("userpage", "customer");
                        request.getRequestDispatcher("/welcome.jsp").forward(request, response);
                        break;
                }

            } else {
                //password does not match, send to index.jps with error message
                s = "Invalid password, please try again";
                request.setAttribute("status", s);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }

        } else {
            //user does not exist, send to index.jsp with error message
            s = "Invalid username, please try again";
            request.setAttribute("status", s);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Username " + user + " " + " password " + password + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
