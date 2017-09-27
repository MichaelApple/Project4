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
 * Created by Miha on 15.09.2017.
 */
public class UserService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final UserService INSTANCE = new UserService();
    }

    public static UserService getInstance() {
        return Holder.INSTANCE;
    }

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

    public Map<UserRequest, Brigade> showUserWorkPlan(User user, int limit, int offset) {
        Map<UserRequest, Brigade> userWorkPlan;
        try (DaoConnection daoConnection = daoFactory.getConnection()){
            daoConnection.begin();
            UserDao userDao = daoFactory.getUserDao(daoConnection);
            userWorkPlan = userDao.getUserWorkPlan(user.getId(), limit, offset);
            daoConnection.commit();
        }
        return userWorkPlan;
    }
}
