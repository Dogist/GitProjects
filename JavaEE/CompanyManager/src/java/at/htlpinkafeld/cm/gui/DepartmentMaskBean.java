/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.gui;

import at.htlpinkafeld.cm.pojo.Department;
import at.htlpinkafeld.cm.pojo.Employee;
import at.htlpinkafeld.cm.service.CompanyFacadeBean;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Martin Six
 */
public class DepartmentMaskBean implements Serializable{

    private Integer deptno;
    private String loadRes;
    private List<Department> deptList;
    private List<Employee> selEmpList;

    private CompanyFacadeBean companyFacadeRef;

    public DepartmentMaskBean() {
    }
    
    @PostConstruct
    private void init(){
        deptList=companyFacadeRef.getDepartmentList();
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public String getLoadRes() {
        return loadRes;
    }

    public void setLoadRes(String loadRes) {
        this.loadRes = loadRes;

    }

    public List<Department> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<Department> deptList) {
        this.deptList = deptList;
    }

    public List<Employee> getSelEmpList() {
        return selEmpList;
    }

    public void setCompanyFacadeRef(CompanyFacadeBean companyFacadeRef) {
        this.companyFacadeRef = companyFacadeRef;
    }

    public Object loadAction() {
        Department d = companyFacadeRef.findDepartmentByID(deptno);
        if (d == null) {
            loadRes = "Department not found!";
        } else {
            loadRes = d.toString();
        }
        return null;
    }

    public Object loadEmpList() {
        this.selEmpList = companyFacadeRef.findEmployeesByDeptno(this.deptno);

        return null;
    }
}
