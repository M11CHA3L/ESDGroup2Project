        <%@page import="java.sql.ResultSet"%>
<% if (session.getAttribute("userRole") != "admin") {
                response.sendRedirect("index.jsp");
            } 
        %>
       View Drivers
       <%
           String drivers = (String)session.getAttribute("drivers");
           out.print(drivers);
       %>