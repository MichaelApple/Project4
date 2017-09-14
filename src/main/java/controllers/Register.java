package controllers;

import models.entity.User;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Miha on 03.09.2017.
 */
@WebServlet(name = "Register", urlPatterns = "/Register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        User user = new User();
        user.setUserName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setAuth_lvl(1);

        int status = UserDao.register(user);

        if (status == 1) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            Cookie userName = new Cookie("user", email);
            response.addCookie(userName);
            request.setAttribute("user", user);
        } else {
            request.setAttribute("userExist", "Such user already exist");
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
