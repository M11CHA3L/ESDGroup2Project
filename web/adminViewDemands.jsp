        <%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.sql.ResultSet"%>
<% if (session.getAttribute("userRole") != "admin") {
                response.sendRedirect("index.jsp");
            } 
        %>
        <head>
            <link rel="stylesheet" type="text/css" href="style.css">
        </head>
        <body >
        <a href="admin.jsp">Back</a><br>
        Outstanding Demands<br><br>
       
        <%
      
                String demand = (String) request.getAttribute("demand");
                out.print(demand);
            
        %>
        


        </body>