/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.dao.simple;

import at.htlpinkafeld.dao.simple.employee.Employee;
import at.htlpinkafeld.dao.simple.employee.EmployeeDao;
import at.htlpinkafeld.dao.simple.employee.EmployeeJdbcDao;
import at.htlpinkafeld.jdbcbase.ConnectionManager;
import at.htlpinkafeld.jdbcbase.DBProperties;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Six
 */
public class MainTest {

    public static void main(String[] args) {
        DBProperties.getInstance().setLocal(true);

        EmployeeDao empDao = new EmployeeJdbcDao();
        DepartmentDao deptDao = new DepartmentJdbcDao(empDao);  //this smells

        //usually you just need to read list of departments 
        //the employees are created within their departments  
        List<Department> departments = deptDao.list();

        System.out.println("All Departments (" + departments.size() + ")");
        for (Department dept : departments) {
            System.out.println(dept);
            for (Employee emp : dept.getEmployees()) {
                System.out.println("    " + emp);
            }
        }

        //there are two options if you need a list of all Employees too
        //1) easy but hairy! you will have two instances for every employee -->
        //   can cause REDUNDANCY PROBLEMS/INCONSISTENCIES 
        //   the department reference of the employees is not initialized
        List<Employee> employees = empDao.list();

        //2) more effort but just one instance for each employee
        employees = new ArrayList<>();
        for (Department dept : departments) {
            for (Employee emp : dept.getEmployees()) {
                employees.add(emp);
            }
        }

        System.out.println("");
        System.out.println("All Employees (" + employees.size() + ")");
        for (Employee emp : employees) {
            System.out.println("    " + emp);
        }

        //Close the connection
        try {
            ConnectionManager.getInstance().closeFinally();
        } catch (SQLException ex) {
            Logger.getLogger(CheckOneToMany.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
