/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public interface Department extends Identifiable{

    public List<Employee> getEmployees();

    public void setEmployees(List<Employee> employees);

    @Override
    public int getId();

    @Override
    public void setId(int id);

    public String getName();

    public void setName(String name);

    public String getLoc();

    public void setLoc(String loc);
}
