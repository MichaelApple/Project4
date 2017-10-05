package controller.commands;

import static org.junit.Assert.*;

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

    @Test
    public void execute() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);

        User user = new User.Builder().build();
        Register register = null;
        try {
            register = new Register();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = register.execute(request, null);


        assertEquals(null, url);
    }

}