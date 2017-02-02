/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.service;

import at.htlpinkafeld.database_manager.dao.database.DAOFactory;
import at.htlpinkafeld.database_manager.dao.database.EmpDAO;
import at.htlpinkafeld.database_manager.pojo.Employee;
import at.htlpinkafeld.database_manager.pojo.StatementEntityWrapper;
import at.htlpinkafeld.database_manager.pojo.StatementEntityWrapper.StatementType;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public class EmpServiceCacheImpl implements EmpService {

    private final EmpDAO empDAO;
    private final List<StatementEntityWrapper<Employee>> commands;
    private List<Employee> employeeCache;

    public EmpServiceCacheImpl() {
        empDAO = DAOFactory.getDAOFactory().getEmpDAO();
        commands = new LinkedList<>();
    }

    @Override
    public List<Employee> getEmployees() {
        if (employeeCache == null) {
            employeeCache = empDAO.getList();
        }
        return new LinkedList<>(employeeCache);
    }

    @Override
    public void insertEmployee(Employee e) {
        employeeCache.add(e);
        commands.add(new StatementEntityWrapper<>(StatementType.INSERT, e));
    }

    @Override
    public void updateEmployee(Employee e) {
        employeeCache.remove(e);
        employeeCache.add(e);
        commands.add(new StatementEntityWrapper<>(StatementType.UPDATE, e));
    }

    @Override
    public void deleteEmployee(Employee e) {
        employeeCache.remove(e);
        commands.add(new StatementEntityWrapper<>(StatementType.DELETE, e));
    }

    @Override
    public void commit() {
        empDAO.executeBatchUpdate(commands);
        empDAO.commit();
        employeeCache = empDAO.getList();
        commands.clear();
    }

    @Override
    public void rollback() {
        employeeCache = empDAO.getList();
        commands.clear();
    }

}
