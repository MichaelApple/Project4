package controllers;

import models.entity.User;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Miha on 07.09.2017.
 */
@WebServlet(name = "ChangeEmail", urlPatterns = "/ChangeEmail")
public class ChangeEmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("newEmail");
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        System.out.println(user.getId());
        UserDao.changeEmail(user.getId(), email);

        request.setAttribute("newEmail", email);
        request.getRequestDispatcher("personal.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
