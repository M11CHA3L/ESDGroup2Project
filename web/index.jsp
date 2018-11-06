<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>AlphaCab</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <%String s = "Enter username and password";
        
        if (request.getAttribute("status") != null) {
                s = (String)request.getAttribute("status");
            }
            out.print(s);
            %>
        <form method="POST" action="login.do">
              Enter username : <input type="text" name="uName" value="username"><br> 
              Enter password : <input type="password" name="pass"><br> 
              <input type="submit" value="login">
        </form> 
        
    </body>
</html>
