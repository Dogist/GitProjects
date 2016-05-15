/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.dao.jdbc;

import at.htlpinkafeld.cm.dao.interf.EmployeeDao;
import at.htlpinkafeld.cm.dao.interf.DepartmentDao;
import at.htlpinkafeld.cm.pojo.Department;
import at.htlpinkafeld.cm.pojo.DepartmentImpl;
import at.htlpinkafeld.cm.pojo.DepartmentProxy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Martin Six
 */
public class DepartmentJdbcDao extends BaseJdbcDao<Department> implements DepartmentDao {

    private final EmployeeDao empDao;

    protected DepartmentJdbcDao(EmployeeDao empDao) {
        super("DEPT", "DEPTNO");
        this.empDao = empDao;
    }

    @Override
    protected Department getPojoFromResultSet(ResultSet result) throws SQLException {
        Department d = new DepartmentProxy(new DepartmentImpl(result.getString("DNAME"), result.getString("LOC")));
        d.setId(result.getInt(getPkName()));
        return d;
    }

    @Override
    protected PreparedStatement getUpdateStatement(Connection c, Department dept)
            throws SQLException {

        String sql = "UPDATE " + getTablename() + " SET DNAME=?, LOC=? WHERE " + getPkName() + "=?";
        PreparedStatement stmt = c.prepareStatement(sql);
        stmt.setString(1, dept.getName());
        stmt.setString(2, dept.getLoc());
        stmt.setInt(3, dept.getId());
        return stmt;

    }

    @Override
    protected PreparedStatement getInsertStatement(Connection c, Department dept)
            throws SQLException {
        String sql = "INSERT INTO " + getTablename() + " (DNAME, LOC) VALUES (?,?)";
        PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, dept.getName());
        stmt.setString(2, dept.getLoc());
        return stmt;
    }
}
