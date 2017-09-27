package controller.commands;

import controller.regex.RegExpressions;
import model.entities.UserRequest;
import model.entities.User;
import model.entities.brigade.Brigade;
import model.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Miha on 17.09.2017.
 */
public class Login implements Action {
    private static final Logger logger = Logger.getLogger(Login.class);

    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_PASSWORD = "password";

    private UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pageToGo = "";
        String email = request.getParameter(PARAM_EMAIL);
        String password = request.getParameter(PARAM_PASSWORD);

        if (!RegExpressions.checkData(request) && email == null && password == null)
            return "/index.jsp";

        Optional<User> optionalUser = userService.login(email, password);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            Cookie userName = new Cookie("user", email);
            response.addCookie(userName);
            Map<UserRequest, Brigade> userWorkPlan = userService.showUserWorkPlan((User) session.getAttribute("user"), 1, 1);
            request.setAttribute("userWorkPlan", userWorkPlan);
            request.setAttribute("user", user);
            pageToGo = "/WEB-INF/views/personal.jsp";

            logger.info("User " + user.getUserName() + " is logged in");
        } else {
            request.setAttribute("error", "Email or password did not match");
            pageToGo = "/index.jsp";
            logger.error("Email or password did not match");
        }


        return pageToGo;
    }
}
