package se.kth.iv1201projekt.model.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * A filter that checks if the user is already logged in and restricts access
 * to the login page.
 * @author Samy
 */
@WebFilter("/jsf/login.xhtml")
public class AlreadyLoggedInFilter implements Filter{

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }
    
    /**
     * Checks session variables for info if user is logged in. If logged in
     * and accessing login.xhtml redirected to logout.xhtml
     * @param req
     * @param resp
     * @param chain
     * @throws IOException
     * @throws ServletException 
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession(false);
        if(session == null  || session.getAttribute("user") == null ){
            chain.doFilter(req, resp);
        }
        else{
            response.sendRedirect(request.getContextPath()+"/jsf/logout.xhtml");
        }
    }

    @Override
    public void destroy() {
        
    }
    

    
}
