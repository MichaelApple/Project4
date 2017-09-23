package filters;

import controller.commands.Action;
import controller.commands.AdminPanel;
import controller.commands.Logout;
import model.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Miha on 12.09.2017.
 */
@WebFilter(filterName = "AuthorizationFilter", urlPatterns = "/pages/*")
public class AuthorizationFilter implements Filter {

    private final static Logger logger = Logger.getLogger(AuthorizationFilter.class);
    private String errorPage;

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, java.io.IOException {

        logger.info("User Authorization process starting");

        HttpSession session = ((HttpServletRequest)request).getSession(false);
        if (session.getAttribute("user") == null) {
            chain.doFilter(request,response);
        } else {
            User currentUser = (User)session.getAttribute("user");

            if (currentUser == null) {
                chain.doFilter(request, response);
            }
            else {
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
        }

    }

    public void init(FilterConfig filterConfig) throws javax.servlet.ServletException {
        errorPage = "/WEB-INF/views/restricted.jsp";
    }

    private void returnError(ServletRequest request, ServletResponse response,String errorString)
              throws ServletException, IOException {
                   request.setAttribute("error", errorString);
                   request.getRequestDispatcher(errorPage).forward(request,response);
      }

}
