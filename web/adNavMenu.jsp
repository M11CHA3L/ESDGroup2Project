<%-- 
    Document   : adminNavMenu
    Created on : 02-Dec-2018, 12:34:01
    Author     : michaelcraddock
--%>
<link rel="stylesheet" type="text/css" href="style.css">
<div>
    <h1>AlphaCab</h1>
</div>
<div>
    <form method="POST" action="AdminServlet.do">
        <table>
            <tr>
                <td>
                    <input type="submit" name="adminOption" value ="View Drivers"><br>
                </td>
                <td>
                    <input type="submit" name="adminOption" value ="View Customers"><br>
                </td>
                <td>
                    <input type="submit" name="adminOption" value ="View New Demands"><br>
                </td>
                <td>
                    <input type="submit" name="adminOption" value ="View Turnover"><br>
                </td>
                <td>
                    <input type="submit" name="adminOption" value ="View Driver Bookings"><br>
                </td>
                <td>
                    <input type="submit" name="adminOption" value ="Create New Customer"><br>
                </td>
                <td>
                    <input type="submit" name="adminOption" value ="Create New Driver"><br>
                </td>
            </tr>
        </table>
    </form>
</div>

