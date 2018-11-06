<%-- 
    Document   : loggedin
    Created on : 06-Nov-2018, 17:46:56
    Author     : Jon
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <% if (session.getAttribute("dbbean") == null) { %>
    <p> You dont have access to this page! </p>
<% }else{
%>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logged in!</title>
    </head>
    <body>
        <% String s = (String)request.getAttribute("userpage"); %> <jsp:include page='<%= s + ".html" %>' />

    </body>
    <%}%>
</html>
