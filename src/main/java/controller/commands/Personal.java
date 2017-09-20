package controller.commands;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Miha on 20.09.2017.
 */
public class Personal implements Action {
    private static final Logger logger = Logger.getLogger(Login.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null)
            logger.info("User " + session.getAttribute("user").toString() + " entered personal cabinet");
        return "/WEB-INF/views/personal.jsp";
    }
}
