/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.dao.database;

/**
 *
 * @author Martin Six
 */
public class SQLEmpDAO {

    public static final String EMPNO_COL = "empno";
    public static final String ENAME_COL = "ename";
    public static final String JOB_COL = "job";
    public static final String MGR_COL = "mgr";
    public static final String HIREDATE_COL = "hiredate";
    private static final String SAL_COL = "sal";
    public static final String COMM_COL = "comm";
    public static final String DEPTNO_COL = SQLDeptDAO.PRIMARY_KEY;

    public static final String TABLE_NAME = "emp";
    public static final String PRIMARY_KEY = EMPNO_COL;
    public static final String[] ALL_COLUMNS = {EMPNO_COL, ENAME_COL, JOB_COL, MGR_COL, HIREDATE_COL, SAL_COL, COMM_COL, DEPTNO_COL};
}
