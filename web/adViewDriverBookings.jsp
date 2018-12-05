<%-- 
    Document   : adViewDriverBookings
    Created on : 02-Dec-2018, 12:39:43
    Author     : michaelcraddock
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>View Driver Bookings</title>
    </head>
    <body>
                <% if (session.getAttribute("userRole") != "admin") {
                response.sendRedirect("index.jsp");
            }
        %>
        <jsp:include page='adNavMenu.jsp'/>
        <div>
            <br>
            <%
                out.print(request.getAttribute("drivers"));
            %>
            <br>
        </div>
        <jsp:include page='zLogout.jsp'/>
    </body>
</html>
