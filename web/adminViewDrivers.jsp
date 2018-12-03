        <%@page import="java.sql.ResultSet"%>
<% if (session.getAttribute("userRole") != "admin") {
                response.sendRedirect("index.jsp");
            } 
        %>
        <head>
            <link rel="stylesheet" type="text/css" href="style.css">
        </head>
        <body>
        <a href="admin.jsp">Back</a><br>
        View Drivers<br>
       <%
           String drivers = (String)request.getAttribute("drivers");
           out.print(drivers);
       %>
       
        </body>