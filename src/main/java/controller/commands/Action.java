package controller.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Miha on 15.09.2017.
 */
public interface Action {
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
