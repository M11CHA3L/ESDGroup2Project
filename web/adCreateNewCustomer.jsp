<%-- 
    Document   : adCreateNewCustomer
    Created on : 02-Dec-2018, 12:40:00
    Author     : michaelcraddock
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Create New Customer</title>
    </head>
    <body>
        <jsp:include page='adNavMenu.jsp'/>
        <% if (session.getAttribute("userRole") != "admin") {
                response.sendRedirect("index.jsp");
            } 
        %>
        <h3>Create Customer</h3><br>
        <form method="POST" style="text-align: right; width:400px; margin: auto;" action="AdminServlet.do">
            Enter full name : <input type="text" name="customerName" ><br> 
            Enter address : <input type="text" name="customerAddress" ><br>
            Enter username : <input type="text" name="customerUsername" ><br>
            Enter password: <input type="text" name="customerPassword" ><br>
        <input type="submit" name="adminOption" value="Add New Customer">
        </form> 
        <br>
        <%
            if (request.getAttribute("errorMessage") != null) {
                out.print((String) request.getAttribute("errorMessage"));
            }else if(request.getAttribute("message") != null){
                out.print((String) request.getAttribute("message"));
            }        
        %> 
        <br>
        <jsp:include page='zLogout.jsp'/>
    </body>
</html>
