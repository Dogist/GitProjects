/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.dao.jdbc;

import at.htlpinkafeld.cm.dao.factory.AbstractDAOFactory;
import at.htlpinkafeld.cm.dao.jdbc.SalgradeJdbcDao;
import at.htlpinkafeld.cm.dao.jdbc.DepartmentJdbcDao;
import at.htlpinkafeld.cm.dao.jdbc.EmployeeJdbcDao;
import at.htlpinkafeld.cm.dao.interf.EmployeeDao;
import at.htlpinkafeld.cm.dao.interf.DepartmentDao;
import at.htlpinkafeld.cm.dao.interf.SalgradeDao;

/**
 *
 * @author Martin Six
 */
public class JDBCFactory extends AbstractDAOFactory {

    private static JDBCFactory daoFact = null;
    private final DepartmentDao depDao;
    private final EmployeeDao empDao;
    private final SalgradeDao salDao;

    private JDBCFactory() {
        empDao = new EmployeeJdbcDao();
        depDao = new DepartmentJdbcDao(empDao);
        salDao = new SalgradeJdbcDao();
    }

    public static synchronized JDBCFactory getDAOFactory() {
        if (daoFact == null) {
            daoFact = new JDBCFactory();
        }
        return daoFact;
    }

    @Override
    public EmployeeDao getEmployeeDAO() {
        return empDao;
    }

    @Override
    public DepartmentDao getDepartmentDAO() {
        return depDao;
    }

    @Override
    public SalgradeDao getSalgradeDAO() {
        return salDao;
    }

}
