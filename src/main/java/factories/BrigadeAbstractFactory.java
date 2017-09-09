package factories;

import models.entity.brigades.Brigade;
import models.enums.WorkKind;
import models.enums.WorkScale;

/**
 * Created by Miha on 09.09.2017.
 */
public interface BrigadeAbstractFactory {
    public Brigade createBrigade(WorkKind workKind, WorkScale workScale);
}
