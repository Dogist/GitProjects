/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.dao.inmem;

import at.htlpinkafeld.cm.dao.factory.AbstractDAOFactory;
import at.htlpinkafeld.cm.dao.interf.EmployeeDao;
import at.htlpinkafeld.cm.dao.interf.DepartmentDao;
import at.htlpinkafeld.cm.dao.interf.SalgradeDao;

/**
 *
 * @author Martin Six
 */
public class InMemFactory extends AbstractDAOFactory {

    private static InMemFactory daoFact = null;
    private final DepartmentDao depDao;
    private final EmployeeDao empDao;
    private final SalgradeDao salDao;

    private InMemFactory() {
        empDao = new EmployeeInMemoryDao();
        depDao = new DepartmentInMemoryDao();
        salDao = new SalgradeInMemoryDao();
    }

    public static synchronized InMemFactory getDAOFactory() {
        if (daoFact == null) {
            daoFact = new InMemFactory();
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
