package controllers;


import dao.UserDao;
import models.entity.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by Miha on 03.09.2017.
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/Login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (UserDao.getUserByEmail(email) == null) {
            request.setAttribute("error", "Email or password did not match");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            User user = UserDao.getUserByEmail(email);
            if (UserDao.checkPassword(password, user.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                Cookie userName = new Cookie("user", email);
                response.addCookie(userName);
                request.setAttribute("user", user);
                request.getRequestDispatcher("personal.jsp").forward(request, response);

            } else {
                request.setAttribute("error", "Email or password did not match");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("personal.jsp").forward(request, response);
    }
}
