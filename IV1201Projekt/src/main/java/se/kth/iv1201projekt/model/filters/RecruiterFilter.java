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
 * Filter for recruiter. Checks that only a recruiter may enter the place a job
 * site.
 *
 * @author Samy
 */
@WebFilter("/jsf/placejob.xhtml")
public class RecruiterFilter implements Filter {

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }
    
    /**
     * This filter stops the user from accessing placejob.xhtml if they're not
     * a recruiter by checking the session variables for the role of the user.
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
        String attribute = (String) session.getAttribute("role");
        if (attribute != null && attribute.equals("recruit")) {
            chain.doFilter(req, resp);
        } else {
            response.sendRedirect(request.getContextPath() + "/jsf/index.xhtml");
        }
    }

    @Override
    public void destroy() {

    }

}
