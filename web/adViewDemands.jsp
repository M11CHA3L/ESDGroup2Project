<%-- 
    Document   : adViewDemands
    Created on : 03-Dec-2018, 16:34:07
    Author     : jl2-miles
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page='adNavMenu.jsp'/>
        Outstanding Demands<br><br>

        <%

            String demand = (String) request.getAttribute("demand");
            out.print(demand);

        %>
        <jsp:include page='zLogout.jsp'/>
    </body>
</html>
