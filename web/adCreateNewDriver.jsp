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
        <title>Create New Driver</title>
    </head>
    <body>
        <jsp:include page='adNavMenu.jsp'/>
        <% if (session.getAttribute("userRole") != "admin") {
                response.sendRedirect("index.jsp");
            }
        %>
        <h3>Create Driver</h3>
        <form method="POST" style="text-align: right; width:400px; margin: auto;" action="AdminServlet.do">
            Enter full name : <input type="text" name="driverName" ><br> 
            Enter registration : <input type="text" name="driverRegistration" ><br>
            Enter username : <input type="text" name="driverUsername" ><br>
            Enter password: <input type="password" name="driverPassword" ><br>
            <input type="submit" name="adminOption" value="Add New Driver">
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
