package controller.commands;

import model.services.DispatcherRequestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Miha on 13.10.2017.
 */
public class ApproveRequest implements Action {
    private static final String PARAM_REQ_ID = "requestId";
    private DispatcherRequestService dispatcherRequestService = DispatcherRequestService.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestId = request.getParameter(PARAM_REQ_ID);
        String pageTogo = "/WEB-INF/views/admin.jsp";

        dispatcherRequestService.approveRequest(Integer.parseInt(requestId));


        return pageTogo;
    }
}
