/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.service;

import at.htlpinkafeld.database_manager.dao.database.DAOFactory;
import at.htlpinkafeld.database_manager.dao.database.DeptDAO;
import at.htlpinkafeld.database_manager.pojo.Department;
import java.util.List;

public class DeptServiceDelegationImpl implements DeptService {

    private final DeptDAO deptDAO;

    public DeptServiceDelegationImpl() {
        deptDAO = DAOFactory.getDAOFactory().getDeptDAO();
    }

    @Override
    public List<Department> getDepartments() {
        return deptDAO.getList();
    }

    @Override
    public void insertDepartment(Department d) {
        deptDAO.insert(d);
    }

    @Override
    public void updateDepartment(Department d) {
        deptDAO.update(d);
    }

    @Override
    public void deleteDepartment(Department d) {
        deptDAO.delete(d);
    }

    @Override
    public void commit() {
        deptDAO.commit();
    }

    @Override
    public void rollback() {
        deptDAO.rollback();
    }

}
