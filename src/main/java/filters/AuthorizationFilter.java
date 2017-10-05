package filters;

import model.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Filter that checks before every command
 * if user has rights to see requested page
 *
 * Created by Miha on 12.09.2017.
 * @author Miha
 */
@WebFilter(filterName = "AuthorizationFilter", urlPatterns = "/pages/*")
public class AuthorizationFilter implements Filter {

    private final static Logger logger = Logger.getLogger(AuthorizationFilter.class);
    private String errorPage;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, java.io.IOException {

        logger.info("User Authorization process starting");

        HttpSession session = ((HttpServletRequest)request).getSession(false);
        User currentUser = (User)session.getAttribute("user");

        if (currentUser == null) {
            chain.doFilter(request, response);
        }
        else {
            isAuthorized(request, response, chain, currentUser);
        }
    }

    /**
     * Method checks user role and verify it with list of allowed pages in properties file
     *
     * @param request ServletRequest
     * @param response ServletResponse
     * @param chain FilterChain
     * @param currentUser user that wants to see page
     * @throws IOException
     * @throws ServletException
     */
    private void isAuthorized(ServletRequest request, ServletResponse response, FilterChain chain, User currentUser) throws IOException, ServletException {
        String URI = ((HttpServletRequest)request).getRequestURI();
        AuthorizationManager authMgr = new AuthorizationManagerDefaultImpl();

        boolean authorized = authMgr.isUserAuthorized(currentUser, URI);
        if (authorized) {
            chain.doFilter(request,response);
        }
        else {
            returnError(request,response,"User is not authorized to access this area!");
        }
    }

    public void init(FilterConfig filterConfig) throws javax.servlet.ServletException {
        errorPage = "/WEB-INF/views/restricted.jsp";
    }

    /**
     * If user has no rights to see page than filter will dispatch user to errorPage
     *
     * @param request ServletRequest
     * @param response ServletResponse
     * @param errorString error Page to show in case not allowed access
     * @throws ServletException
     * @throws IOException
     */
    private void returnError(ServletRequest request, ServletResponse response,String errorString)
              throws ServletException, IOException {
                   request.setAttribute("error", errorString);
                   request.getRequestDispatcher(errorPage).forward(request,response);
      }

    public void destroy() {
    }

}
