<% if (session.getAttribute("userRole") != "driver") {
                response.sendRedirect("index.jsp");
            } 
        %>

        Welcome driver<br><br>

        <a href="">View jobs</a><br>

