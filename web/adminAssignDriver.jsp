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
        Assign Driver<br><br>
       
        <%
            if ((String)request.getAttribute("drivers") != null){
                 String drivers = (String) request.getAttribute("drivers");
                out.print(drivers);

            }
               
            if ((String)request.getAttribute("message") != null){
                 out.print((String) request.getAttribute("message"));
            }
               

        %>
        


        </body>