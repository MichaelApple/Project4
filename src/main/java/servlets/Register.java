package servlets;

import beans.User;
import dao.ConnectionProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Miha on 03.09.2017.
 */
@WebServlet(name = "Register", urlPatterns = "/Register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        User user = new User();
        user.setUserName(name);
        user.setEmail(email);
        user.setPassword(password);

        int status = ConnectionProvider.register(user);

        if (status == 1) {
            request.setAttribute("user", user);
        } else {
            request.setAttribute("userExist", "Such user already exist");
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
