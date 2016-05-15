/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.service;

import at.htlpinkafeld.cm.dao.jdbc.JDBCFactory;
import at.htlpinkafeld.cm.dao.interf.DepartmentDao;
import at.htlpinkafeld.cm.pojo.Department;
import at.htlpinkafeld.cm.pojo.DepartmentImpl;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public class DepartmentService {

    private static DepartmentService ds;
    private final DepartmentDao dDao;

    public static DepartmentService getInstance() {
        if (ds == null) {
            ds = new DepartmentService();
        }
        return ds;
    }

    private DepartmentService() {
        dDao = JDBCFactory.getDAOFactory().getDepartmentDAO();
    }

    public void createDep(Department t) {
        dDao.create(t);
    }

    public Department readDep(int id) {
        return dDao.read(id);
    }

    public void updateDep(Department t) {
        dDao.update(t);
    }

    public void deleteDep(Department t) {
        dDao.delete(t);
    }

    public List<Department> listDepts() {
        return dDao.list();
    }
    
    public Department createEmptyDep(){
        return new DepartmentImpl();
    }

}
