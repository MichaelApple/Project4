package model.dao.jdbc;

import model.dao.BrigadeDao;
import model.entities.Request;
import model.entities.brigade.Brigade;

import java.sql.*;
import java.util.List;
import java.util.Optional;

/**
 * Created by Miha on 21.09.2017.
 */
public class JdbcBrigadeDao implements BrigadeDao {

    private Connection connection;
    private static final String CREATE_REQUEST = "INSERT INTO requests(user_id, workkind, workscale, desiredtime) VALUES (?,?,?,?);";
    private static final String CREATE_BRIGADE = "INSERT INTO brigades(workercount, name, request_id) VALUES (?,?,?);";

    JdbcBrigadeDao(Connection sqlConnection) {
        super();
        this.connection = sqlConnection;
    }

    @Override
    public Optional<Brigade> find(int id) {
        return null;
    }

    @Override
    public List<Brigade> findAll() {
        return null;
    }

    @Override
    public void create(Brigade brigade) {

    }

    @Override
    public void update(Brigade brigade) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public int createRequest(Request request) {
//        try (PreparedStatement ps = connection.prepareStatement(CREATE_REQUEST, Statement.RETURN_GENERATED_KEYS)) {
//            ps.setInt(1, request.getUserId());
//            ps.setString(2, request.getWorkKind().toString());
//            ps.setString(3, request.getWorkScale().toString());
//            ps.setString(4, request.getDesiredDateTime().toString());
//            int id = ps.executeUpdate();
//
//            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
//                if (generatedKeys.next()) {
//                    request.setId(generatedKeys.getInt(1));
//                }
//                else {
//                    throw new SQLException("Creating user failed, no ID obtained.");
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return  request.getId();
    }
}
