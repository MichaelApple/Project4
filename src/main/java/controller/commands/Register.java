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
import java.io.IOException;
import java.util.Map;

/**
 * Command that that register new user if all input data were correct
 *
 * @author Miha
 */
public class Register implements Action {

    private static final Logger logger = Logger.getLogger(Register.class);

    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_PASSWORD = "password";

    private UserService userService = UserService.getInstance();

    /**
     * Method validate all user input, creates new user and
     * writes it to database, creates new session for this user
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return page to show user depends on User Role
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pageToGo;
        String name = request.getParameter(PARAM_NAME);
        String email = request.getParameter(PARAM_EMAIL);
        String password = request.getParameter(PARAM_PASSWORD);

        User user;
        boolean status;

        if (!RegExpressions.checkData(request) || name == null || email == null || password == null) {
            logger.info("User inputs wrong data");
            return  "/index.jsp";
        }
        user = new User.Builder()
                .setUserName(name)
                .setEmail(email)
                .setPassword(password)
                .setRole(User.Role.USER)
                .build();
        status = userService.register(user);

        if (status) {
            request.getSession().setAttribute("user", user);
            Cookie userName = new Cookie("user", email);
            response.addCookie(userName);
            request.setAttribute("user", user);
            Map<UserRequest, Brigade> userWorkPlan = userService.showUserWorkPlan((User) request.getSession().getAttribute("user"), 0);
            request.setAttribute("userWorkPlan", userWorkPlan);

            pageToGo = (user.getRole().toString().equals("ADMIN")) ? "/WEB-INF/views/admin.jsp" : "/WEB-INF/views/personal.jsp";

            logger.info("User " + user.getUserName() + " is registered");
        } else {
            pageToGo = "/index.jsp";
            request.setAttribute("userExist", "error");
            logger.error("Error occurred");
        }
        return pageToGo;
    }
}
