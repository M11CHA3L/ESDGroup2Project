        <%@page import="java.sql.ResultSet"%>
<% if (session.getAttribute("userRole") != "admin") {
                response.sendRedirect("index.jsp");
            } 
        %>
        <head>
            <link rel="stylesheet" type="text/css" href="style.css">
        </head>
        <body>
        <a href="driver.jsp">Back</a><br>
       View Drivers
       <%
           String drivers = (String)session.getAttribute("drivers");
           out.print(drivers);
       %>
        </body>