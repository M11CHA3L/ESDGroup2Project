<% if (session.getAttribute("userRole") != "driver") {
        response.sendRedirect("index.jsp");
    }
%>

Welcome driver<br><br>

<form method="post" action="driver.do">
    <input type="date" name="date">
    <input type="submit" name="Get Jobs">
</form>

