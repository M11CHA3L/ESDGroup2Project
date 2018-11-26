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
        View Customers<br>
       <%
           String customers = (String)request.getAttribute("customers");
           out.print(customers);
       %>
       
        </body>