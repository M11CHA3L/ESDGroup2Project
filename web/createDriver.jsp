<% if (session.getAttribute("userRole") != "admin") {
                response.sendRedirect("index.jsp");
            } 
        %>
<a href="admin.jsp" class ='button'>Back</a><br>    
<p1>Create Driver</p1><br><br>
        <form method="POST" action="createDriver.do">
              Enter full name : <input type="text" name="driverName" ><br> 
              Enter registration : <input type="text" name="driverRegistration" ><br>
              Enter username : <input type="text" name="driverUsername" ><br>
              Enter password: <input type="text" name="driverPassword" ><br>
              <input type="submit" value="submit">
        </form> 
<br>
<%
        if (request.getAttribute("errorMessage") != null) {
            out.print((String) request.getAttribute("errorMessage"));
        }else if(request.getAttribute("message") != null){
            out.print((String) request.getAttribute("message"));
        }
        
    %> 
<br>
   