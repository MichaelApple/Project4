package model.dao;

/**
 * Created by Miha on 15.09.2017.
 */
public interface DaoConnection extends AutoCloseable {
    void begin();
    void commit();
    void rollback();
    void close();
}
