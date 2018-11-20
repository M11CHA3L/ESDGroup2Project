<% if (session.getAttribute("userRole") != "driver") {
                response.sendRedirect("index.jsp");
            } 
        %>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
        
</head>

<body>
        Welcome driver<br><br>

        <a href="">View jobs</a><br>

</body>