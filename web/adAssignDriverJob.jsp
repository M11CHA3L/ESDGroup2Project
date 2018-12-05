<%-- 
    Document   : adAssignDriverJob
    Created on : 03-Dec-2018, 16:39:29
    Author     : jl2-miles
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Assign Driver To Demand</title>
    </head>
    <body>
                <% if (session.getAttribute("userRole") != "admin") {
                response.sendRedirect("index.jsp");
            }
        %>
        <jsp:include page='adNavMenu.jsp'/>
        Assign Driver<br><br>
       
        <%
            if ((String)request.getAttribute("drivers") != null){
                 String drivers = (String) request.getAttribute("drivers");
                out.print(drivers);

            }
               
            if ((String)request.getAttribute("message") != null){
                 out.print((String) request.getAttribute("message"));
            }
               

        %>
        <jsp:include page='zLogout.jsp'/>
    </body>
</html>
