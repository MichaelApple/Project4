package model.services;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.dao.WorkPlanDao;
import model.entities.UserRequest;
import model.entities.WorkPlan;
import model.entities.brigade.Brigade;
import model.entities.brigade.factory.BrigadeFactory;

import java.util.Optional;

/**
 * Manages UserRequest creates brigade based on request and
 * write to database in one transaction request, brigade and workPlan records
 *
 * Created by Miha on 21.09.2017.
 * @author Miha
 */
public class DispatcherRequestService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final DispatcherRequestService INSTANCE = new DispatcherRequestService();
    }
    public static DispatcherRequestService getInstance() {
        return DispatcherRequestService.Holder.INSTANCE;
    }

    /**
     * in one transaction writes to database userRequest, brigade and workPlan
     * based on userRequest
     *
     * @param userRequest UserRequest
     * @return result of writing workPlan to database
     */
    public Optional<WorkPlan> addToWorkPlan(UserRequest userRequest) {

        WorkPlan workPlan = null;
        BrigadeFactory brigadeFactory = new BrigadeFactory();
        Brigade brigade = brigadeFactory.createBrigade(userRequest);

        try (DaoConnection daoConnection = daoFactory.getConnection()){
            daoConnection.begin();

            WorkPlanDao workPlanDao = daoFactory.getWorkPlanDao(daoConnection);
            int requestId = workPlanDao.createRequest(userRequest);

            if (requestId != 0){
                brigade.setRequestId(requestId);
                int brigadeId = workPlanDao.createBrigade(brigade);

                workPlan = new WorkPlan.Builder()
                        .setBrigadeId(brigadeId)
                        .setRequestId(brigade.getRequestId())
                        .setDesiredDate(userRequest.getDesiredDateTime())
                        .build();
                workPlanDao.create(workPlan);
            }
            daoConnection.commit();
        }
        return workPlan != null ? Optional.of(workPlan) : Optional.empty();
    }

    public void deleteRequest(int requestId) {
        try (DaoConnection daoConnection = daoFactory.getConnection()){
            WorkPlanDao dao = daoFactory.getWorkPlanDao(daoConnection);
            dao.delete(requestId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void approveRequest(int id) {
        try (DaoConnection daoConnection = daoFactory.getConnection()){
            WorkPlanDao dao = daoFactory.getWorkPlanDao(daoConnection);
            dao.approve(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
