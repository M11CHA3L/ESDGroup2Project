<%-- 
    Document   : adUpdateSelection
    Created on : 02-Dec-2018, 15:48:34
    Author     : michaelcraddock
--%>

<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Driver/Customer</title>
    </head>
    <body>
        <jsp:include page='adNavMenu.jsp'/>

        <%
            HashMap<String, String> columns = (HashMap<String, String>) request.getSession().getAttribute("columnNames");

            out.print("<form method=\"POST\" action=\"AdUpdateCustomerDriverCommitServlet.do\">");
            for (String column : columns.keySet()) {
                if (column.equals("ID") || column.equals("USERNAME")) {

                } else {
                    out.print(column + ": " + "<input type=\"text\" name=\"" + column + "\" value=\"" + columns.get(column) + "\" ><br>");
                }
            }
            out.print("<input type=\"submit\" name=\"submit\" value=\"submit\">");
            out.print("<input type=\"submit\" name=\"submit\" value=\"cancel\">");
            out.print("/<form>");
        %>

        <jsp:include page='zLogout.jsp'/>
    </body>
</html>
