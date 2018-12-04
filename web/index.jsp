
<html>
    <head>
        <title>AlphaCab</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>AlphaCab</h1>
        <%String s = "";

            if (request.getAttribute("errorMessage") != null) {
                s = (String) request.getAttribute("errorMessage");
            }
            out.print(s);
        %>
        <div id='form_wrap'>
            <form method="POST" action="login.do">
             <div class='container'>
                <label for="uname"><b>Username</b></label>
                <input type="text" name="uName" required>

                <label for="psw"><b>Password</b></label>
                <input type="password" name="pass" required>
                <button type="submit">Login</button>
            </div>
            </form> 
        </div>
    </body>
</html>
