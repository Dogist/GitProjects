/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.dao.database;

import at.htlpinkafeld.database_manager.pojo.Employee;
import java.sql.Date;
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
public class SQLEmpDAO extends BaseSQLDAO<Employee> implements EmpDAO {

    public static final String EMPNO_COL = "empno";
    public static final String ENAME_COL = "ename";
    public static final String JOB_COL = "job";
    public static final String MGR_COL = "mgr";
    public static final String HIREDATE_COL = "hiredate";
    public static final String SAL_COL = "sal";
    public static final String COMM_COL = "comm";
    public static final String DEPTNO_COL = SQLDeptDAO.PRIMARY_KEY;

    public static final String TABLE_NAME = "emp";
    public static final String PRIMARY_KEY = EMPNO_COL;
    public static final String[] ALL_COLUMNS = {EMPNO_COL, ENAME_COL, JOB_COL, MGR_COL, HIREDATE_COL, SAL_COL, COMM_COL, DEPTNO_COL};

    private DeptDAO deptDAO;

    public SQLEmpDAO() {
        super(TABLE_NAME, ALL_COLUMNS, PRIMARY_KEY);
        deptDAO = new SQLDeptDAO();
    }

    @Override
    protected Map<String, Object> entityToMap(Employee entity) {
        Map<String, Object> map = new HashMap<>();
        map.put(EMPNO_COL, entity.getEmpno());
        map.put(ENAME_COL, entity.getEname());
        map.put(JOB_COL, entity.getJob());
        if (entity.getMgr() != null) {
            map.put(MGR_COL, entity.getMgr().getEmpno());
        } else {
            map.put(MGR_COL, null);
        }
        map.put(HIREDATE_COL, entity.getHiredate() != null ? Date.valueOf(entity.getHiredate()) : null);
        map.put(SAL_COL, entity.getSal());
        map.put(COMM_COL, entity.getComm());
        if (entity.getDept() != null) {
            map.put(DEPTNO_COL, entity.getDept().getDeptno());
        } else {
            map.put(DEPTNO_COL, deptDAO.getList().get(0).getDeptno());
        }
        return map;
    }

    @Override
    protected Employee getEntityFromResultSet(ResultSet rs) {
        try {
            int empno = rs.getInt(EMPNO_COL);

            Integer mgr = rs.getInt(MGR_COL);
            if (rs.wasNull()) {
                mgr = null;
            }

            Double sal = rs.getDouble(SAL_COL);
            if (rs.wasNull()) {
                sal = null;
            }

            Double comm = rs.getDouble(COMM_COL);
            if (rs.wasNull()) {
                comm = null;
            }

            return new Employee(empno, rs.getString(ENAME_COL), rs.getString(JOB_COL), mgr != null && mgr != empno ? get(mgr) : null, rs.getDate(HIREDATE_COL) != null ? rs.getDate(HIREDATE_COL).toLocalDate() : null, sal, comm, deptDAO.get(rs.getInt(DEPTNO_COL)));
        } catch (SQLException ex) {
            Logger.getLogger(SQLEmpDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    protected void updateEntityWithAutoKeys(ResultSet rs, Employee entity) {
        try {
            if (rs.next()) {
                entity.setEmpno(rs.getInt(EMPNO_COL));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLEmpDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
