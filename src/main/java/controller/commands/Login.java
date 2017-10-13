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
 * Command that validate user input and than
 * login users, set session and cookies
 *
 * Created by Miha on 20.09.2017.
 * @author Miha
 */
public class Login implements Action {

    private static final Logger logger = Logger.getLogger(Login.class);

    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_PASSWORD = "password";

    private UserService userService = UserService.getInstance();

    /**
     * Method checks if user is in database,
     * if true set session and cookies for this user
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return page to show user
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pageToGo;
        String email = request.getParameter(PARAM_EMAIL);
        String password = request.getParameter(PARAM_PASSWORD);

        if (!RegExpressions.checkData(request) || email == null || password == null) {
            logger.info("Wrong input data");
            return "/index.jsp";
        }

        Optional<User> optionalUser = userService.login(email, password);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            Cookie userName = new Cookie("user", email);
            response.addCookie(userName);

            int offset = 0;

            Map<UserRequest, Brigade> userWorkPlan = user.getRole().toString().equals("ADMIN") ? userService.showAllWorkPlan((User) session.getAttribute("user"), offset) : userService.showUserWorkPlan((User) session.getAttribute("user"), 0);
            request.setAttribute("userWorkPlan", userWorkPlan);
            request.setAttribute("user", user);

            pageToGo = (user.getRole().toString().equals("ADMIN")) ? "/WEB-INF/views/admin.jsp" : "/WEB-INF/views/personal.jsp";

            logger.info("User " + user.getUserName() + " is logged in");
        } else {
            request.setAttribute("error", "Email or password did not match");
            pageToGo = "/index.jsp";
            logger.error("Email or password did not match");
        }
        return pageToGo;
    }
}
