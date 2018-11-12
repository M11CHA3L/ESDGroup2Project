        <% if (session.getAttribute("userRole") != "admin") {
                response.sendRedirect("index.jsp");
            } 
        %>
       Welcome admin<br><br>
       <a href="">Add a driver</a><br>
       <a href="">Add a customer</a><br>
       <a href="">View all drivers</a><br>
       <a href="">View all customers</a><br>