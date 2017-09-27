package model.dao.jdbc;

import model.dao.WorkPlanDao;
import model.entities.UserRequest;
import model.entities.WorkPlan;
import model.entities.brigade.Brigade;

import java.sql.*;
import java.util.List;
import java.util.Optional;

/**
 * Created by Miha on 22.09.2017.
 */
public class JdbcWorkPlanDao implements WorkPlanDao {

    private Connection connection;

    private static final String CREATE_WORKPLAN = "INSERT INTO workplan (request_id, brigade_id, desireddate) VALUES (?,?,?);";
    private static final String CREATE_REQUEST = "INSERT INTO requests(user_id, workkind, workscale, desiredtime) VALUES (?,?,?,?);";
    private static final String CREATE_BRIGADE = "INSERT INTO brigades(workercount, name, request_id) VALUES (?,?,?);";

    JdbcWorkPlanDao(Connection connection) {
        super();
        this.connection = connection;
    }
    @Override
    public Optional<WorkPlan> find(int id) {
        return null;
    }

    @Override
    public List<WorkPlan> findAll() {

        return null;
    }

    @Override
    public void create(WorkPlan workPlan) {
        try (PreparedStatement ps = connection.prepareStatement(CREATE_WORKPLAN)){
            ps.setInt(1, workPlan.getRequestId());
            ps.setInt(2, workPlan.getBrigadeId());
            ps.setString(3, workPlan.getDesiredDate().toString());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(WorkPlan workPlan) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public int createRequest(UserRequest userRequest) {
        try (PreparedStatement ps = connection.prepareStatement(CREATE_REQUEST, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, userRequest.getUserId());
            ps.setString(2, userRequest.getWorkKind().toString());
            ps.setString(3, userRequest.getWorkScale().toString());
            ps.setString(4, userRequest.getDesiredDateTime().toString());
            int id = ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    userRequest.setId(generatedKeys.getInt(1));
                }
                else throw new SQLException("Creating user failed, no ID obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  userRequest.getId();
    }

    @Override
    public int createBrigade(Brigade brigade) {
        try (PreparedStatement ps = connection.prepareStatement(CREATE_BRIGADE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, brigade.getWorkerCount());
            ps.setString(2, brigade.getName());
            ps.setInt(3, brigade.getRequestId());
            int id = ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    brigade.setId(generatedKeys.getInt(1));
                } else throw new SQLException("Creating user failed, no ID obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brigade.getId();
    }


}
