        <% if (session.getAttribute("userRole") != "admin") {
                response.sendRedirect("index.jsp");
            } 
        %>
        
             Welcome Admin<br><br>
            <a href="/ESDGroup2Project/AdminDriversServlet">View Drivers</a><br>
            <a href="">View Customers</a><br>
            <a href="">View Driver availability</a><br>
            <a href="">View Jobs completed</a><br>
            <a href="">View Turnover</a><br>
            <a href="/ESDGroup2Project/bookings.do">View Bookings</a><br>
