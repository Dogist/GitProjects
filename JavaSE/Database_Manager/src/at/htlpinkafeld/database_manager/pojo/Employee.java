/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.pojo;

import java.time.LocalDate;

/**
 *
 * @author Martin Six
 */
public class Employee {

    private int empno;
    private String ename;
    private String job;
    private Employee mgr;
    private Double sal;
    private Double comm;
    private LocalDate hiredate;
    private Department dept;

    public Employee(int empno, String ename, String job, Employee mgr, LocalDate hiredate, Double sal, Double comm, Department dept) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.sal = sal;
        this.comm = comm;
        this.hiredate = hiredate;
        this.dept = dept;
    }

    public Employee(String ename, String job, Employee mgr, Double sal, Double comm, LocalDate hiredate, Department dept) {
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.sal = sal;
        this.comm = comm;
        this.hiredate = hiredate;
        this.dept = dept;
    }

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Employee getMgr() {
        return mgr;
    }

    public void setMgr(Employee mgr) {
        this.mgr = mgr;
    }

    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }

    public Double getComm() {
        return comm;
    }

    public void setComm(Double comm) {
        this.comm = comm;
    }

    public LocalDate getHiredate() {
        return hiredate;
    }

    public void setHiredate(LocalDate hiredate) {
        this.hiredate = hiredate;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return empno + "  " + ename;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.empno;
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
        if (this.empno != other.empno) {
            return false;
        }
        return true;
    }

}
