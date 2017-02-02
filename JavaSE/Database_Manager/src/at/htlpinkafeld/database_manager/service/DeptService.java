/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.service;

import at.htlpinkafeld.database_manager.pojo.Department;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public interface DeptService {

    public List<Department> getDepartments();

    public void insertDepartment(Department d);

    public void updateDepartment(Department d);

    public void deleteDepartment(Department d);

    public void commit();

    public void rollback();
}
