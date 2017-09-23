package model.entities.brigade.factory;

import model.entities.Request;
import model.entities.brigade.Brigade;
import model.entities.brigade.BuildingBrigade;
import model.entities.brigade.CleaningBrigade;
import model.entities.brigade.RepairingBrigade;

/**
 * Created by Miha on 09.09.2017.
 */
public class BrigadeFactory implements BrigadeAbstractFactory{

    @Override
    public Brigade createBrigade(Request request) {
        Brigade brigade;

        switch (request.getWorkKind()) {
            case BUILDING: {
                brigade = new BuildingBrigade();
                break;
            }
            case CLEANING: {
                brigade = new CleaningBrigade();
                break;
            }
            case REPAIRING: {
                brigade = new RepairingBrigade();
                break;
            }
            default: return null;
        }
        switch (request.getWorkScale()) {
            case EASY: {
                brigade.setWorkerCount(1);
                break;
            }
            case MEDIUM: {
                brigade.setWorkerCount(2);
                break;
            }
            case HARD: {
                brigade.setWorkerCount(3);
                break;
            }
        }
        brigade.setRequestId(request.getId());
        brigade.setName(brigade.getClass().getSimpleName());
        return brigade;
    }
}
