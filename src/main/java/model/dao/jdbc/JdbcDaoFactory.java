package model.dao.jdbc;

import model.dao.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Miha on 15.09.2017.
 */
public class JdbcDaoFactory extends DaoFactory {

    private static DataSource ds;

    public JdbcDaoFactory() {
        try {
            InitialContext ic = new InitialContext();
            ds = (DataSource) ic.lookup("jdbc/Project4");
        } catch (NamingException e) {
            e.printStackTrace();
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
        return new JdbcUserDao(sqlConnection);
    }

    @Override
    public BrigadeDao getRequestDao(DaoConnection daoConnection) {
        JdbcDaoConnection jdbcDaoConnection = (JdbcDaoConnection) daoConnection;
        Connection sqlConnection = jdbcDaoConnection.getConnection();
        return new JdbcBrigadeDao(sqlConnection);
    }

    @Override
    public WorkPlanDao getWorkPlanDao(DaoConnection daoConnection) {
        JdbcDaoConnection jdbcDaoConnection = (JdbcDaoConnection) daoConnection;
        Connection sqlConnection = jdbcDaoConnection.getConnection();
        return new JdbcWorkPlanDao(sqlConnection);
    }

}
