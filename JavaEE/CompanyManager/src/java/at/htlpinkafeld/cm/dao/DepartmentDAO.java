/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.dao;

import at.htlpinkafeld.cm.pojo.Department;
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
public class DepartmentDAO implements BaseDAO<Department>, Serializable {
    private static final String DEPTNO="deptno";
    private static final String DNAME="dname";
    private static final String LOC="loc";
    

    @Override
    public List<Department> getEntityList() {
        List<Department> retVal = new LinkedList<>();

        try (Connection con = CompanyConnectionManager.getInst().getConn();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select deptno, dname, loc from dept")) {
            while (rs.next()) {
                retVal.add(new Department(rs.getInt(DEPTNO), rs.getString(DNAME), rs.getString(LOC)));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return retVal;
    }

    @Override
    public boolean insertEntity(Department obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeEntity(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateEntity(Department obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Department findByID(int id) {
        Department retVal = null;

        try (Connection con = CompanyConnectionManager.getInst().getConn();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select deptno, dname, loc from dept where deptno =" + id)) {

            if (rs.next()) {
                retVal = new Department(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return retVal;
    }
}
