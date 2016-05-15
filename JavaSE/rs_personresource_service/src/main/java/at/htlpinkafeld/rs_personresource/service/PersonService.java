/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rs_personresource.service;

import at.htlpinkafeld.rs_personresource.model.Person;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public interface PersonService {

    public void insertPerson(Person person) throws DAOSysException;

    public void updatePerson(Person person) throws DAOSysException;

    public void deletePerson(Person person) throws DAOSysException;

    public Person findPersonById(int id) throws DAOSysException;

    public List<Person> getAllPersons() throws DAOSysException;
}
