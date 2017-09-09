package models.enums;

/**
 * Created by Miha on 08.09.2017.
 */
public enum  WorkKind {
    CLEANING, REPAIRING, BUILDING;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
