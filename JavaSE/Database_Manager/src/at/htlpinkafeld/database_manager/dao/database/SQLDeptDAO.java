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
public class SQLDeptDAO {

    private static final String DEPTNO_COL = "deptno";
    private static final String DNAME_COL = "dname";
    private static final String LOC_COL = "loc";

    public static final String TABLE_NAME = "dept";
    public static final String PRIMARY_KEY = DEPTNO_COL;
    public static final String[] ALL_COLUMNS = {DEPTNO_COL, DNAME_COL, LOC_COL};

}
