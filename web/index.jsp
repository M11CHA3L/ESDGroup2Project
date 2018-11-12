
<html>
    <head>
        <title>AlphaCab</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <%String s = "Enter username and password";
        
        if(session.getAttribute("userRole") != null){
            request.getRequestDispatcher("/welcome.jsp").forward(request, response);
        }
        if (request.getAttribute("errorMessage") != null) {
                s = (String)request.getAttribute("errorMessage");
            }
            out.print(s);
            %>
        <form method="POST" action="login.do">
              Enter username : <input type="text" name="uName"><br> 
              Enter password : <input type="password" name="pass"><br> 
              <input type="submit" value="login">
        </form> 
        
    </body>
</html>
