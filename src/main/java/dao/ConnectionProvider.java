package dao;

import beans.User;
import dao.pass.Password;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by Miha on 03.09.2017.
 */
public class ConnectionProvider {
    private static Connection connection;
    private static InitialContext ic;
    private static DataSource ds;
    private static Connection getConnection() {
        try {
            ic = new javax.naming.InitialContext();
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project4", "postgres", "root");
        } catch (ClassNotFoundException | SQLException | NamingException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static int register(User user) {
        int status = 0;
        if (getUserByEmail(user.getEmail()) != null) return 0;
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO users(username, email, password) VALUES (?,?,?)");
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, Password.hashPassword(user.getPassword()));

            status = ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static User getUserByEmail(String email) {
        User user = new User();

        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setUserName(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
            } else return null;
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

}
