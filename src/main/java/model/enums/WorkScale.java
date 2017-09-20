package model.enums;

/**
 * Created by Miha on 08.09.2017.
 */
public enum WorkScale {
    EASY, MEDIUM, HARD;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
