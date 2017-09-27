package controller.commands;

import model.entities.UserRequest;
import model.entities.User;
import model.entities.brigade.Brigade;
import model.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Miha on 26.09.2017.
 */
public class Pagination extends HttpServlet {
    private UserService userService = UserService.getInstance();
    private static final String SQL_SUBLIST = "SELECT id, username, job, place FROM"
            + " contact ORDER BY id LIMIT %d OFFSET %d";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Map<UserRequest, Brigade> userWorkPlan = userService.showUserWorkPlan((User) request.getSession().getAttribute("user"), 1 ,1);
        request.setAttribute("userWorkPlan", userWorkPlan);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
