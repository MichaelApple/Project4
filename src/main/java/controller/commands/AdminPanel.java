package controller.commands;

import model.entities.User;
import model.entities.UserRequest;
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
public class AdminPanel implements Action {

    private static final Logger logger = Logger.getLogger(Login.class);
    private UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Map<UserRequest, Brigade> userWorkPlan = userService.showUserWorkPlan((User) session.getAttribute("user"), 3, 1);
        request.setAttribute("userWorkPlan", userWorkPlan);

        logger.info("User " + session.getAttribute("user").toString() + " entered admin panel");
        return "/WEB-INF/views/admin.jsp";
    }
}
