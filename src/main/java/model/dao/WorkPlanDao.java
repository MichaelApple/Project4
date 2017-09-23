package model.dao;

import model.entities.Request;
import model.entities.WorkPlan;
import model.entities.brigade.Brigade;

/**
 * Created by Miha on 22.09.2017.
 */
public interface WorkPlanDao extends GenericDao<WorkPlan> {
    int createRequest(Request request);
    int createBrigade(Brigade brigade);

}
