package filters;

import controller.commands.Action;
import controller.commands.Logout;
import model.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Miha on 12.09.2017.
 */
@WebFilter(filterName = "AuthorizationFilter", urlPatterns = "/*")
public class AuthorizationFilter implements Filter {

    private final static Logger logger = Logger.getLogger(AuthorizationFilter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, java.io.IOException {
        req.setCharacterEncoding("UTF-8");

        //logger.info("User Authorization process starting");

        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");

            Map<String , Action> accessPages = new HashMap<>();
            switch (user.getRole()) {
                case USER: break;
                case ADMIN: accessPages.put("GET:/logout", new Logout());
            }

            req.setAttribute("accessPages", accessPages);
        }

        chain.doFilter(req, resp);

    }

    public void init(javax.servlet.FilterConfig config) throws javax.servlet.ServletException {

    }

}
