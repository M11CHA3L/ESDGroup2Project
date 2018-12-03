<% if (session.getAttribute("userRole") != "customer") {
  response.sendRedirect("index.jsp");
}
%>
<style type="text/css">
.tg  {border-collapse:collapse;border-spacing:0;border:none;}
.tg td{font-family:Arial, text-align: right; sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:0px;overflow:hidden;word-break:normal;}
.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:0px;overflow:hidden;word-break:normal;}
.tg .tg-0lax{text-align:left;vertical-align:top}

.td{
  height:20px;
}
</style>
Welcome customer<br><br>

<h1>Book a Ride</h1><br><br>


<form method="POST" action="createDemand.do">
<table class="tg">
  <tr>
    <th class="tg-0lax">Full Name</th>
    <th class="tg-0lax"><input type="text" name="customerName" ></th>
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
<br>
<%
if (request.getAttribute("errorMessage") != null) {
  out.print((String) request.getAttribute("errorMessage"));
}else if(request.getAttribute("message") != null){
  out.print((String) request.getAttribute("message"));
}

%>
<br>
