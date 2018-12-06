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
        <% if (session.getAttribute("userRole") != "customer") {
                response.sendRedirect("index.jsp");
            }
        %>
        <jsp:include page='cuNavMenu.jsp'/>
     <div id='form_wrap'>
    <form method="POST"  action="CuServlet.do">
      <table id='newjob' class="tg">
        <tr>
          <td class="tg-0lax">Full Name<br><br></td>
          <td class="tg-0lax"><input type="text" name="customerName" ></td>
        </tr>
        <tr>
          <td class="tg-0lax">Current House No/Street</td>
          <td class="tg-0lax"><input type="text" name="currentHouse" ></td>
        </tr>
        <tr>
          <td class="tg-0lax">Current City</td>
          <td class="tg-0lax"><input type="text" name="currentCity" ></td>
        </tr>
        <tr>
          <td class="tg-0lax">Current Postcode<br> eg.SN139RA </td>
          <td class="tg-0lax"><input type="text" name="currentPost" ></td>
        </tr>
        <tr>
          <td class="tg-0lax">Destination House No/Street</td>
          <td class="tg-0lax"><input type="text" name="destinationHouse" ></td>
        </tr>
        <tr>
          <td class="tg-0lax">Destination City</td>
          <td class="tg-0lax"><input type="text" name="destinationCity" ></td>
        </tr>
        <tr>
          <td class="tg-0lax">Destination Postcode<br> eg.SN139RA </td>
          <td class="tg-0lax"><input type="text" name="destinationPost" ></td>
        </tr>
        <tr>
          <td class="tg-0lax">Date required</td>
          <td class="tg-0lax"><input type="date" name="dateRequired" placeholder="YYYY-MM-DD"> </td>
        </tr>
        <tr>
          <td class="tg-0lax">Time required</td>
          <td class="tg-0lax"><input type="time" name="timeRequired" placeholder="HH-MM"> </td>
        </tr>
        <tr>
          <td class="tg-0lax"></td>
          <td class="tg-0lax"><input type="submit" name="customerOption" value="Request Booking"></td>
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
