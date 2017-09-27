package controller.commands;

import model.entities.UserRequest;
import model.entities.User;
import model.entities.brigade.Brigade;
import model.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Miha on 20.09.2017.
 */
public class Personal implements Action {

    private static final Logger logger = Logger.getLogger(Login.class);
    private UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageToGo;
        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null) {

            Map<UserRequest, Brigade> userWorkPlan = userService.showUserWorkPlan((User) session.getAttribute("user"), 6, 2);
            request.setAttribute("userWorkPlan", userWorkPlan);

            pageToGo = "/WEB-INF/views/personal.jsp";
            logger.info("User " + session.getAttribute("user").toString() + " entered personal cabinet");

        } else {
            pageToGo = "/WEB-INF/views/error.jsp";
            logger.error("User " + session.getAttribute("user").toString() + " failed to enter personal cabinet");
        }
        return pageToGo;
    }
}
