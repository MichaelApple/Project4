package controller.commands;

import controller.regex.RegExpressions;
import model.entities.User;
import model.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by Miha on 19.09.2017.
 */
public class ChangeEmail implements Action {

    private static final Logger logger = Logger.getLogger(Login.class);

    private static final String PARAM_EMAIL = "newEmail";
    private UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pageToGo;
        String email = request.getParameter(PARAM_EMAIL);

        if (!RegExpressions.checkData(request) && email == null)
            return "/index.jsp";

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Optional<User> optionalUser = userService.changeEmail(user, email);

        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            session.setAttribute("user", user);
            request.setAttribute("user", user);
            pageToGo = "/WEB-INF/views/personal.jsp";

            logger.info("User " + user.getUserName() + " changed his email to " + user.getEmail());
        } else {
            request.setAttribute("error", "Email or password did not match");
            pageToGo = "/WEB-INF/views/personal.jsp";
            logger.error("Errors occurred with User " + user.getUserName() + ": ");
        }

        return pageToGo;
    }
}
