package controller.commands;

import model.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Miha on 19.09.2017.
 */
public class Logout implements Action {

    private static final Logger logger = Logger.getLogger(Login.class);

    private UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null)
            logger.info("User " + session.getAttribute("user").toString() + " is logged out");
        session.invalidate();

        request.setAttribute("logout", "Logged out");
        return "/index.jsp";
    }
}
