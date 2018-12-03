<%-- 
    Document   : adCreateNewDriver
    Created on : 02-Dec-2018, 12:40:08
    Author     : michaelcraddock
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page='adNavMenu.jsp'/>
        <% if (session.getAttribute("userRole") != "admin") {
                response.sendRedirect("index.jsp");
            }
        %>
        <p1>Create Driver</p1><br><br>
        <form method="POST" action="AdminServlet.do">
            Enter full name : <input type="text" name="driverName" ><br> 
            Enter registration : <input type="text" name="driverRegistration" ><br>
            Enter username : <input type="text" name="driverUsername" ><br>
            Enter password: <input type="text" name="driverPassword" ><br>
            <input type="submit" name="adminOption" value="Create New Driver">
        </form> 
        <br>
        <%
            if (request.getAttribute("errorMessage") != null) {
                out.print((String) request.getAttribute("errorMessage"));
            } else if (request.getAttribute("message") != null) {
                out.print((String) request.getAttribute("message"));
            }

        %> 
        <br>
        <jsp:include page='zLogout.jsp'/>
    </body>
</html>
