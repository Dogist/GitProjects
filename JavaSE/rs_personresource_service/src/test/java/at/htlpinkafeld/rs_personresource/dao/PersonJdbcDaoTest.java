/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rs_personresource.dao;

import at.htlpinkafeld.connectionmanager.ConnectionManagerImpl;
import at.htlpinkafeld.connectionmanager.DAOHelper;
import at.htlpinkafeld.rs_personresource.model.Person;
import at.htlpinkafeld.rs_personresource.service.DAOSysException;
import at.htlpinkafeld.rs_personresource.service.PersonServiceImpl;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Martin Six
 */
public class PersonJdbcDaoTest {

    public PersonJdbcDaoTest() {
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of main method, of class PersonJdbcDao.
     */
    @Test
    public void testMain() {
        // Get instance of ConnectionManager and inject in DAOHelper
        DAOHelper.setConnectionManager(ConnectionManagerImpl.getInstance());
        // Set Tables to create: Tablename = Classname, primary key name have to be "id"
        DAOHelper.setTableClasses(new Class<?>[]{Person.class});
        try {
            // Create Database and Tables (determine database type and name)
            DAOHelper.createDatabaseAndTables(DAOHelper.DATABASE.HSQLDB, "crm");
        } catch (SQLException ex) {
            Logger.getLogger(JdbcBaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        PersonServiceImpl psi = new PersonServiceImpl();
        try {
            Person p = new Person(-1, "Hans", "Peter", "noplan@gmx.net");
            psi.insertPerson(p);
            p = new Person(-1, "Martin", "Six", "noplan@gmx.net");
            psi.insertPerson(p);
            List<Person> pL = psi.getAllPersons();
            assertEquals(pL.get(1), p);
            assertEquals(psi.findPersonById(1), p);
            psi.deletePerson(p);
            pL = psi.getAllPersons();
            if (pL.isEmpty()) {
                System.out.println("Keine Personen vorhanden");
            } else {
                System.out.println("Personen vorhanden");
            }
        } catch (DAOSysException ex) {
            Logger.getLogger(PersonJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
