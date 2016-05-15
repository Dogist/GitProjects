/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.gui;

import at.htlpinkafeld.cm.pojo.Department;
import at.htlpinkafeld.cm.pojo.Employee;
import at.htlpinkafeld.cm.service.DepartmentService;
import at.htlpinkafeld.cm.service.EmployeeService;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public class EmployeeOverviewBean {
    
    private final EmployeeService es;
    private final DepartmentService ds;
    
    private final List<Department> deptList;
    private Department curDept;
    private Employee curEmp;
    
    public EmployeeOverviewBean() {
        es = EmployeeService.getInstance();
        ds = DepartmentService.getInstance();
        deptList = ds.listDepts();
    }
    
    public List<Department> getDeptList() {
        return deptList;
    }
    
    public Department getCurDept() {
        return curDept;
    }
    
    public Employee getCurEmp() {
        return curEmp;
    }
    
    public void setCurEmp(Employee curEmp) {
        this.curEmp = curEmp;
    }
    
    public void setCurDept(Department curDept) {
        this.curDept = curDept;
    }
    
    public List<Employee> getEmpList() {
        return curDept.getEmployees();
    }
    
    public Object editEmpAction(Employee e) {
        curEmp = new Employee(e);
        return null;
    }
    
    public Object deleteEmpAction(Employee e) {
        curDept.getEmployees().remove(e);
        es.deleteEmp(e);
        return null;
    }
    
    public Object saveCurEmpAction() {
        if (curEmp.getId() == -1) {
            es.createEmp(curEmp);
            curDept.getEmployees().add(curEmp);
        } else {
            es.updateEmp(curEmp);
            curDept.getEmployees().remove(curEmp);
            curDept.getEmployees().add(curEmp);
        }
        this.deptList.remove(this.curDept);
        this.deptList.add(curDept);
        return null;
    }
    
    public Object newCurEmpAction() {
        curDept = ds.createEmptyDep();
        return null;
    }
    
}
