/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rs_personresource.dao;

import at.htlpinkafeld.connectionmanager.DAOHelper;
import at.htlpinkafeld.rs_personresource.model.Identifiable;
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
 * @param <T>
 */
public abstract class JdbcBaseDao<T extends Identifiable> {

    private final String TABLENAME;
    private final String PKNAME;
    private final Connection con;

    protected abstract T getPojoFromResultSet(ResultSet result) throws SQLException;

    protected abstract PreparedStatement getUpdateStatement(Connection c, T t) throws SQLException;

    protected abstract PreparedStatement getInsertStatement(Connection c, T t) throws SQLException;

    public JdbcBaseDao(String tablename, String pkName) throws SQLException {
        this.TABLENAME = tablename;
        this.PKNAME = pkName;

        con = DAOHelper.getConnectionManager().getConnection();
    }

    public String getTablename() {
        return TABLENAME;
    }

    public String getPkName() {
        return PKNAME;
    }

    protected PreparedStatement getPreparedStatement(Connection c, String sql, int id) throws SQLException {
        PreparedStatement stmt = c.prepareStatement(sql);
        stmt.setInt(1, id);
        return stmt;
    }

    public void delete(T t) {
        if (t.getId() < 0) {
            return;
        }

        String sql = "DELETE FROM " + TABLENAME + " WHERE " + PKNAME + " = ?";
        try (PreparedStatement stmt = getPreparedStatement(con, sql, t.getId())) {

            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JdbcBaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public T read(int id) {
        T t = null;
        String sql = "SELECT * FROM " + TABLENAME + " WHERE " + PKNAME + " = ?";
        try (PreparedStatement stmt = getPreparedStatement(con, sql, id);
                ResultSet result = stmt.executeQuery()) {

            if (result.next()) {
                t = getPojoFromResultSet(result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JdbcBaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return t;
    }

    public List<T> list() {
        List<T> results = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLENAME;
        try (Statement stmt = con.createStatement();
                ResultSet result = stmt.executeQuery(sql)) {

            while (result.next()) {
                results.add(getPojoFromResultSet(result));
            }
        } catch (SQLException ex) {
            Logger.getLogger(JdbcBaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return results;
    }

    public void update(T t) {
        if (t.getId() < 0) {
            return;
        }

        try (PreparedStatement stmt = getUpdateStatement(con, t)) {
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JdbcBaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void create(T t) {
        if (t.getId() >= 0) {
            return;
        }

        try (PreparedStatement stmt = getInsertStatement(con, t);
                ResultSet genKeys = (stmt.executeUpdate() == 1) ? stmt.getGeneratedKeys() : null) {

            if (genKeys != null && genKeys.next()) {
                t.setId(genKeys.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(JdbcBaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
