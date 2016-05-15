/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.service;

import at.htlpinkafeld.cm.dao.DepartmentDAO;
import at.htlpinkafeld.cm.dao.EmployeeDAO;
import at.htlpinkafeld.cm.pojo.Department;
import at.htlpinkafeld.cm.pojo.Employee;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public class CompanyFacadeBean implements Serializable{

    private final DepartmentDAO depDAO;
    private final EmployeeDAO empDAO;

    public CompanyFacadeBean() {
        depDAO = new DepartmentDAO();
        empDAO = new EmployeeDAO();
    }

    public List<Department> getDepartmentList() {
        return depDAO.getEntityList();
    }

    public boolean insertDepartment(Department obj) {
        return depDAO.insertEntity(obj);
    }

    public boolean removeDepartment(int i) {
        return depDAO.removeEntity(i);
    }

    public boolean updateDepartment(Department obj) {
        return depDAO.updateEntity(obj);
    }

    public Department findDepartmentByID(int id) {
        return depDAO.findByID(id);
    }

    public List<Employee> getEmployeeList() {
        return empDAO.getEntityList();
    }

    public List<Employee> findEmployeesByDeptno(int deptno) {
        return empDAO.findByDeptno(deptno);
    }

    public boolean insertEmployee(Employee obj) {
        return empDAO.insertEntity(obj);
    }

    public boolean removeEmployee(int i) {
        return empDAO.removeEntity(i);
    }

    public boolean updateEmployee(Employee obj) {
        return empDAO.updateEntity(obj);
    }

}
