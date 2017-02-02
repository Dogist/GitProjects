/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.service;

import at.htlpinkafeld.database_manager.dao.database.DAOFactory;
import at.htlpinkafeld.database_manager.dao.database.EmpDAO;
import at.htlpinkafeld.database_manager.pojo.Employee;
import java.util.List;

public class EmpServiceDelegationImpl implements EmpService {

    private final EmpDAO empDAO;

    public EmpServiceDelegationImpl() {
        empDAO = DAOFactory.getDAOFactory().getEmpDAO();
    }

    @Override
    public List<Employee> getEmployees() {
        return empDAO.getList();
    }

    @Override
    public void insertEmployee(Employee e) {
        empDAO.insert(e);
    }

    @Override
    public void updateEmployee(Employee e) {
        empDAO.update(e);
    }

    @Override
    public void deleteEmployee(Employee e) {
        empDAO.delete(e);
    }

    @Override
    public void commit() {
        empDAO.commit();
    }

    @Override
    public void rollback() {
        empDAO.rollback();
    }

}
