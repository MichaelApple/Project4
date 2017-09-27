package controller;

import controller.commands.Register;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Miha on 27.09.2017.
 */
public class FrontControllerTest {
    @Test
    public void serviceTest() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);



        when(request.getMethod().toUpperCase()).thenReturn("GET");
        when(request.getRequestURI()).thenReturn("/something");
        when(request.getRequestDispatcher("/WEB-INF/views/personal.jsp")).thenReturn(dispatcher);

        new FrontController().service(request, response);
        //String viewPage =

        //assertEquals("/WEB-INF/views/personal.jsp", viewPage);
    }

}