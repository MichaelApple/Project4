package model.dao.jdbc;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.dao.old.ConnectionProvider;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Miha on 15.09.2017.
 */
public class JdbcDaoFactory extends DaoFactory {

    private static InitialContext ic;
    private static DataSource ds;

    public JdbcDaoFactory() {
        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("jdbc/Project4");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DaoConnection getConnection() {
        try {
            return new JdbcDaoConnection(ds.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public UserDao getUserDao(DaoConnection connection) {
        JdbcDaoConnection jdbcDaoConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcDaoConnection.getConnection();

        Connection myConnection = ConnectionProvider.getConnection();

        return new JdbcUserDao(sqlConnection);
    }
}
