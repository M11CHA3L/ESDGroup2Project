<%-- 
    Document   : results
    Created on : 20-Nov-2018, 21:51:55
    Author     : Jon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            out.print(request.getAttribute("result"));
            %>
    </body>
</html>
