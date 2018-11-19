        <%@page import="java.sql.ResultSet"%>
<% if (session.getAttribute("userRole") != "admin") {
                response.sendRedirect("index.jsp");
            } 
        %>
        <a href="driver.jsp">Back</a><br>
       View Drivers
       <%
           String drivers = (String)session.getAttribute("drivers");
           out.print(drivers);
       %>