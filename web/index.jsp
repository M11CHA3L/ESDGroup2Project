
<html>
    <head>
        <title>AlphaCab</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <jsp:include page='zNavMenu.jsp'/>
        <%String s = "<h3>Login</h3>";
        
        if (request.getAttribute("errorMessage") != null) {
                s = (String)request.getAttribute("errorMessage");
            }
            out.print(s);
            %>
        <div id='form_wrap'>
        <form method="POST" action="login.do">
            <table  class="tg">
                <tr>
                    <td>Username</td><td><input type="text" name="uName"></td> 
                </tr>
                <tr>
                    <td>Password</td><td><input type="password" name="pass"></td> 
                </tr>
                <tr>
                    <td></td><td style='text-align: right'><input type="submit" value="login"></td>
              </tr>
        </form> 
        </div>
    </body>
</html>
