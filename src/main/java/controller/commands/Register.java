package controller.commands;

import model.entities.User;
import model.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Miha on 15.09.2017.
 */
public class Register implements Action {

    private static final Logger logger = Logger.getLogger(Register.class);

    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_PASSWORD = "password";

    private UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pageToGo;
        String name = request.getParameter(PARAM_NAME);
        String email = request.getParameter(PARAM_EMAIL);
        String password = request.getParameter(PARAM_PASSWORD);
        User user = null;

        int status;
        if (name != null && email != null && password != null) {
            user = new User.Builder()
                    .setUserName(name)
                    .setEmail(email)
                    .setPassword(password)
                    .setRole(User.Role.USER)
                    .build();
            status = userService.register(user);

        } else status = 0;
        if (status == 1) {
            request.getSession().setAttribute("user", user);
            Cookie userName = new Cookie("user", email);
            response.addCookie(userName);
            request.setAttribute("user", user);
            pageToGo = "/WEB-INF/views/personal.jsp";

            logger.info("User " + user.getUserName() + " is registered");
        } else {
            pageToGo = "/index.jsp";
            request.setAttribute("userExist", "error");
            logger.error("Error occurred");
        }
        return pageToGo;
    }
}
