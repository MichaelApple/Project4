package controller.old;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Miha on 03.09.2017.
 */
@WebServlet(name = "Logout", urlPatterns = "/Logout")
public class LogoutServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LogoutServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        logger.info("User " + session.getAttribute("user").toString() + " is logged out");
        session.invalidate();
        request.setAttribute("logout", "Logged out");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
