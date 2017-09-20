package model.dao;

import java.util.List;
import java.util.Optional;

/**
 * Created by Miha on 15.09.2017.
 */
public interface GenericDao<E> {
    Optional<E> find(int id);
    List<E> findAll();
    void create(E e);
    void update(E e);
    void delete(int id);
}
