<%-- 
    Document   : cuNewJob
    Created on : 02-Dec-2018, 19:34:10
    Author     : michaelcraddock
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page='cuNavMenu.jsp'/>
    <p1>Book a Ride</p1><br><br>
    <form method="POST" action="CuServlet.do">
        Enter full name : <input type="text" name="customerName" ><br> 
        Enter current post code : <input type="text" name="currentAddress" > (in format: SN139RA)<br>
        Enter destination post code : <input type="text" name="destinationAddress" > (in format: SN139RA) <br>
        Enter date required : <input type="text" name="dateRequired" > (in format: YYYY-MM-DD)<br>
        Enter time required : <input type="text" name="timeRequired" > (in format: HH:MM)<br>
        <input type="submit" name="customerOption" value="Request Booking">
    </form> 
    <br>
    <%
        if (request.getAttribute("errorMessage") != null) {
            out.print((String) request.getAttribute("errorMessage"));
        } else if (request.getAttribute("message") != null) {
            out.print((String) request.getAttribute("message"));
        }

    %> 
    <br>
    <jsp:include page='zLogout.jsp'/>
</body>
</html>
