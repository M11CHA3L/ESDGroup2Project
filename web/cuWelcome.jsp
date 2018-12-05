<%--
    Document   : cuWelcome
    Created on : 02-Dec-2018, 18:30:45
    Author     : michaelcraddock
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" type="text/css" href="style.css">
        <title>Welcome Customer</title>
        <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
    </head>
    <body>
        <% if (session.getAttribute("userRole") != "customer") {
                response.sendRedirect("index.jsp");
            }
        %>
        <jsp:include page='cuNavMenu.jsp'/>
        <h3>Welcome Customer<h3/>
        <jsp:include page='zLogout.jsp'/>
    </body>
</html>
