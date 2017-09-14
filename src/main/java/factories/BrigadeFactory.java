package factories;

import models.entity.brigades.Brigade;
import models.entity.brigades.BuildingBrigade;
import models.entity.brigades.CleaningBrigade;
import models.entity.brigades.RepairingBrigade;
import models.enums.WorkKind;
import models.enums.WorkScale;

/**
 * Created by Miha on 09.09.2017.
 */
public class BrigadeFactory implements BrigadeAbstractFactory{

    @Override
    public Brigade createBrigade(WorkKind workKind, WorkScale workScale) {
        Brigade brigade;

        switch (workKind) {
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
        switch (workScale) {
            case EASY: {
                brigade.setCount(1);
                break;
            }
            case MEDIUM: {
                brigade.setCount(2);
                break;
            }
            case HARD: {
                brigade.setCount(3);
                break;
            }
        }
        return brigade;
    }
}
