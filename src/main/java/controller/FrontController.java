package controller;

import controller.commands.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Miha on 15.09.2017.
 */
@WebServlet("/pages/*")
public class FrontController extends HttpServlet {

    private Map<String , Action> actions = new HashMap<>();

    @Override
    public void init(){
        actions.put("POST:/register",  new Register());
        actions.put("POST:/login", new Login());
        actions.put("GET:/logout", new Logout());
        actions.put("POST:/changeEmail", new ChangeEmail());
        actions.put("GET:/personal", new Personal());
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod().toUpperCase();
        String path = req.getRequestURI();

        path = path.replaceAll(".*Project4_war_exploded/pages", "").replaceAll("/pages", "").replaceAll("\\d+", "");

        String actionKey = method + ":" + path;
        Action action = actions.getOrDefault(actionKey, (re, res) -> "/index.jsp");

        String viewPage = action.execute(req, resp);
        dispatch(req, resp, viewPage);
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, String page) throws  javax.servlet.ServletException, java.io.IOException {
        if (page.equals("/index.jsp")) request.getRequestDispatcher(page).forward(request, response);

        request.getRequestDispatcher(page).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
