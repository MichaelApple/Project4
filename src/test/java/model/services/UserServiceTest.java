package model.services;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Miha on 21.09.2017.
 */
public class UserServiceTest {


    @Test
    public void register() throws Exception {
        DaoFactory daoFactory = mock(DaoFactory.class);
        UserDao userDao = mock(UserDao.class);
        DaoConnection daoConnection = mock(DaoConnection.class);
        UserService userService = new UserService(daoFactory);
        User user = new User.Builder()
                .setUserName("name")
                .setEmail("email")
                .setPassword("password")
                .setRole(User.Role.USER)
                .build();
        boolean fake = true;

        when(daoFactory.getConnection()).thenReturn(daoConnection);
        when(daoFactory.getUserDao(anyObject())).thenReturn(userDao);
        when(userDao.getUserByEmail(user.getEmail()).isPresent()).thenReturn(fake);

        boolean register = userService.register(user);

        assertEquals(fake, register);
    }



    @Test
    public void login() throws Exception {

    }

    @Test
    public void changeEmail() throws Exception {

    }

}