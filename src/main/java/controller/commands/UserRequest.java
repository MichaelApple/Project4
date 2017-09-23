package controller.commands;

import model.entities.Request;
import model.entities.User;
import model.entities.brigade.Brigade;
import model.enums.WorkKind;
import model.enums.WorkScale;
import model.services.DispatcherRequestService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Miha on 21.09.2017.
 */
public class UserRequest implements Action {

    private static final Logger logger = Logger.getLogger(Login.class);

    private static final String PARAM_WORKKIND = "workKind";
    private static final String PARAM_WORKSCALE = "workScale";
    private static final String PARAM_DESIRED_DATE = "desiredDateTime";

    private DispatcherRequestService dispatcherRequestService = DispatcherRequestService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pageToGo = "";
        String workKind = request.getParameter(PARAM_WORKKIND);
        String workScale = request.getParameter(PARAM_WORKSCALE);
        LocalDate desiredDateTime = LocalDate.parse(request.getParameter(PARAM_DESIRED_DATE),
                DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        Request userRequest;
        Brigade brigade;
        User user = (User) request.getSession().getAttribute("user");

        if (workKind != null && workScale != null && desiredDateTime != null) {
            userRequest = new Request.RequestBuilder()
                    .setUserId(user.getId())
                    .setWorkKind(WorkKind.valueOf(workKind.toUpperCase()))
                    .setWorkScale(WorkScale.valueOf(workScale.toUpperCase()))
                    .setDesiredDateTime(desiredDateTime)
                    .build();

            //brigade = dispatcherRequestService.createBrigade(userRequest);

            dispatcherRequestService.addToWorkPlan(userRequest);

            request.setAttribute("userRequest", userRequest);
            //request.setAttribute("brigade", brigade);

            pageToGo = "/WEB-INF/views/result.jsp";
        } else pageToGo = "/index.jsp";

        return pageToGo;
    }
}
