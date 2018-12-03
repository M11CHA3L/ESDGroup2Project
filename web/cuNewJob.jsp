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
     <div id='form_wrap'>
    <form method="POST"  action="CuServlet.do">
      <table id='newjob' class="tg">
        <tr>
          <td class="tg-0lax">Full Name<br><br></td>
          <td class="tg-0lax"><input type="text" name="customerName" ></td>
        </tr>
        <tr>
          <td class="tg-0lax">Current Postcode<br> eg.SN139RA </td>
          <td class="tg-0lax"><input type="text" name="currentAddress" ></td>
        </tr>
        <tr>
          <td class="tg-0lax">Destination Postcode<br> eg.SN139RA </td>
          <td class="tg-0lax"><input type="text" name="destinationAddress" ></td>
        </tr>
        <tr>
          <td class="tg-0lax">Date required<br>YYYY-MM-DD</td>
          <td class="tg-0lax"><input type="text" name="dateRequired" > </td>
        </tr>
        <tr>
          <td class="tg-0lax">Time required<br>HH:MM</td>
          <td class="tg-0lax"><input type="text" name="timeRequired" > </td>
        </tr>
        <tr>
          <td class="tg-0lax"></td>
          <td class="tg-0lax"><input type="submit" value="Request Ride"></td>
        </tr>
      </table>
    </form>
     </div>
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
