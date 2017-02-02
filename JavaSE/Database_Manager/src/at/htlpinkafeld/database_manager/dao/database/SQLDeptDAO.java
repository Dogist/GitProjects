/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.dao.database;

import at.htlpinkafeld.database_manager.pojo.Department;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Six
 */
public class SQLDeptDAO extends BaseSQLDAO<Department> implements DeptDAO {

    private static final String DEPTNO_COL = "deptno";
    private static final String DNAME_COL = "dname";
    private static final String LOC_COL = "loc";

    public static final String TABLE_NAME = "dept";
    public static final String PRIMARY_KEY = DEPTNO_COL;
    public static final String[] ALL_COLUMNS = {DEPTNO_COL, DNAME_COL, LOC_COL};

    public SQLDeptDAO() {
        super(TABLE_NAME, ALL_COLUMNS, PRIMARY_KEY);
    }

    @Override
    protected Map<String, Object> entityToMap(Department entity) {
        Map<String, Object> map = new HashMap<>();
        map.put(DEPTNO_COL, entity.getDeptno());
        map.put(DNAME_COL, entity.getDname());
        map.put(LOC_COL, entity.getLoc());

        return map;
    }

    @Override
    protected Department getEntityFromResultSet(ResultSet rs) {
        try {
            return new Department(rs.getInt(DEPTNO_COL), rs.getString(DNAME_COL), rs.getString(LOC_COL));
        } catch (SQLException ex) {
            Logger.getLogger(SQLDeptDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    protected void updateEntityWithAutoKeys(ResultSet rs, Department entity) {
        try {
            if (rs.next()) {
                entity.setDeptno(rs.getInt(DEPTNO_COL));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLDeptDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
