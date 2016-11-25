/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.dao.database;

import java.util.List;

/**
 *
 * @author Martin Six
 * @param <T>
 */
public interface BaseDAO<T> {

    public abstract void insert(T obj);

    public abstract void update(T obj);

    public abstract void delete(T obj);

    public abstract List<T> getList();

    public abstract void executeBatchUpdate(List<String> commands);
}
