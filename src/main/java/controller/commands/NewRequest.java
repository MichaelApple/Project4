package controller.commands;

import controller.regex.RegExpressions;
import model.entities.UserRequest;
import model.entities.User;
import model.entities.WorkPlan;
import model.enums.WorkKind;
import model.enums.WorkScale;
import model.services.DispatcherRequestService;
import model.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Command that provide main logic of application:
 * validate user input in "New Request From" and
 * after it creates new UserRequest, build Brigade, based on request, and
 * add request to WorkPlan
 *
 * Created by Miha on 20.09.2017.
 * @author Miha
 */
public class NewRequest implements Action {

    private static final Logger logger = Logger.getLogger(Login.class);

    private static final String PARAM_WORK_KIND = "workKind";
    private static final String PARAM_WORK_SCALE = "workScale";
    private static final String PARAM_DESIRED_DATE = "desiredDateTime";

    private DispatcherRequestService dispatcherRequestService = DispatcherRequestService.getInstance();
    private UserService userService = UserService.getInstance();

    /**
     * Method validate user input and build UserRequest,
     * than build proper brigade based on request
     * and add all information in one transaction to database
     * and set request attribute "userRequest"
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return page to show user
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pageToGo;
        String workKind = request.getParameter(PARAM_WORK_KIND);
        String workScale = request.getParameter(PARAM_WORK_SCALE);
        LocalDate desiredDateTime;

        User user = (User) request.getSession().getAttribute("user");

        if (request.getParameter(PARAM_DESIRED_DATE).equals("") || workKind == null || workScale == null || !RegExpressions.checkData(request)) {
            request.setAttribute("wrongInput", "Wrong Input Data");
            logger.info("User " + user.getUserName() + " wrong input data");
            return "/index.jsp";
        }
        desiredDateTime = LocalDate.parse(request.getParameter(PARAM_DESIRED_DATE),
                DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        UserRequest userRequest;
        Optional<WorkPlan> workPlan;

        userRequest = new UserRequest.RequestBuilder()
                .setUserId(user.getId())
                .setWorkKind(WorkKind.valueOf(workKind.toUpperCase()))
                .setWorkScale(WorkScale.valueOf(workScale.toUpperCase()))
                .setDesiredDateTime(desiredDateTime)
                .build();

        workPlan = dispatcherRequestService.addToWorkPlan(userRequest);

        if (workPlan.isPresent()) {
            request.setAttribute("userRequest", userRequest);
            logger.info("User " + user.getUserName() + " request has been added to workPlan");
            pageToGo = "/WEB-INF/views/result.jsp";
        } else {
            logger.info("Error occurred");
            pageToGo = "/index.jsp";
        }
        return pageToGo;
    }
}
