/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.jdbcbase;

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
public abstract class BaseJdbcDao<T extends Identifiable> {

    private final String TABLENAME;
    private final String PKNAME;

    protected abstract T getPojoFromResultSet(ResultSet result) throws SQLException;

    protected abstract PreparedStatement getUpdateStatement(Connection c, T t) throws SQLException;

    protected abstract PreparedStatement getInsertStatement(Connection c, T t) throws SQLException;

    public BaseJdbcDao(String tablename, String pkName) {
        this.TABLENAME = tablename;
        this.PKNAME = pkName;
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
        try (WrappedConnection wrCon = ConnectionManager.getInstance().getWrappedConnection();
                PreparedStatement stmt = getPreparedStatement(wrCon.getConn(), sql, t.getId())) {

            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BaseJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public T read(int id) {
        T t = null;
        String sql = "SELECT * FROM " + TABLENAME + " WHERE " + PKNAME + " = ? LIMIT 1";
        try (WrappedConnection wrCon = ConnectionManager.getInstance().getWrappedConnection();
                PreparedStatement stmt = getPreparedStatement(wrCon.getConn(), sql, id);
                ResultSet result = stmt.executeQuery()) {

            if (result.next()) {
                t = getPojoFromResultSet(result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return t;
    }

    public List<T> list() {
        List<T> results = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLENAME;
        try (WrappedConnection wrCon = ConnectionManager.getInstance().getWrappedConnection();
                Statement stmt = wrCon.getConn().createStatement();
                ResultSet result = stmt.executeQuery(sql)) {

            while (result.next()) {
                results.add(getPojoFromResultSet(result));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return results;
    }

    public void update(T t) {
        if (t.getId() < 0) {
            return;
        }

        try (WrappedConnection wrCon = ConnectionManager.getInstance().getWrappedConnection();
                PreparedStatement stmt = getUpdateStatement(wrCon.getConn(), t)) {
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BaseJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void create(T t) {
        if (t.getId() >= 0) {
            return;
        }

        try (WrappedConnection wrCon = ConnectionManager.getInstance().getWrappedConnection();
                PreparedStatement stmt = getInsertStatement(wrCon.getConn(), t);
                ResultSet genKeys = (stmt.executeUpdate() == 1) ? stmt.getGeneratedKeys() : null) {

            if (genKeys != null && genKeys.next()) {
                t.setId(genKeys.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
