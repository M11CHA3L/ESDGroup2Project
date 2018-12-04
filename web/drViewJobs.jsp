<%-- 
    Document   : drViewJobs
    Created on : 02-Dec-2018, 19:35:23
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
        <div>
            <br>
            <%
                if (request.getAttribute("updated") != null) {
                    out.println(request.getAttribute("updated")); %>
            <br><br>
            <%
                }
            %>

            <%
                if (request.getAttribute("jobs") != null) {
                    out.println(request.getAttribute("jobs"));%>
            <br>
            <%
                }
            %>
            
        </div>
        <jsp:include page='zLogout.jsp'/>
    </body>
</html>
