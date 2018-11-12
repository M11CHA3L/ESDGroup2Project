<% if (session.getAttribute("userRole") != "customer") {
                response.sendRedirect("index.jsp");
            } 
        %>
Welcome customer<br><br>

<a href="">Make a booking</a>