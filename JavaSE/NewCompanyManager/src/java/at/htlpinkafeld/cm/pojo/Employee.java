/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.pojo;

import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author Martin Six
 */
public class Employee implements Identifiable {

    private int id;
    private String name;
    private String job;
    private Employee mgr;
    private Timestamp hiredate;
    private double sal;
    private double comm;
    private Department department;

    public Employee(Employee emp) {
        this.id = emp.id;
        this.name = emp.name;
        this.job = emp.job;
        this.mgr = emp.mgr;
        this.hiredate = emp.hiredate;
        this.sal = emp.sal;
        this.comm = emp.comm;
        this.department = emp.department;
    }

    public Employee() {
        id = -1;
    }

    public Employee(String name, String job, Employee mgr, Timestamp hiredate, double sal, double comm, Department department) {
        this();
        this.name = name;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.sal = sal;
        this.comm = comm;
        this.department = department;
    }

    public Employee getMgr() {
        return mgr;
    }

    public void setMgr(Employee mgr) {
        this.mgr = mgr;
    }

    public Timestamp getHiredate() {
        return hiredate;
    }

    public void setHiredate(Timestamp hiredate) {
        this.hiredate = hiredate;
    }

    public double getComm() {
        return comm;
    }

    public void setComm(double comm) {
        this.comm = comm;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.id;
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + Objects.hashCode(this.job);
        hash = 83 * hash + Objects.hashCode(this.hiredate);
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.sal) ^ (Double.doubleToLongBits(this.sal) >>> 32));
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.comm) ^ (Double.doubleToLongBits(this.comm) >>> 32));
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
        final Employee other = (Employee) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
