<% if (session.getAttribute("userRole") != "admin") {
                response.sendRedirect("index.jsp");
            } 
        %>
<a href="admin.jsp" class ='button'>Back</a><br>    
<p1>Create Customer</p1><br><br>
        <form method="POST" action="createCustomer.do">
              Enter full name : <input type="text" name="customerName" ><br> 
              Enter address : <input type="text" name="customerAddress" ><br>
              Enter username : <input type="text" name="customerUsername" ><br>
              Enter password: <input type="text" name="customerPassword" ><br>
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
   