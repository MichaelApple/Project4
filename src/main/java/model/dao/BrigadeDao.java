package model.dao;

import model.entities.UserRequest;
import model.entities.brigade.Brigade;

/**
 * Created by Miha on 21.09.2017.
 */
public interface BrigadeDao extends GenericDao<Brigade>{

    int createRequest(UserRequest userRequest);
}
