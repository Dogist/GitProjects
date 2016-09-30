package at.htlpinkafeld.projectmanager.dao;

import java.util.List;

/**
 * Created by User on 07.03.2016.
 */
public interface BaseDAO<T> {

    public void insert(T entity);

    public List<T> getEntityList();

    public void save(T entity);

    public void delete(Long id);

}
