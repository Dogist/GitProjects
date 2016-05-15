/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.dao.simple.employee;

import at.htlpinkafeld.dao.simple.Department;
import at.htlpinkafeld.jdbcbase.Identifiable;

/**
 *
 * @author Martin Six
 */
public class Employee implements Identifiable {

    private int id;
    private String name;
    private String job;
    private double sal;
    private Department department;

    public Employee() {
        id = -1;
    }

    public Employee(String name, String job, double sal) {
        this();
        this.name = name;
        this.job = job;
        this.sal = sal;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name=" + name + ", job=" + job + ", sal=" + sal + ", department=" + department + '}';
    }

}
