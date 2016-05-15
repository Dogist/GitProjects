/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.pojo;

import java.io.Serializable;

/**
 *
 * @author Martin Six
 */
public class Department implements Serializable{

    private Integer deptno;
    private String dname;
    private String loc;

    public Department(Integer deptno, String dname, String loc) {
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "Department{" + "deptno=" + deptno + ", dname=" + dname + ", loc=" + loc + '}';
    }
    
    
}
