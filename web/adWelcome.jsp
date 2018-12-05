<%-- 
    Document   : adminWelcom
    Created on : 02-Dec-2018, 12:35:03
    Author     : michaelcraddock
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Welcome Admin</title>
    </head>
    <body>
        <jsp:include page='adNavMenu.jsp'/>
        <h3>Welcome Admin<h3/>
      
        <jsp:include page='zLogout.jsp'/>
    </body>
</html>
