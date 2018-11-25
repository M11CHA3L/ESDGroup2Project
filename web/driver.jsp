<% if (session.getAttribute("userRole") != "driver") {
        response.sendRedirect("index.jsp");
    }
%>

Welcome driver<br><br>

<% 
    if(request.getAttribute("jobs") != null){
    out.println(request.getAttribute("jobs"));
    }
%>

<form method="post" action="driver.do">
    <input type="submit" value="Get New Jobs" name="Get Jobs">
</form>