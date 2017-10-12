package controller;

import controller.commands.*;

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

    /**
     * Initiates servlet and fills Map actions
     */
    @Override
    public void init(){
        actions.put("POST:/register",  new Register());
        actions.put("POST:/login", new Login());
        actions.put("GET:/logout", new Logout());
        actions.put("POST:/changeEmail", new ChangeEmail());
        actions.put("GET:/personal", new Personal());
        actions.put("POST:/personal", new Personal());
        actions.put("GET:/admin", new AdminPanel());
        actions.put("POST:/admin", new AdminPanel());
        actions.put("POST:/newRequest", new NewRequest());
    }


    /**
     * This is the main method that takes request method and URI,
     * composes from it actionPath and than search in actions Map
     * for matches to perform wright action(command)
     * and dispatch wright page to show
     * @param request - browser request
     * @param response - server response to user
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String method = request.getMethod().toUpperCase();
        String path = request.getRequestURI();

        path = path.replaceAll(".*Project4_war_exploded/pages", "").replaceAll("\\d+", "");

        String actionKey = method + ":" + path;
        Action action = actions.getOrDefault(actionKey, (re, res) -> "/index.jsp");

        String viewPage = action.execute(request, response);
        dispatch(request, response, viewPage);
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, String page) throws  javax.servlet.ServletException, java.io.IOException {
        request.getRequestDispatcher(page).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
