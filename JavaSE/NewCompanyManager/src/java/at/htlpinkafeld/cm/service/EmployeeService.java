/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.service;

import at.htlpinkafeld.cm.dao.jdbc.JDBCFactory;
import at.htlpinkafeld.cm.dao.interf.EmployeeDao;
import at.htlpinkafeld.cm.pojo.Employee;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public class EmployeeService {

    private static EmployeeService es;
    private final EmployeeDao eDao;

    public static EmployeeService getInstance() {
        if (es == null) {
            es = new EmployeeService();
        }
        return es;
    }

    private EmployeeService() {
        eDao = JDBCFactory.getDAOFactory().getEmployeeDAO();
    }

    public List<Employee> findEmpByDepartment(int deptId) {
        return eDao.findByDepartment(deptId);
    }

    public void createEmp(Employee t) {
        eDao.create(t);
    }

    public Employee readEmp(int id) {
        return eDao.read(id);
    }

    public void updateEmp(Employee t) {
        eDao.update(t);
    }

    public void deleteEmp(Employee t) {
        eDao.delete(t);
    }

    public List<Employee> listEmps() {
        return eDao.list();
    }
}
