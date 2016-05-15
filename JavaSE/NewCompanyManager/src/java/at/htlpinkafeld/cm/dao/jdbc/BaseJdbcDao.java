/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.dao.jdbc;

import at.htlpinkafeld.cm.dao.util.ConnectionManager;
import at.htlpinkafeld.cm.pojo.Identifiable;
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
public abstract class BaseJdbcDao<T extends Identifiable>{

    private final String TABLENAME;
    private final String PKNAME;

    /**
     *
     * @param result
     * @return
     * @throws SQLException
     */
    protected abstract T getPojoFromResultSet(ResultSet result) throws SQLException;

    /**
     *
     * @param c
     * @param t
     * @return
     * @throws SQLException
     */
    protected abstract PreparedStatement getUpdateStatement(Connection c, T t) throws SQLException;

    /**
     *
     * @param c
     * @param t
     * @return
     * @throws SQLException
     */
    protected abstract PreparedStatement getInsertStatement(Connection c, T t) throws SQLException;

    /**
     *
     * @param tablename
     * @param pkName
     */
    public BaseJdbcDao(String tablename, String pkName) {
        this.TABLENAME = tablename;
        this.PKNAME = pkName;
    }

    /**
     *
     * @return
     */
    public String getTablename() {
        return TABLENAME;
    }

    /**
     *
     * @return
     */
    public String getPkName() {
        return PKNAME;
    }

    /**
     *
     * @param c
     * @param sql
     * @param id
     * @return
     * @throws SQLException
     */
    protected PreparedStatement getPreparedStatement(Connection c, String sql, int id) throws SQLException {
        PreparedStatement stmt = c.prepareStatement(sql);
        stmt.setInt(1, id);
        return stmt;
    }

    /**
     *
     * @param t
     */
    public void delete(T t) {
        if (t.getId() < 0) {
            return;
        }

        String sql = "DELETE FROM " + TABLENAME + " WHERE " + PKNAME + " = ?";
        try (Connection con = ConnectionManager.getInstance().getConnection();
                PreparedStatement stmt = getPreparedStatement(con, sql, t.getId())) {

            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BaseJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public T read(int id) {
        T t = null;
        String sql = "SELECT * FROM " + TABLENAME + " WHERE " + PKNAME + " = ? LIMIT 1";
        try (Connection con = ConnectionManager.getInstance().getConnection();
                PreparedStatement stmt = getPreparedStatement(con, sql, id);
                ResultSet result = stmt.executeQuery()) {

            if (result.next()) {
                t = getPojoFromResultSet(result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return t;
    }

    /**
     *
     * @return
     */
    public List<T> list() {
        List<T> results = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLENAME;
        try (Connection con = ConnectionManager.getInstance().getConnection();
                Statement stmt = con.createStatement();
                ResultSet result = stmt.executeQuery(sql)) {

            while (result.next()) {
                results.add(getPojoFromResultSet(result));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return results;
    }

    /**
     *
     * @param t
     */
    public void update(T t) {
        if (t.getId() < 0) {
            return;
        }

        try (Connection con = ConnectionManager.getInstance().getConnection();
                PreparedStatement stmt = getUpdateStatement(con, t)) {
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BaseJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param t
     */
    public void create(T t) {
        if (t.getId() >= 0) {
            return;
        }

        try (Connection con = ConnectionManager.getInstance().getConnection();
                PreparedStatement stmt = getInsertStatement(con, t);
                ResultSet genKeys = (stmt.executeUpdate() == 1) ? stmt.getGeneratedKeys() : null) {

            if (genKeys != null && genKeys.next()) {
                t.setId(genKeys.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
