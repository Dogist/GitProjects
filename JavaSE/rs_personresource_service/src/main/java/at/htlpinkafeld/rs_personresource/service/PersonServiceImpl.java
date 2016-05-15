/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rs_personresource.service;

import at.htlpinkafeld.connectionmanager.ConnectionManagerImpl;
import at.htlpinkafeld.connectionmanager.DAOHelper;
import at.htlpinkafeld.rs_personresource.dao.PersonDao;
import at.htlpinkafeld.rs_personresource.dao.PersonJdbcDao;
import at.htlpinkafeld.rs_personresource.model.Person;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Six
 */
public class PersonServiceImpl implements PersonService {

    PersonDao personDao;

    public PersonServiceImpl() {
        try {
            DAOHelper.setConnectionManager(ConnectionManagerImpl.getInstance());
            // Set Tables to create: Tablename = Classname, primary key name have to be "id"
            DAOHelper.setTableClasses(new Class<?>[]{Person.class});
            // Create Database and Tables (determine database type and name)
            DAOHelper.createDatabaseAndTables(DAOHelper.DATABASE.HSQLDB, "crm");

            personDao = new PersonJdbcDao();
        } catch (SQLException ex) {
            Logger.getLogger(PersonServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void insertPerson(Person person) throws DAOSysException {
        personDao.create(person);
    }

    @Override
    public void updatePerson(Person person) throws DAOSysException {
        personDao.update(person);
    }

    @Override
    public void deletePerson(Person person) throws DAOSysException {
        personDao.delete(person);
    }

    @Override
    public Person findPersonById(int id) throws DAOSysException {
        return personDao.read(id);
    }

    @Override
    public List<Person> getAllPersons() throws DAOSysException {
        return personDao.list();
    }
}
