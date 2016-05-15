/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Martin Six
 */
public class DepartmentImpl implements Department {

    private int id;
    private String name;
    private String loc;
    private List<Employee> employees;

    @Override
    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;

        //Set the department reference for all employees
        for (Employee emp : this.employees) {
            emp.setDepartment(this);
        }
    }

    public DepartmentImpl(Department d) {
        this.id = d.getId();
        this.name = d.getName();
        this.loc = d.getLoc();
        this.employees = d.getEmployees();
    }

    public DepartmentImpl() {
        this.id = -1;
        this.employees = new ArrayList<>();
    }

    public DepartmentImpl(String name, String loc) {
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getLoc() {
        return loc;
    }

    @Override
    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "Department{" + "id=" + id + ", name=" + name + ", loc=" + loc + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.loc);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Department other = (Department) obj;
        if (this.id != other.getId()) {
            return false;
        }
        return true;
    }

}
