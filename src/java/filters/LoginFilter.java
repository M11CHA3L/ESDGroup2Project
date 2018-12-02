/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author RyanCoole
 */
@WebFilter(filterName = "LoginFilter", servletNames = {"LoginServlet"})
public class LoginFilter implements Filter {
    
       	private ServletContext context;
	
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("LoginFilter initialized");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

                
            String username = request.getParameter("uName");
            String password = request.getParameter("pass");

            String errorMessage;
            // Check username was entered
            if (username.equals("")) {
                errorMessage = "No username Entered";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }

            // Check if password was entered
            if (password.equals("")) {
                errorMessage = "No password entered";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            
            chain.doFilter(request, response);
				
	}

	

	public void destroy() {
		//close any resources here
	}
    
    
}
