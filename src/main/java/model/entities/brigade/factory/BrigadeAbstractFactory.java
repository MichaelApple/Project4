package model.entities.brigade.factory;

import model.entities.UserRequest;
import model.entities.brigade.Brigade;

/**
 * Created by Miha on 09.09.2017.
 */
public interface BrigadeAbstractFactory {
    public Brigade createBrigade(UserRequest userRequest);
}
