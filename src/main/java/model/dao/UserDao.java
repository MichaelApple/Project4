package model.dao;

import model.entities.User;

import java.util.Optional;

/**
 * Created by Miha on 15.09.2017.
 */
public interface UserDao extends GenericDao<User>{

    Optional<User> getUserByEmail(String email);
    void changeEmail(int id, String email);
    boolean checkPassword(String passwordInput, String storedHash);
}
