package model.factories;

import model.entities.brigades.Brigade;
import model.enums.WorkKind;
import model.enums.WorkScale;

/**
 * Created by Miha on 09.09.2017.
 */
public interface BrigadeAbstractFactory {
    public Brigade createBrigade(WorkKind workKind, WorkScale workScale);
}
