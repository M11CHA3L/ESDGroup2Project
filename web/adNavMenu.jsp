<%-- 
    Document   : adminNavMenu
    Created on : 02-Dec-2018, 12:34:01
    Author     : michaelcraddock
--%>
<link rel="stylesheet" type="text/css" href="style.css">
<style>
    input[type=submit] {
    min-width: 120px;
    
    }
    table{
        margin: auto;
    }
</style>
<div>
    <h1>AlphaCab</h1>
</div>
<div>
    <form method="POST" id='adnavform' action="AdminServlet.do">


                    <input class='adnavbtn' type="submit" name="adminOption" value ="View Drivers">

                    <input class='adnavbtn' type="submit" name="adminOption" value ="View Customers">

                    <input class='adnavbtn' type="submit" name="adminOption" value ="View New Demands">

                    <input class='adnavbtn' type="submit" name="adminOption" value ="View Turnover">
                    <input class='adnavbtn' type="submit" name="adminOption" value ="View Driver Bookings">
       
                    <input class='adnavbtn' type="submit" name="adminOption" value ="Create New Customer">
     
                    <input class='adnavbtn' type="submit" name="adminOption" value ="Create New Driver">
 
    </form>
</div>

