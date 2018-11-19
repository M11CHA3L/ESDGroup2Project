<%@page import="java.util.HashMap"%>
<%@page import="java.lang.String"%>
Update selected row:
<% if (session.getAttribute("userRole") != "admin") {
                response.sendRedirect("index.jsp");
            } 
        %>
<%
    HashMap<String, String> columns = (HashMap<String, String>)request.getAttribute("columnNames");
    
    out.print("<form method=\"POST\">");
    for(String column : columns.keySet())
    {
        out.print(column + ": " + "<input type=\"text\" name=\"" + column + "\" value=\"" + columns.get(column) + "\" ><br>");
    }
    out.print("<input type=\"submit\" value=\"submit\">");
    out.print("/<form>");
    %>