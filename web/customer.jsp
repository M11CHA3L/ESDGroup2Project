<% if (session.getAttribute("userRole") != "customer") {
                response.sendRedirect("index.jsp");
            } 
        %>
Welcome customer<br><br>

<a href="">Make a booking</a>

        <form method="POST" action="createDemand.do">
              Enter full name : <input type="text" name="customerName"><br> 
              Enter current post code : <input type="text" name="currentAddress"><br> 
              Enter destination post code : <input type="text" name="destinationAddress"><br>
              Enter date required : <input type="text" name="dateRequired"><br> 
              Enter time required : <input type="text" name="timeRequired"><br> 
              <input type="submit" value="Request Ride">
        </form> 