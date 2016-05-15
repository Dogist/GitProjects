/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.dao.simple;

import at.htlpinkafeld.dao.simple.employee.Employee;
import at.htlpinkafeld.jdbcbase.Identifiable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public class Department implements Identifiable {

    private int id;
    private String name;
    private String loc;
    private List<Employee> employees;

     public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
            
        //Set the department reference for all employees
        for(Employee emp: this.employees)
            emp.setDepartment(this);
    }


    public Department() {
        this.id = -1;
        this.employees = new ArrayList<>();
    }

    public Department(String name, String loc) {
        this();
        this.name = name;
        this.loc = loc;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "Department{" + "id=" + id + ", name=" + name + ", loc=" + loc + '}';
    }

}
