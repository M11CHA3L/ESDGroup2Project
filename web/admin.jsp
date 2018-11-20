        <% if (session.getAttribute("userRole") != "admin") {
                response.sendRedirect("index.jsp");
            } 
        %>
        
        <head>
            <link rel="stylesheet" type="text/css" href="style.css">
        </head>
        
        <body>
            Welcome Admin<br><br>
            <a href="/ESDGroup2Project/AdminDriversServlet">View Drivers</a><br>
            <a href="">View Customers</a><br>
            <a href="">View Driver availability</a><br>
            <a href="">View Jobs completed over time</a><br>
            <a href="">View Turnover</a><br>
        </body>