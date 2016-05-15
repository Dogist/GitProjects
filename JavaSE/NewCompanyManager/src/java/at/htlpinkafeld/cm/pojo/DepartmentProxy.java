/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.pojo;

import at.htlpinkafeld.cm.dao.factory.AbstractDAOFactory;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public class DepartmentProxy implements Department {
    
    private Department d;
    
    public DepartmentProxy(Department d) {
        this.d = d;
    }
    
    @Override
    public List<Employee> getEmployees() {
        if (d.getEmployees() == null) {
            d.setEmployees(AbstractDAOFactory.getDAOFactory().getEmployeeDAO().findByDepartment(d.getId()));
        }
        return d.getEmployees();
    }
    
    @Override
    public void setEmployees(List<Employee> employees) {
        d.setEmployees(employees);
    }
    
    @Override
    public int getId() {
        return d.getId();
    }
    
    @Override
    public void setId(int id) {
        d.setId(id);
    }
    
    @Override
    public String getName() {
        return d.getName();
    }
    
    @Override
    public void setName(String name) {
        d.setName(name);
    }
    
    @Override
    public String getLoc() {
        return d.getLoc();
    }
    
    @Override
    public void setLoc(String loc) {
        d.setLoc(loc);
    }
    
}
