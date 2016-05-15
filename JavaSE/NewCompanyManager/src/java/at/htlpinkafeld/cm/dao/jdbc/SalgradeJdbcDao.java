/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.dao.jdbc;

import at.htlpinkafeld.cm.dao.interf.SalgradeDao;
import at.htlpinkafeld.cm.pojo.Salgrade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Martin Six
 */
public class SalgradeJdbcDao extends BaseJdbcDao<Salgrade> implements SalgradeDao {

    protected SalgradeJdbcDao() {
        super("SALGRADE", "GRADE");
    }

    @Override
    protected Salgrade getPojoFromResultSet(ResultSet result) throws SQLException {
        Salgrade sal = new Salgrade(result.getDouble("LOSAL"), result.getDouble("HISAL"));
        sal.setId(result.getInt("GRADE"));
        return sal;
    }

    @Override
    protected PreparedStatement getUpdateStatement(Connection c, Salgrade sal) throws SQLException {
        String sql = "UPDATE " + getTablename() + " SET LOSAL=?, HISAL=? WHERE " + getPkName() + "=?";
        PreparedStatement stmt = c.prepareStatement(sql);
        stmt.setDouble(1, sal.getLosal());
        stmt.setDouble(2, sal.getHisal());
        stmt.setInt(3, sal.getId());
        return stmt;
    }

    @Override
    protected PreparedStatement getInsertStatement(Connection c, Salgrade sal) throws SQLException {
        String sql = "INSERT INTO " + getTablename() + " (LOSAL, HISAL) VALUES (?,?)";
        PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setDouble(1, sal.getLosal());
        stmt.setDouble(2, sal.getHisal());
        return stmt;
    }

}
