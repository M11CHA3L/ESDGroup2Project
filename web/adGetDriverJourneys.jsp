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
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page='adNavMenu.jsp'/>
                <%
            out.print(request.getAttribute("result"));
            %>
            <jsp:include page='zLogout.jsp'/>
    </body>
</html>
