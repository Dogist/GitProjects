/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.dao.factory;

import at.htlpinkafeld.cm.dao.inmem.InMemFactory;
import at.htlpinkafeld.cm.dao.jdbc.JDBCFactory;
import at.htlpinkafeld.cm.dao.interf.EmployeeDao;
import at.htlpinkafeld.cm.dao.interf.DepartmentDao;
import at.htlpinkafeld.cm.dao.interf.SalgradeDao;

/**
 *
 * @author Martin Six
 */
public abstract class AbstractDAOFactory {

    private static AbstractDAOFactory daoFact = null;

    private static final int JDBC_DAO = 1;
    private static final int INMEM_DAO = 0;

    private static final int DAOTYPE = JDBC_DAO;

    public abstract EmployeeDao getEmployeeDAO();

    public abstract DepartmentDao getDepartmentDAO();

    public abstract SalgradeDao getSalgradeDAO();

    public static synchronized AbstractDAOFactory getDAOFactory() {
        if (daoFact == null) {
            switch (DAOTYPE) {
                case JDBC_DAO:
                    daoFact = JDBCFactory.getDAOFactory();
                case INMEM_DAO:
                    daoFact = InMemFactory.getDAOFactory();
                default:
                    return null;
            }
        }
        return daoFact;
    }
}
