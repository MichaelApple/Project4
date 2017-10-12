package model.services;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.UserRequest;
import model.entities.User;
import model.entities.brigade.Brigade;

import java.util.Map;
import java.util.Optional;

/**
 * UserService that provides methods to manipulate with user data in database
 *
 * Created by Miha on 15.09.2017.
 * @author Miha
 */
public class UserService {

    private DaoFactory daoFactory ;

    UserService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        static final UserService INSTANCE = new UserService(DaoFactory.getInstance());
    }

    public static UserService getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Method verify that in database there is no such user yet
     * and creates new db record
     * @param user - new user to register in db
     * @return true if user successfully registered in db
     */
    public boolean register(User user) {
        try (DaoConnection daoConnection = daoFactory.getConnection()){
            daoConnection.begin();
            UserDao dao = daoFactory.getUserDao(daoConnection);
            if (dao.getUserByEmail(user.getEmail()).isPresent()) {
                return false;
            }
            dao.create(user);
            daoConnection.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Method checks that user exists in database and verify his email and password
     * @param email user email
     * @param password user password
     * @return Optional<User>
     */
    public Optional<User> login(String email, String password) {
        try (DaoConnection daoConnection = daoFactory.getConnection()){

            UserDao userDao = daoFactory.getUserDao(daoConnection);
            if (userDao.getUserByEmail(email).isPresent()) {
                User dbUser = userDao.getUserByEmail(email).get();
                if (userDao.checkPassword(password, dbUser.getPassword()))
                    return Optional.of(dbUser);
            }
            return Optional.empty();
        }
    }

    /**
     * Method changes user email to new one that user input
     * @param user user who wants to change his email
     * @param newEmail user new email
     * @return user with changed email
     */
    public Optional<User> changeEmail(User user, String newEmail) {
        try (DaoConnection daoConnection = daoFactory.getConnection()){

            UserDao userDao;
            try {
                daoConnection.begin();
                userDao = daoFactory.getUserDao(daoConnection);
                userDao.changeEmail(user.getId(), newEmail);
                daoConnection.commit();
            } catch (Exception e) {
                daoConnection.rollback();
                daoConnection.close();
                return Optional.empty();
            }
            return userDao.getUserByEmail(newEmail);
        }
    }

    /**
     * Method shows workPlan for user
     * @param user user
     * @param offset which request and brigade to show
     * @return userWorkPlan
     */
    public Map<UserRequest, Brigade> showUserWorkPlan(User user, int offset) {
        Map<UserRequest, Brigade> userWorkPlan;
        try (DaoConnection daoConnection = daoFactory.getConnection()){
            daoConnection.begin();
            UserDao userDao = daoFactory.getUserDao(daoConnection);
            userWorkPlan = userDao.getUserWorkPlan(user.getId(), offset);
            daoConnection.commit();
        }
        return userWorkPlan;
    }
}
