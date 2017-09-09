package dao;


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

    public static Connection getConnection() {
        try {
            ic = new javax.naming.InitialContext();
            ds = (DataSource) ic.lookup("jdbc/Project4");
            connection = ds.getConnection();

        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
        return connection;
    }


}
