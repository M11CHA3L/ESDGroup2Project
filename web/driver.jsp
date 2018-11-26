<% if (session.getAttribute("userRole") != "driver") {
        response.sendRedirect("index.jsp");
    }
%>

Welcome <%out.print(session.getAttribute("userName"));%><br><br>

<%
    if (request.getAttribute("updated") != null) {
        out.println(request.getAttribute("updated")); %>
<br><br>
<%
    }
%>

<%
    if (request.getAttribute("jobs") != null) {
        out.println(request.getAttribute("jobs"));
    }
%>

<%
    if (request.getAttribute("jobs") == null) {
%>
<form method="post" action="driver.do">
    <input type="submit" value="Get New Jobs" name="getJobs">
</form>
<%
    }
%>