/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.service;

import at.htlpinkafeld.database_manager.pojo.Employee;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public interface EmpService {

    public List<Employee> getEmployees();

    public void insertEmployee(Employee e);

    public void updateEmployee(Employee e);

    public void deleteEmployee(Employee e);

    public void commit();

    public void rollback();

}
