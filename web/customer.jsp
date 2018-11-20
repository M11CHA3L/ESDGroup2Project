<% if (session.getAttribute("userRole") != "customer") {
                response.sendRedirect("index.jsp");
            } 
        %>
Welcome customer<br><br>
    
<p1>Book a Ride</p1><br><br>
        <form method="POST" action="createDemand.do">
              Enter full name : <input type="text" name="customerName"><br> 
              Enter current post code : <input type="text" name="currentAddress"> (in format: SN139RA)<br>
              Enter destination post code : <input type="text" name="destinationAddress"> (in format: SN139RA) <br>
              Enter date required : <input type="text" name="dateRequired"> (in format: YYYY-MM-DD)<br>
              Enter time required : <input type="text" name="timeRequired"> (in format: HH:MM)<br>
              <input type="submit" value="Request Ride">
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
   