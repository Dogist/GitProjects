/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.dao.inmem;

import at.htlpinkafeld.cm.pojo.Identifiable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin Six
 * @param <T>
 */
public abstract class BaseInMemoryDao<T extends Identifiable> {

    private int id = 1;
    private List<T> memList;

    public BaseInMemoryDao() {
        memList = new ArrayList<>();

    }

    private int getNextId() {
        return id++;
    }

    private T getFromId(int id) {
        for (T t : memList) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    public void create(T t) {
        if (t.getId() == -1) {
            t.setId(getNextId());
        }
        memList.add(clone(t));
    }

    public T read(int id) {
        return clone(getFromId(id));
    }

    public void update(T t) {
        memList.remove(t);
        memList.add(clone(t));
    }

    public void delete(T t) {
        memList.remove(t);
    }

    public List<T> list() {
        List<T> ret = new ArrayList<>();
        for (T t : memList) {
            ret.add(clone(t));
        }
        return ret;
    }

    protected abstract T clone(T t);

}
