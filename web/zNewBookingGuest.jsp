<%-- 
    Document   : zNewBookingGuest
    Created on : 04-Dec-2018, 21:05:23
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
        <jsp:include page='zNavMenu.jsp'/>
        <div>
            <%
                if (request.getAttribute("errorMessage") != null) {
                    out.print((String) request.getAttribute("errorMessage"));
                } else if (request.getAttribute("message") != null) {
                    out.print((String) request.getAttribute("message"));
                }

            %>
        </div>
        <div>
            <form method="POST" style="text-align: right; width:400px; margin: auto;" action="zServlet.do">
                <H3 style="text-align: center"><b>Customer Details</b></h3>
                Full Name : <input type="text" name="customerName" ><br>
                Home - House No./Street : <input type="text" name="customerHomeHouse" ><br>               
                Home - City : <input type="text" name="customerHomeCity" ><br>               
                Home - Postcode : <input type="text" name="customerHomePostcode" ><br>
                <h3 style="text-align: center"><b>Journey Details</b></h3>
                Start - House No./Street : <input type="text" name="customerStartHouse" ><br>
                Start - City : <input type="text" name="customerStartCity" ><br>
                Start - Postcode: <input type="text" name="customerStartPostcode" ><br>
                End - House No./Street : <input type="text" name="customerDestHouse" ><br>
                End - City : <input type="text" name="customerDestCity" ><br>
                End - Postcode: <input type="text" name="customerDestPostcode" ><br><br>
                Date Required: <input type="text" name="customerJournDate" placeholder="YYYY-MM-DD"><br>
                Time Required: <input type="text" name="customerJournTime" placeholder="HH:MM"><br><br>
                <input type="submit" name="zOption" value="Create Booking">
            </form> 
        </div>
    </body>
</html>
