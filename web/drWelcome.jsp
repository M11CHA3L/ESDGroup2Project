<%-- 
    Document   : drWelcome
    Created on : 02-Dec-2018, 18:30:29
    Author     : michaelcraddock
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page='drNavMenu.jsp'/>
        <h3>Welcome Driver<h3/>
        <jsp:include page='zLogout.jsp'/>
    </body>
</html>
