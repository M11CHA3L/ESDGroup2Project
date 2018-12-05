<%-- 
    Document   : adminNavMenu
    Created on : 02-Dec-2018, 12:34:01
    Author     : michaelcraddock
--%>
<link rel="stylesheet" type="text/css" href="style.css">

<div>
    <h1>AlphaCab</h1>
</div>
<div class="container">
    <form method="POST" id='adnavform' action="AdminServlet.do">
        <div style='width:205px;' class="one">
            <button class='btnSize' type="submit" name="adminOption" value="Home">Home</button>
        </div>
        <div style='width:205px;' class="two">
            <button type="submit" class='btnSize'  name="adminOption" value="View Drivers">View Drivers</button>
        </div>

        <div style='width:205px;' class="two">
            <button type="submit" class='btnSize'  name="adminOption" value="View Customers">View Customers</button>
        </div>

        <div style='width:205px;' class="two">
            <button class='btnSize' type="submit" name="adminOption" value="View New Demands">View New Demands</button>
        </div>

        <div style='width:205px;' class="two">
            <button class='btnSize' type="submit" name="adminOption" value="View Daily Turnover">View Daily Turnover</button>
        </div>
        <div style='width:205px;' class="two">
            <button class='btnSize' type="submit" name="adminOption" value="View Driver Bookings" >View Driver Bookings</button>
        </div>

        <div style='width:205px;' class="two">
            <button class='btnSize' type="submit" name="adminOption" value="Create New Customer">Create New Customer</button>
        </div>

        <div style='width:205px;' class="two">
            <button class='btnSize' type="submit" name="adminOption" value="Create New Driver">"Create New Driver"</button>
        </div>


    </form>
    
</div>
<br><br><br><br>

