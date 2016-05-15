/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.dao;

import at.htlpinkafeld.cm.pojo.Department;
import at.htlpinkafeld.cm.pojo.Employee;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public class EmployeeDAO implements BaseDAO<Employee> , Serializable{
    
    private static final String EMPNO = "empno";
    private static final String ENAME = "ename";
    private static final String JOB = "job";
    private static final String MGR = "mgr";
    private static final String HIREDATE = "hiredate";
    private static final String SAL = "sal";
    private static final String COMM = "comm";
    private static final String DEPTNO = "deptno";
    
    @Override
    public List<Employee> getEntityList() {
        List<Employee> retVal = new LinkedList<>();
        
        try (Connection con = CompanyConnectionManager.getInst().getConn();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from emp")) {
            while (rs.next()) {
                retVal.add(new Employee(rs.getInt(EMPNO), rs.getString(ENAME), rs.getString(JOB), rs.getInt(MGR), rs.getDate(HIREDATE), rs.getInt(SAL), rs.getInt(COMM), rs.getInt(DEPTNO)));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return retVal;
    }
    
    public List<Employee> findByDeptno(int deptno){
        List<Employee> retVal = new LinkedList<>();
        
        try (Connection con = CompanyConnectionManager.getInst().getConn();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from emp where deptno="+deptno)) {
            while (rs.next()) {
                retVal.add(new Employee(rs.getInt(EMPNO), rs.getString(ENAME), rs.getString(JOB), rs.getInt(MGR), rs.getDate(HIREDATE), rs.getInt(SAL), rs.getInt(COMM), rs.getInt(DEPTNO)));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return retVal;
    }
    
    @Override
    public boolean insertEntity(Employee obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean removeEntity(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean updateEntity(Employee obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
