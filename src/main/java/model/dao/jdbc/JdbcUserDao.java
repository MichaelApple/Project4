package model.dao.jdbc;

import model.dao.UserDao;
import model.entities.UserRequest;
import model.entities.User;
import model.entities.brigade.Brigade;
import model.entities.brigade.factory.BrigadeFactory;
import model.enums.WorkKind;
import model.enums.WorkScale;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Miha on 15.09.2017.
 */
public class JdbcUserDao implements UserDao {

    private Connection connection;

    private static final String REGISTER_NEW_USER = "INSERT INTO users(username, email, password, role) VALUES (?,?,?,?);";
    private static final String LOGIN_USER = "SELECT * FROM users WHERE email = ?;";
    private static final String CHANGE_EMAIL = "UPDATE users SET email = ? WHERE id = ?;";
    private static final String FIND_ALL_USER_REQUESTS_BRIGADES = "SELECT * FROM brigades\n" +
            "  INNER JOIN requests r ON brigades.request_id = r.id\n" +
            "  WHERE user_id = ? ORDER BY brigades.request_id DESC LIMIT 2 OFFSET %d;";

    JdbcUserDao(Connection sqlConnection) {
        super();
        this.connection = sqlConnection;
    }

    @Override
    public Optional<User> find(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void create(User user) {

        try (PreparedStatement ps = connection.prepareStatement(REGISTER_NEW_USER)){

            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, getHash(user.getPassword()));
            ps.setString(4, user.getRole().toString());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        Optional<User> result = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement(LOGIN_USER)) {

            ps.setString(1, email.toLowerCase());
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                User user = new User.Builder()
                        .setId(resultSet.getInt(1))
                        .setUserName(resultSet.getString(2))
                        .setEmail(resultSet.getString(3))
                        .setPassword(resultSet.getString(4))
                        .setRole(User.Role.valueOf(resultSet.getString(5)))
                        .build();
                result = Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void changeEmail(int id, String email) {
        try (PreparedStatement ps = connection.prepareStatement(CHANGE_EMAIL)){
            ps.setString(1, email.toLowerCase());
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<UserRequest, Brigade> getUserWorkPlan(int id, int offset) {
        Map<UserRequest, Brigade> userWorkPlan = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(String.format(FIND_ALL_USER_REQUESTS_BRIGADES, offset))){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserRequest userRequest = new UserRequest.RequestBuilder()
                        .setId(rs.getInt("id"))
                        .setUserId(id)
                        .setWorkKind(WorkKind.valueOf(rs.getString("workkind").toUpperCase()))
                        .setWorkScale(WorkScale.valueOf(rs.getString("workscale").toUpperCase()))
                        .setDesiredDateTime(LocalDate.parse(rs.getString("desiredtime")))
                        .build();
                userWorkPlan.put(userRequest, new BrigadeFactory().createBrigade(userRequest));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userWorkPlan;
    }


    private static String getHash(String password) {
        String result = null;
        try {
            MessageDigest ms = MessageDigest.getInstance("SHA-256");
            result = new String(ms.digest(password.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean checkPassword(String passwordInput, String storedHash) {
        boolean password_verified = false;
        if (getHash(passwordInput).equals(storedHash))
            password_verified = true;

        return password_verified;
    }
}
