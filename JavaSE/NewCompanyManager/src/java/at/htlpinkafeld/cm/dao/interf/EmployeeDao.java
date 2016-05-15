/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.dao.interf;

import at.htlpinkafeld.cm.dao.interf.Dao;
import at.htlpinkafeld.cm.pojo.Employee;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public interface EmployeeDao extends Dao<Employee> {

    public List<Employee> findByDepartment(int deptId);
}
