<%-- 
    Document   : deleteSelection
    Created on : 21-Nov-2018, 17:37:07
    Author     : patin_000
--%>
<% if (session.getAttribute("userRole") != "admin") {
                response.sendRedirect("index.jsp");
            } 
        %>
<%
    String rowValue = request.getParameter("deleteChoice");
    String tableName = request.getParameter("tableName");
    String columnName = request.getParameter("columnName");
    
    request.getSession().setAttribute("deleteChoice", rowValue);
    request.getSession().setAttribute("tableName", tableName);
    request.getSession().setAttribute("columnName", columnName);

%>
        
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DELETE Confirm</title>
    </head>
    <body>
        <%
            out.print("Are you sure you wish to delete '" + columnName + " = " + rowValue + "' from the " + tableName + " table?");
        %>
        <form method="POST" action="DeleteSelectionServlet.do">
            <input type="submit" name="deleteConfirmation" value="YES"><br>
            <input type="submit" name="deleteConfirmation" value="NO"><br>
        </form>
    </body>
</html>
