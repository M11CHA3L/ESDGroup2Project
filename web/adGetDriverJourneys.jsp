<%-- 
    Document   : adGetDriverJourneys
    Created on : 02-Dec-2018, 16:49:18
    Author     : michaelcraddock
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Selected Driver Journeys</title>
    </head>
    <body>
                <% if (session.getAttribute("userRole") != "admin") {
                response.sendRedirect("index.jsp");
            }
        %>
        <jsp:include page='adNavMenu.jsp'/>
        <div>
            <br>
            <%
                out.print(request.getAttribute("journey"));
            %>
            <br>
        </div>
        <jsp:include page='zLogout.jsp'/>
    </body>
</html>
