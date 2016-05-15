/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.dao.simple.employee;

import at.htlpinkafeld.jdbcbase.BaseJdbcDao;
import at.htlpinkafeld.jdbcbase.ConnectionManager;
import at.htlpinkafeld.jdbcbase.WrappedConnection;
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
    public EmployeeJdbcDao(){
        super("EMP", "EMPNO");
    }
    
    @Override
    protected Employee getPojoFromResultSet(ResultSet result) throws SQLException {
        Employee e = new Employee(result.getString("ENAME"), result.getString("JOB"),
                                  result.getDouble("SAL"));
        e.setId(result.getInt( getPkName() ));
        return e;
    }

    @Override
    protected PreparedStatement getUpdateStatement(Connection c, Employee emp)throws SQLException {
        String sql = "UPDATE " + getTablename() + 
                     " SET ENAME=?, JOB=?, SAL=? WHERE "+getPkName()+"=?";
        PreparedStatement stmt = c.prepareStatement(sql);
        stmt.setString(1, emp.getName());
        stmt.setString(2, emp.getJob());
        stmt.setDouble(3, emp.getSal());
        stmt.setInt(4, emp.getId());
        return stmt;
    }

    @Override
    protected PreparedStatement getInsertStatement(Connection c, Employee emp)throws SQLException {
        String sql = "INSERT INTO " + getTablename() + " (ENAME, JOB, SAL) VALUES (?,?,?)";
        PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, emp.getName());
        stmt.setString(2, emp.getJob());
        stmt.setDouble(3, emp.getSal());
        return stmt;
    }  

    @Override
    public List<Employee> findByDepartment(int deptId) {
        List<Employee> results = new ArrayList<>();
        String sql = "SELECT * FROM " + getTablename() + " WHERE deptno=?";
        try( WrappedConnection wrCon = ConnectionManager.getInstance().getWrappedConnection();
             PreparedStatement stmt = getPreparedStatement(wrCon.getConn(), sql, deptId); 
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
