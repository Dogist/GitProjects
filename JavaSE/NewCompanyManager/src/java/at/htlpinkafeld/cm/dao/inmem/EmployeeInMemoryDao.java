/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.dao.inmem;

import at.htlpinkafeld.cm.dao.interf.EmployeeDao;
import at.htlpinkafeld.cm.pojo.Employee;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public class EmployeeInMemoryDao extends BaseInMemoryDao<Employee> implements EmployeeDao {

    protected EmployeeInMemoryDao() {
    }

    @Override
    protected Employee clone(Employee t) {
        return new Employee(t);
    }

    @Override
    public List<Employee> findByDepartment(int deptId) {
        List<Employee> empL = list();
        List<Employee> retL = new ArrayList<>();
        for (Employee e : empL) {
            if (e.getDepartment().getId() == deptId) {
                retL.add(clone(e));
            }
        }
        return retL;
    }

}
