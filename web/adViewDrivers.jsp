<%-- 
    Document   : adViewDrivers
    Created on : 02-Dec-2018, 12:37:43
    Author     : michaelcraddock
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>View Drivers</title>
    </head>
    <body>
                <% if (session.getAttribute("userRole") != "admin") {
                response.sendRedirect("index.jsp");
            }
        %>
        <jsp:include page='adNavMenu.jsp'/>
        <form action="AdFilterDriverServlet.do" method="POST">
            <input type="submit" value="Enable/Disable filtering active drivers">
        </form>
        <%
            out.print((String) request.getAttribute("drivers"));
        %>
        <jsp:include page='zLogout.jsp'/>
    </body>
</html>
