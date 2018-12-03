<%-- 
    Document   : adViewCustomers
    Created on : 02-Dec-2018, 12:37:54
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
        <%
            String customers = (String)request.getAttribute("customers");
            out.print(customers);
        %>
        <jsp:include page='zLogout.jsp'/>
    </body>
</html>
