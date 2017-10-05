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
 * Command that changes user email to new one
 *
 * Created by Miha on 20.09.2017.
 * @author Miha
 */
public class ChangeEmail implements Action {

    private static final Logger logger = Logger.getLogger(Login.class);

    private static final String PARAM_EMAIL = "newEmail";
    private UserService userService = UserService.getInstance();

    /**
     * Method do validation of user input and
     * change old email to new one
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return personal cabinet page
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pageToGo;
        String email = request.getParameter(PARAM_EMAIL);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (!RegExpressions.checkData(request) || email == null) {
            logger.info("User " + user.getUserName() + " wrong input data");
            return "/index.jsp";
        }

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
