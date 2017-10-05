package controller.commands;

import model.entities.UserRequest;
import model.entities.User;
import model.entities.brigade.Brigade;
import model.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Command that opens personal panel
 * for users with USER role
 *
 * Created by Miha on 20.09.2017.
 * @author Miha
 */
public class Personal implements Action {

    private static final Logger logger = Logger.getLogger(Login.class);
    private UserService userService = UserService.getInstance();

    /**
     * If user exists than he/she will be dispatched to
     * page "/WEB-INF/views/personal.jsp" and if in request is
     * offset parameter the right rows will be get from database
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return page to show user
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageToGo;
        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null) {
            int offset = 0;
            offset = getOffset(request, offset);

            Map<UserRequest, Brigade> userWorkPlan = userService.showUserWorkPlan((User) session.getAttribute("user"), offset);
            request.setAttribute("userWorkPlan", userWorkPlan);

            pageToGo = "/WEB-INF/views/personal.jsp";
            logger.info("User " + session.getAttribute("user").toString() + " entered personal cabinet");

        } else {
            pageToGo = "/WEB-INF/views/error.jsp";
            logger.error("User " + session.getAttribute("user").toString() + " failed to enter personal cabinet");
        }
        return pageToGo;
    }

    /**
     * Method that calculate offset
     * from users request
     * @param request user request
     * @param offset - number of request in database from which
     *               we should get users requests
     * @return offset
     */
    private int getOffset(HttpServletRequest request, int offset) {
        if (request.getParameter("submit") != null) {
            offset = Integer.parseInt(request.getParameter("offset"));
            offset += (request.getParameter("submit").equals("next")) ? 1 : -1;
            request.setAttribute("offset", offset);
        }
        return offset;
    }
}
