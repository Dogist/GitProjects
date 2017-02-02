/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.dao.database;

import at.htlpinkafeld.database_manager.pojo.StatementEntityWrapper;
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
 * @param <T>
 */
public abstract class BaseSQLDAO<T> implements BaseDAO<T> {

    private final String TABLE_NAME;
    private final String PRIMARY_KEY;
    private final String[] ALL_COLUMNS;

    private final String SQL_SELECTALL_STATEMENT;
    private final String SQL_INSERT_STATEMENT;
    private final String SQL_UPDATE_STATEMENT;
    private final String SQL_DELETE_STATEMENT;
    private final String SQL_GET_STATEMENT;

    protected BaseSQLDAO(String tableName, String[] columns, String primary_key) {
        TABLE_NAME = tableName;
        PRIMARY_KEY = primary_key;
        ALL_COLUMNS = columns;

        SQL_SELECTALL_STATEMENT = createSelectAllStatement();

        SQL_INSERT_STATEMENT = createInsertStatement();

        SQL_UPDATE_STATEMENT = createUpdateStatement();

        SQL_DELETE_STATEMENT = createDeleteStatement();

        SQL_GET_STATEMENT = createGetStatement();

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
            if (PRIMARY_KEY.contentEquals(col)) {
                colIsPK = true;
            }

            if (!colIsPK) {
                sql_statement += col + " = ?, ";
            }
        }
        sql_statement = sql_statement.substring(0, sql_statement.length() - 2) + " WHERE ";
        sql_statement += PRIMARY_KEY + "= ?";

        return sql_statement;
    }

    //Used to dynamically create a sql-update-Statement
    //usable for all Tables except n-m Relation Tables
    private String createDeleteStatement() {
        String sql_statement = "DELETE FROM " + TABLE_NAME + " WHERE ";
        sql_statement += PRIMARY_KEY + "= ? ";

        return sql_statement;
    }

    private String createGetStatement() {
        String sql_statement = "SELECT * FROM " + TABLE_NAME + " WHERE ";
        sql_statement += PRIMARY_KEY + "= ? ";
        return sql_statement;
    }

    @Override
    public List<T> getList() throws DAOException {
        List<T> entityList = new ArrayList<>();

        Connection con = DAOFactory.getDAOFactory().getDbConnection();
        try (Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL_SELECTALL_STATEMENT)) {

            while (rs.next()) {
                entityList.add(getEntityFromResultSet(rs));
            }

        } catch (SQLException ex) {
            Logger.getLogger(BaseSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException(ex);
        }

        return entityList;
    }

    @Override
    public void insert(T o) throws DAOException {
        Connection con = DAOFactory.getDAOFactory().getDbConnection();
        if (con != null) {
            try (PreparedStatement stmt = con.prepareStatement(SQL_INSERT_STATEMENT, Statement.RETURN_GENERATED_KEYS)) {

                Map<String, Object> entityMap = entityToMap(o);
                int i = 1;
                for (String col : ALL_COLUMNS) {
                    if (entityMap.get(col) == null || entityMap.get(col).toString().contentEquals("-1")) {
                        stmt.setNull(i, Types.INTEGER);
                    } else {
                        stmt.setObject(i, entityMap.get(col));
                    }
                    i++;
                }

                stmt.executeUpdate();
                updateEntityWithAutoKeys(stmt.getGeneratedKeys(), o);

            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        }
    }

    @Override
    public void update(T o) throws DAOException {
        Connection con = DAOFactory.getDAOFactory().getDbConnection();
        if (con != null) {
            try (PreparedStatement stmt = con.prepareStatement(SQL_UPDATE_STATEMENT);) {

                Map<String, Object> entityMap = entityToMap(o);
                int i = 1;
                for (String col : ALL_COLUMNS) {
                    if (!PRIMARY_KEY.contentEquals(col)) {
                        stmt.setObject(i, entityMap.get(col));
                        i++;
                    }
                }

                stmt.setObject(i, entityMap.get(PRIMARY_KEY));

                stmt.executeUpdate();

            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        }
    }

    @Override
    public void delete(T o) throws DAOException {
        Connection con = DAOFactory.getDAOFactory().getDbConnection();
        try (PreparedStatement stmt = con.prepareStatement(SQL_DELETE_STATEMENT);) {

            Map<String, Object> entityMap = entityToMap(o);
            stmt.setObject(1, entityMap.get(PRIMARY_KEY));

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
    }

    @Override
    public T get(int pk) throws DAOException {
        T entity = null;
        if (pk != 0) {
            Connection con = DAOFactory.getDAOFactory().getDbConnection();

            try (PreparedStatement stmt = con.prepareStatement(SQL_GET_STATEMENT);) {

                stmt.setInt(1, pk);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    entity = getEntityFromResultSet(rs);
                }

            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        }
        return entity;
    }

    @Override
    public void executeBatchUpdate(List<StatementEntityWrapper<T>> commands) {

        Connection con = DAOFactory.getDAOFactory().getDbConnection();
        try (Statement stmt = con.createStatement()) {

            for (StatementEntityWrapper<T> entityWrapper : commands) {
                Map<String, Object> entityMap = entityToMap(entityWrapper.getEntity());
                String sqlStatement = "";
                switch (entityWrapper.getStatementtype()) {
                    case DELETE:
                        sqlStatement = SQL_DELETE_STATEMENT.replaceFirst("?", entityMap.get(PRIMARY_KEY).toString());
                        break;
                    case INSERT:
                        sqlStatement = SQL_INSERT_STATEMENT;
                        for (String col : ALL_COLUMNS) {
                            if (entityMap.get(col).toString().contentEquals("-1")) {
                                sqlStatement = sqlStatement.replaceFirst("?", "null");
                            } else {
                                sqlStatement = sqlStatement.replaceFirst("?", entityMap.get(col).toString());
                            }
                        }
                        break;
                    case UPDATE:
                        sqlStatement = SQL_UPDATE_STATEMENT;
                        for (String col : ALL_COLUMNS) {
                            if (!PRIMARY_KEY.contentEquals(col)) {
                                sqlStatement = sqlStatement.replaceFirst("?", entityMap.get(col).toString());
                            }
                        }
                        sqlStatement = sqlStatement.replaceFirst("?", entityMap.get(PRIMARY_KEY).toString());
                        break;
                    default:
                }
                stmt.addBatch(sqlStatement);
            }

            stmt.executeBatch();

        } catch (SQLException ex) {
            Logger.getLogger(BaseSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void commit() {
        try {
            DAOFactory.getDAOFactory().getDbConnection().commit();
        } catch (SQLException ex) {
            Logger.getLogger(BaseSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void rollback() {
        try {
            DAOFactory.getDAOFactory().getDbConnection().rollback();
        } catch (SQLException ex) {
            Logger.getLogger(BaseSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected abstract Map<String, Object> entityToMap(T entity);

    protected abstract T getEntityFromResultSet(ResultSet rs);

    protected abstract void updateEntityWithAutoKeys(ResultSet rs, T entity);

}
