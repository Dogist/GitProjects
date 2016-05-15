/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rs_personresource.dao;

import java.util.List;

/**
 *
 * @author Martin Six
 * @param <T>
 */
public interface Dao<T> {
    
    public void create(T t);

    public T read(int id);

    public void update(T t);

    public void delete(T t);

    public List<T> list();
}
