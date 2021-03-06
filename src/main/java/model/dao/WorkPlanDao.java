package model.dao;

import model.entities.UserRequest;
import model.entities.WorkPlan;
import model.entities.brigade.Brigade;

/**
 * Created by Miha on 22.09.2017.
 */
public interface WorkPlanDao extends GenericDao<WorkPlan> {
    int createRequest(UserRequest userRequest);
    int createBrigade(Brigade brigade);

    void approve(int id);
}
