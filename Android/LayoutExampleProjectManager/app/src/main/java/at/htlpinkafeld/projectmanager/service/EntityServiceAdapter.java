package at.htlpinkafeld.projectmanager.service;

import java.util.List;

/**
 * Created by tq on 16-01-09.
 */
public interface EntityServiceAdapter<E> {

    List<E> getList();

    E get(int id);

    void add(E e);

    void update(E e);

    void delete(E e);

}
