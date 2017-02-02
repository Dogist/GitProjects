/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.service;

import at.htlpinkafeld.database_manager.dao.database.DAOFactory;
import at.htlpinkafeld.database_manager.dao.database.DeptDAO;
import at.htlpinkafeld.database_manager.pojo.Department;
import at.htlpinkafeld.database_manager.pojo.StatementEntityWrapper;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public class DeptServiceCacheImpl implements DeptService {

    private final DeptDAO deptDAO;
    private final List<StatementEntityWrapper<Department>> commands;
    private List<Department> departmentCache;

    public DeptServiceCacheImpl() {
        deptDAO = DAOFactory.getDAOFactory().getDeptDAO();
        commands = new LinkedList<>();
    }

    @Override
    public List<Department> getDepartments() {
        if (departmentCache == null) {
            departmentCache = deptDAO.getList();
        }
        return new LinkedList<>(departmentCache);
    }

    @Override
    public void insertDepartment(Department d) {
        departmentCache.add(d);
        commands.add(new StatementEntityWrapper<>(StatementEntityWrapper.StatementType.INSERT, d));
    }

    @Override
    public void updateDepartment(Department d) {
        departmentCache.remove(d);
        departmentCache.add(d);
        commands.add(new StatementEntityWrapper<>(StatementEntityWrapper.StatementType.UPDATE, d));
    }

    @Override
    public void deleteDepartment(Department d) {
        departmentCache.remove(d);
        commands.add(new StatementEntityWrapper<>(StatementEntityWrapper.StatementType.DELETE, d));
    }

    @Override
    public void commit() {
        deptDAO.executeBatchUpdate(commands);
        deptDAO.commit();
        departmentCache = deptDAO.getList();
        commands.clear();
    }

    @Override
    public void rollback() {
        departmentCache = deptDAO.getList();
        commands.clear();
    }
}
