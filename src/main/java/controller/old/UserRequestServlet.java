package controller.old;

import model.entities.Request;
import model.enums.WorkKind;
import model.enums.WorkScale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Miha on 09.09.2017.
 */
@WebServlet(name = "UserRequestServlet", urlPatterns = "/NewRequest")
public class UserRequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String workKind = request.getParameter("workKind").toUpperCase();
        String workScale = request.getParameter("workScale").toUpperCase();
        String dateTime = request.getParameter("desiredDateTime");
        LocalDateTime desiredDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));

//
//        long time = desiredDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
//        long now = System.currentTimeMillis();
//
//        request.setAttribute("time", time);
//        request.setAttribute("now", now);

        Request userRequest = new Request.RequestBuilder(WorkKind.valueOf(workKind), WorkScale.valueOf(workScale), desiredDateTime).build();

        request.setAttribute("userRequest", userRequest);
        request.getRequestDispatcher("request.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
