        <% if (session.getAttribute("userRole") != "admin") {
                response.sendRedirect("index.jsp");
            } 
        %>
             <link rel="stylesheet" type="text/css" href="style.css">
             
             Welcome Admin<br><br>
             
            <form method="POST" action="AdminServlet">
                <input type="submit" name="adminOption" value ="View Drivers"><br>
                <input type="submit" name="adminOption" value ="View Customers"><br>
                <input type="submit" name="adminOption" value ="View Driver Availability"><br>
                <input type="submit" name="adminOption" value ="View Turnover"><br>
                <input type="submit" name="adminOption" value ="View Bookings"><br>
                <input type="submit" name="adminOption" value ="Create Customer"><br>
                <input type="submit" name="adminOption" value ="Create Driver"><br>
            </form>