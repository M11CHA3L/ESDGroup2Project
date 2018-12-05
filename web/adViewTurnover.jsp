<%-- 
    Document   : adViewTurnover
    Created on : 02-Dec-2018, 12:39:01
    Author     : michaelcraddock
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>View Daily Turnover</title>
    </head>
    <body>
        <div>
            <jsp:include page='adNavMenu.jsp'/>
            <H3>View Daily Turnover</H3>
            <form method="post" action="AdminServlet.do">
                <b>Select Date:</b><br>
                <input type="date" name="date" placeholder="YYYY-MM-DD">
                <input type="submit" name="adminOption" value="Get Daily Turnover">
            </form>
            <%
                if (request.getAttribute("turnover") != null) {
            %>
            <br>
            <%
                    out.print(request.getAttribute("turnover"));
                }
            %>
            <br>
        </div>
        <jsp:include page='zLogout.jsp'/>
    </body>
</html>
