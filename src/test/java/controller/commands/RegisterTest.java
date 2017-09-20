package controller.commands;


import model.entities.User;
import model.services.UserService;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

/**
 * Created by Miha on 19.09.2017.
 */
public class RegisterTest {

    private HttpServletRequest request;
    private HttpServletResponse response;

    @Test
    public void execute() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        UserService userService = mock(UserService.class);
        User user = new User.Builder().build();
        Register register = (Register) mockingDetails(Register.class);


        verify(userService, times(1)).register(user);
        verify(request, times(1)).setAttribute("user", user);
    }

}