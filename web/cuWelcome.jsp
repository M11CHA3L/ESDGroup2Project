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
        <title>Welcome Customer</title>
    </head>
    <body>
        <jsp:include page='cuNavMenu.jsp'/>
        <h3>Welcome Customer<h3/>
        <jsp:include page='zLogout.jsp'/>
    </body>
</html>
