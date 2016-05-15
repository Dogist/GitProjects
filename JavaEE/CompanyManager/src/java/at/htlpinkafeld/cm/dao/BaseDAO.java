/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.dao;

import java.util.List;

/**
 *
 * @author Martin Six
 */
public interface BaseDAO<T> {
    public abstract List<T> getEntityList();
    public abstract boolean insertEntity(T obj);
    public abstract boolean removeEntity(int i);
    public abstract boolean updateEntity(T obj);
}
