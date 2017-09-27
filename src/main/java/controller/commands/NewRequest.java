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
 * Created by Miha on 21.09.2017.
 */
public class NewRequest implements Action {

    private static final Logger logger = Logger.getLogger(Login.class);

    private static final String PARAM_WORK_KIND = "workKind";
    private static final String PARAM_WORK_SCALE = "workScale";
    private static final String PARAM_DESIRED_DATE = "desiredDateTime";

    private DispatcherRequestService dispatcherRequestService = DispatcherRequestService.getInstance();
    private UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pageToGo;
        String workKind = request.getParameter(PARAM_WORK_KIND);
        String workScale = request.getParameter(PARAM_WORK_SCALE);
        LocalDate desiredDateTime = LocalDate.parse(request.getParameter(PARAM_DESIRED_DATE),
                DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        UserRequest userRequest;
        Optional<WorkPlan> workPlan;

        User user = (User) request.getSession().getAttribute("user");

        if (workKind == null && workScale == null && desiredDateTime == null)
            return "/index.jsp";

        userRequest = new UserRequest.RequestBuilder()
                .setUserId(user.getId())
                .setWorkKind(WorkKind.valueOf(workKind.toUpperCase()))
                .setWorkScale(WorkScale.valueOf(workScale.toUpperCase()))
                .setDesiredDateTime(desiredDateTime)
                .build();

        workPlan = dispatcherRequestService.addToWorkPlan(userRequest);

        if (workPlan.isPresent()) {
            request.setAttribute("userRequest", userRequest);

            pageToGo = "/WEB-INF/views/result.jsp";
        } else pageToGo = "/index.jsp";

        return pageToGo;
    }
}
