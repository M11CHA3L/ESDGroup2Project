<% if (session.getAttribute("userRole") != "customer") {
                response.sendRedirect("index.jsp");
            } 
        %>
Welcome customer<br><br>

<a href="">Make a booking</a>

        <form method="POST" action="createBooking.do">
              Enter current post code : <input type="text" name="currentAddress"><br> 
              Enter destination post code : <input type="ext" name="destinationAddress"><br> 
              <input type="submit" value="createBooking">
        </form> 