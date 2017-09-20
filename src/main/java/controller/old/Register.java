package controller.old;

import model.entities.User;
import model.dao.old.UserDao;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by Miha on 03.09.2017.
 */
@WebServlet(name = "Register", urlPatterns = "/Register")
public class Register extends HttpServlet {

    private static final Logger logger = Logger.getLogger(Register.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        User user = new User.Builder()
                .setUserName(name)
                .setEmail(email)
                .setPassword(password)
                .build();

        int status = UserDao.register(user);

        if (status == 1) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            Cookie userName = new Cookie("user", email);
            response.addCookie(userName);
            request.setAttribute("user", user);

            logger.info("User " + user.getUserName() + " is registered");

        } else {
            request.setAttribute("userExist", "Such user already exist");
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
