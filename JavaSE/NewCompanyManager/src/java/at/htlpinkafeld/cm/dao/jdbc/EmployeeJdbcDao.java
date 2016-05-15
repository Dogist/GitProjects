/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.dao.jdbc;

import at.htlpinkafeld.cm.dao.util.ConnectionManager;
import at.htlpinkafeld.cm.dao.interf.EmployeeDao;
import at.htlpinkafeld.cm.pojo.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Six
 */
public class EmployeeJdbcDao extends BaseJdbcDao<Employee> implements EmployeeDao {

    protected EmployeeJdbcDao() {
        super("EMP", "EMPNO");
    }

    @Override
    protected Employee getPojoFromResultSet(ResultSet result) throws SQLException {
        Employee e = new Employee(result.getString("ENAME"), result.getString("JOB"), new EmployeeJdbcDao().read(result.getInt("MGR")), result.getTimestamp("Hiredate"), result.getDouble("SAL"), result.getDouble("COMM"), null);
        e.setId(result.getInt(getPkName()));
        return e;
    }

    @Override
    protected PreparedStatement getUpdateStatement(Connection c, Employee emp) throws SQLException {
        String sql = "UPDATE " + getTablename()
                + " SET ENAME=?, JOB=?, MGR=?, HIREDATE=?, SAL=?, COMM=?, DEPTNO=? WHERE " + getPkName() + "=?";
        PreparedStatement stmt = c.prepareStatement(sql);
        stmt.setString(1, emp.getName());
        stmt.setString(2, emp.getJob());
        stmt.setInt(3, emp.getMgr().getId());
        stmt.setTimestamp(4, emp.getHiredate());
        stmt.setDouble(5, emp.getSal());
        stmt.setDouble(6, emp.getComm());
        stmt.setInt(7, emp.getDepartment().getId());
        stmt.setInt(8, emp.getId());
        return stmt;
    }

    @Override
    protected PreparedStatement getInsertStatement(Connection c, Employee emp) throws SQLException {
        String sql = "INSERT INTO " + getTablename() + " (ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, emp.getName());
        stmt.setString(2, emp.getJob());
        stmt.setInt(3, emp.getMgr().getId());
        stmt.setTimestamp(4, emp.getHiredate());
        stmt.setDouble(5, emp.getSal());
        stmt.setDouble(6, emp.getComm());
        stmt.setInt(7, emp.getDepartment().getId());
        return stmt;
    }

    @Override
    public List<Employee> findByDepartment(int deptId) {
        List<Employee> results = new ArrayList<>();
        String sql = "SELECT * FROM " + getTablename() + " WHERE deptno=?";
        try (Connection con = ConnectionManager.getInstance().getConnection();
                PreparedStatement stmt = getPreparedStatement(con, sql, deptId);
                ResultSet result = stmt.executeQuery()) {

            while (result.next()) {
                results.add(getPojoFromResultSet(result));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return results;
    }
}
