package dao;



import models.entity.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by Miha on 05.09.2017.
 */
public class UserDao {
    public static int register(User user) {
        int status = 0;
        if (getUserByEmail(user.getEmail()) != null) return 0;

        PreparedStatement ps = null;
        try (Connection connection = ConnectionProvider.getConnection()){

            ps = connection.prepareStatement("INSERT INTO users(username, email, password, auth_lvl) VALUES (?,?,?,?)");
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, getHash(user.getPassword()));
            ps.setInt(4, 1);
            status = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return status;
    }

    public static User getUserByEmail(String email) {
        User user = new User();
        PreparedStatement ps = null;
        try(Connection connection = ConnectionProvider.getConnection()) {
            ps = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setUserName(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
            } else return null;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return user;
    }

    public static void changeEmail(int id, String email) {
        PreparedStatement ps = null;
        try (Connection connection = ConnectionProvider.getConnection()) {

            ps = connection.prepareStatement("UPDATE users SET email = ? WHERE id = ?");
            ps.setString(1, email);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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

    public static boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified = false;
        if (getHash(password_plaintext).equals(stored_hash))
            password_verified = true;

        return password_verified;
    }

}
