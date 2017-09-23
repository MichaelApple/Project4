package model.services;

import model.dao.BrigadeDao;
import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.WorkPlanDao;
import model.entities.Request;
import model.entities.WorkPlan;
import model.entities.brigade.Brigade;
import model.entities.brigade.factory.BrigadeFactory;

/**
 * Created by Miha on 21.09.2017.
 */
public class DispatcherRequestService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final DispatcherRequestService INSTANCE = new DispatcherRequestService();
    }
    public static DispatcherRequestService getInstance() {
        return DispatcherRequestService.Holder.INSTANCE;
    }

    public Brigade createBrigade(Request request) {
//        BrigadeFactory brigadeFactory = new BrigadeFactory();
//        Brigade brigade = brigadeFactory.createBrigade(request);
//
//        try (DaoConnection daoConnection = daoFactory.getConnection()){
//            daoConnection.begin();
//            BrigadeDao brigadeDao = daoFactory.getRequestDao(daoConnection);
//            int requestId = brigadeDao.createRequest(request);
//
//            if (requestId != 0){
//                brigade.setRequestId(requestId);
//                brigadeDao.create(brigade);
//            }
//            daoConnection.commit();
//        }
        return null;
    }

    public WorkPlan addToWorkPlan(Request request) {

        WorkPlan workPlan = null;
        BrigadeFactory brigadeFactory = new BrigadeFactory();
        Brigade brigade = brigadeFactory.createBrigade(request);

        try (DaoConnection daoConnection = daoFactory.getConnection()){
            daoConnection.begin();

            WorkPlanDao workPlanDao = daoFactory.getWorkPlanDao(daoConnection);
            int requestId = workPlanDao.createRequest(request);


            if (requestId != 0){
                brigade.setRequestId(requestId);
                int brigadeId = workPlanDao.createBrigade(brigade);

                workPlan = new WorkPlan.Builder()
                        .setBrigadeId(brigadeId)
                        .setRequestId(brigade.getRequestId())
                        .setDesiredDate(request.getDesiredDateTime())
                        .build();
                workPlanDao.create(workPlan);
            }
            daoConnection.commit();
        }

        return workPlan;
    }


}
