<%-- 
    Document   : Welcome
    Created on : 06-Nov-2018, 17:46:56
    Authors     : Jon
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Welcome</title>
    </head>
    <body>
        <% if (session.getAttribute("userRole") == null) {
                response.sendRedirect("index.jsp");
            } else {

                String s = (String) session.getAttribute("userRole");%> 
        <jsp:include page='<%= s + ".jsp"%>' />
        <%}%>
        
        <form method="POST" action="logout.do">
            <input type="submit" value ="logout">
        </form>
       
    </body>

</html>
