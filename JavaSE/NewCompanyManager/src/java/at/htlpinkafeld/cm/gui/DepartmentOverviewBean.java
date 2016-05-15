/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.gui;

import at.htlpinkafeld.cm.dao.interf.DepartmentDao;
import at.htlpinkafeld.cm.pojo.Department;
import at.htlpinkafeld.cm.service.DepartmentService;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public class DepartmentOverviewBean {

    private DepartmentService ds;
    private List<Department> deptList;
    private Department curDept;

    public DepartmentOverviewBean() {
        ds = DepartmentService.getInstance();
        deptList = ds.listDepts();
        curDept = ds.createEmptyDep();
    }

    public List<Department> getDeptList() {
        return deptList;
    }

    public Department getCurDept() {
        return curDept;
    }

    public void setCurDept(Department curDept) {
        this.curDept = curDept;
    }

    public Object deleteDeptAction(Department d) {
        ds.deleteDep(d);
        this.deptList.remove(d);
        return null;
    }

    public Object editDeptAction(Department d) {
        this.curDept = ds.createEmptyDep();
        return null;
    }

    public Object saveCurDeptAction() {
        if (curDept.getId() == -1) {
            ds.createDep(curDept);
        } else {
            ds.updateDep(curDept);
        }
        this.deptList.remove(this.curDept);
        this.deptList.add(curDept);
        return null;
    }

    public Object newCurDeptAction() {
        curDept = ds.createEmptyDep();
        return null;
    }
}
