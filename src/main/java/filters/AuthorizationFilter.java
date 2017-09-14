package filters;

import dao.UserDao;
import models.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Miha on 12.09.2017.
 */
@WebFilter(filterName = "AuthorizationFilter", urlPatterns = "/Login")
public class AuthorizationFilter implements Filter {

    private final static Logger logger = Logger.getLogger(AuthorizationFilter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, java.io.IOException {
        req.setCharacterEncoding("UTF-8");

        logger.info("User Authorization process starting");

        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
//        req.setAttribute("auth_lvl", user.getAuth_lvl());
        chain.doFilter(req, resp);

    }

    public void init(javax.servlet.FilterConfig config) throws javax.servlet.ServletException {

    }

}
