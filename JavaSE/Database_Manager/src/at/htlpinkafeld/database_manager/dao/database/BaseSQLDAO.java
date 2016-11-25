/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.dao.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Six
 */
public abstract class BaseSQLDAO<T> implements BaseDAO<T> {

    private final String TABLE_NAME;
    private final String[] PRIMARY_KEY;
    private final String[] ALL_COLUMNS;

    private final String SQL_SELECTALL_STATEMENT;
    private final String SQL_INSERT_STATEMENT;
    private final String SQL_UPDATE_STATEMENT;
    private final String SQL_DELETE_STATEMENT;

    protected BaseSQLDAO(String tableName, String[] columns, String... primary_key) {
        TABLE_NAME = tableName;
        PRIMARY_KEY = primary_key;
        ALL_COLUMNS = columns;

        SQL_SELECTALL_STATEMENT = createSelectAllStatement();

        SQL_INSERT_STATEMENT = createInsertStatement();

        SQL_UPDATE_STATEMENT = createUpdateStatement();

        SQL_DELETE_STATEMENT = createDeleteStatement();

    }

    private String createSelectAllStatement() {
        String sql_statement = "SELECT * FROM " + TABLE_NAME;
        return sql_statement;
    }

    //Used to dynamically create a sql-insert-Statement
    //usable for all Tables except n-m Relation Tables
    private String createInsertStatement() {
        String sql_statement = "INSERT INTO " + TABLE_NAME + " (";
        for (String col : ALL_COLUMNS) {
            sql_statement += col + ", ";
        }
        sql_statement = sql_statement.substring(0, sql_statement.length() - 2) + ") VALUES (";
        for (int i = ALL_COLUMNS.length; i > 0; i--) {
            sql_statement += "?, ";
        }
        sql_statement = sql_statement.substring(0, sql_statement.length() - 2) + ")";
        return sql_statement;
    }

    //Used to dynamically create a sql-update-Statement
    private String createUpdateStatement() {
        String sql_statement = "UPDATE " + TABLE_NAME + " SET ";
        for (String col : ALL_COLUMNS) {
            boolean colIsPK = false;
            for (String pk : PRIMARY_KEY) {
                if (pk.contentEquals(col)) {
                    colIsPK = true;
                }
            }
            if (!colIsPK) {
                sql_statement += col + " = ?, ";
            }
        }
        sql_statement = sql_statement.substring(0, sql_statement.length() - 2) + " WHERE ";
        for (String pk : PRIMARY_KEY) {
            sql_statement += pk + "= ? AND ";
        }
        sql_statement = sql_statement.substring(0, sql_statement.length() - 4);

        return sql_statement;
    }

    //Used to dynamically create a sql-update-Statement
    //usable for all Tables except n-m Relation Tables
    private String createDeleteStatement() {
        String sql_statement = "DELETE FROM " + TABLE_NAME + " WHERE ";
        for (String pk : PRIMARY_KEY) {
            sql_statement += pk + "= ? AND ";
        }
        sql_statement = sql_statement.substring(0, sql_statement.length() - 4);

        return sql_statement;
    }

    @Override
    public List<T> getList() {
        List<T> entityList = new ArrayList<>();
        Connection con = DAOFactory.getDAOFactory().getDbConnection();
        try (Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL_SELECTALL_STATEMENT)) {

            while (rs.next()) {
                entityList.add(getEntityFromResultSet(rs));

            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return entityList;
    }

    @Override
    public void insert(T o) {
        Connection con = DAOFactory.getDAOFactory().getDbConnection();
        if (con != null) {
            try (PreparedStatement stmt = con.prepareStatement(SQL_INSERT_STATEMENT, Statement.RETURN_GENERATED_KEYS)) {

                Map<String, Object> entityMap = entityToMap(o);
                int i = 1;
                for (String col : ALL_COLUMNS) {
                    if (entityMap.get(col).toString().contentEquals("-1")) {
                        stmt.setNull(i, Types.INTEGER);
                    } else {
                        stmt.setObject(i, entityMap.get(col));
                    }
                    i++;
                }

                stmt.executeUpdate();
                updateEntityWithAutoKeys(stmt.getGeneratedKeys(), o);

            } catch (SQLException ex) {
                Logger.getLogger(BaseSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(T o) {
        Connection con = DAOFactory.getDAOFactory().getDbConnection();
        if (con != null) {
            try (PreparedStatement stmt = con.prepareStatement(SQL_UPDATE_STATEMENT);) {

                Map<String, Object> entityMap = entityToMap(o);
                int i = 1;
                for (String col : ALL_COLUMNS) {
                    boolean colIsPK = false;
                    for (String pk : PRIMARY_KEY) {
                        if (pk.contentEquals(col)) {
                            colIsPK = true;
                        }
                    }
                    if (!colIsPK) {
                        stmt.setObject(i, entityMap.get(col));
                        i++;
                    }
                }

                for (String pk : PRIMARY_KEY) {
                    stmt.setObject(i, entityMap.get(pk));
                    i++;
                }
                stmt.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(BaseSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete(T o) {
        Connection con = DAOFactory.getDAOFactory().getDbConnection();
        try (PreparedStatement stmt = con.prepareStatement(SQL_DELETE_STATEMENT);) {

            Map<String, Object> entityMap = entityToMap(o);
            int i = 1;
            for (String pk : PRIMARY_KEY) {
                stmt.setObject(i, entityMap.get(pk));
                i++;
            }

            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BaseSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected abstract Map<String, Object> entityToMap(T entity);

    protected abstract T getEntityFromResultSet(ResultSet rs);

    protected abstract void updateEntityWithAutoKeys(ResultSet rs, T entity);

    @Override
    public void executeBatchUpdate(List<String> commands) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
