package com;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.JDBC;

public class DrViewJobsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        JDBC dbBean = (JDBC) request.getSession().getAttribute("dbbean");

        //if complete button selected update selected job to complete
        String complete = request.getParameter("complete");
        if (complete != null) {
            String updated;
            updated = dbBean.setJourneyComplete(request.getParameter("selectedJob"));
            //if update does nothing (returns empty string) return nothing
            if (!"".equals(updated)) {
                request.setAttribute("updated", updated);

                //   Print Invoice - start location, desitination, distance, time, date, customer name
                String[] s = dbBean.returnInvoice(request.getParameter("selectedJob"));
                // <date><time> customer name

                //add these to print invoice
                //PrintWriter out = new PrintWriter("H:\\Personal\\"+ s[1] + ".txt");
                //out.println(s[0]);             
                //out.close();
            }

        }

        String jobs = dbBean.getDriverJobs((String) session.getAttribute("userName"));
        request.setAttribute("jobs", jobs);
        request.getRequestDispatcher("/drViewJobs.jsp").forward(request, response);

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
