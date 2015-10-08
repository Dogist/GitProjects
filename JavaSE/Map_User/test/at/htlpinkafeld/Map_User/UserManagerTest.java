/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.Map_User;

import java.sql.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Martin Six
 */
public class UserManagerTest {

    UserManager uMan;

    @Before
    public void setUp() {
        uMan = new UserManager();
    }

    @Test
    public void testAdd() {
        System.out.println("add");
        uMan.add(new User("Irish", "neaej.fun@gdot.net", "scheißWinows", "Nope", "Avi", Date.valueOf("1998-4-14")));
        assertEquals(1,uMan.users.size());
    }
    
    @Test
    public void testAddDoubleEmail() {
        System.out.println("add");
        uMan.add(new User("Irish", "neaej.fun@gdot.net", "scheißWinows", "Nope", "Avi", Date.valueOf("1998-4-14")));
        uMan.add(new User("Dogist", "neaej.fun@gdot.net", "noPlan", "Martinus", "Sixtus", Date.valueOf("1998-9-7")));
        assertEquals(1,uMan.users.size());
    }

    @Test
    public void testGet() {
        System.out.println("get");
        uMan.add(new User("Irish", "neaej.fun@gdot.net", "scheißWinows", "Nope", "Avi", Date.valueOf("1998-4-14")));
        uMan.add(new User("Dogist", "4str.fun@gdot.com", "noPlan", "Martinus", "Sixtus", Date.valueOf("1998-9-7")));
        assertEquals("neaej.fun@gdot.net", uMan.get("Irish").getEmail());
    }

    @Test
    public void testSetEMail() {
        System.out.println("setEMail");
        uMan.add(new User("Irish", "neaej.fun@gdot.net", "scheißWinows", "Nope", "Avi", Date.valueOf("1998-4-14")));
        uMan.setEMail("Irish", "4str.fun@gdot.com");
    }

    @Test(expected = NoSuchUserException.class)
    public void testSetEMailExc() {
        System.out.println("setEMailExc");
        uMan.add(new User("Irish", "neaej.fun@gdot.net", "scheißWinows", "Nope", "Avi", Date.valueOf("1998-4-14")));
        uMan.setEMail("Dogist", "4str.fun@gdot.com");
    }

    @Test
    public void testSetPW() {
        System.out.println("setPW");
        uMan.add(new User("Irish", "neaej.fun@gdot.net", "scheißWinows", "Nope", "Avi", Date.valueOf("1998-4-14")));
        uMan.setPW("Irish", "noPlan");
    }

    @Test(expected = NoSuchUserException.class)
    public void testSetPWExc1() {
        System.out.println("setPW");
        uMan.add(new User("Irish", "neaej.fun@gdot.net", "scheißWinows", "Nope", "Avi", Date.valueOf("1998-4-14")));
        uMan.setPW("Dogist", "noPlan");
    }

    @Test(expected = InvalidPasswordException.class)
    public void testSetPWExc2() {
        System.out.println("setPW");
        uMan.add(new User("Irish", "neaej.fun@gdot.net", "scheißWinows", "Nope", "Avi", Date.valueOf("1998-4-14")));
        uMan.setPW("Irish", "scheißWinows");
    }

    @Test
    public void testLogin() {
        System.out.println("login");
        uMan.add(new User("Irish", "neaej.fun@gdot.net", "scheißWinows", "Nope", "Avi", Date.valueOf("1998-4-14")));
        uMan.login("Irish", "scheißWinows");
    }

    @Test(expected = NoSuchUserException.class)
    public void testLoginExc1() {
        System.out.println("login");
        uMan.add(new User("Irish", "neaej.fun@gdot.net", "scheißWinows", "Nope", "Avi", Date.valueOf("1998-4-14")));
        uMan.login("Dogist", "scheißWinows");
    }

    @Test(expected = WrongPasswordException.class)
    public void testLoginExc2() {
        System.out.println("login");
        uMan.add(new User("Irish", "neaej.fun@gdot.net", "scheißWinows", "Nope", "Avi", Date.valueOf("1998-4-14")));
        uMan.login("Irish", "noPlan");
    }

    @Test
    public void testGetUserList() {
        System.out.println("getUserList");
        User t1 = new User("Irish", "neaej.fun@gdot.net", "scheißWinows", "Nope", "Avi", Date.valueOf("1998-4-14"));
        User t2 = new User("Dogist", "4str.fun@gdot.com", "noPlan", "Martinus", "Sixtus", Date.valueOf("1998-9-7"));
        uMan.add(t1);
        uMan.add(t2);
        List<User> l = uMan.getUserList();
        assertTrue(l.contains(t1));
        assertTrue(l.contains(t2));
    }

    @Test
    public void testGetSortedList() {
        System.out.println("getSortedList");
        User t1 = new User("Irish", "neaej.fun@gdot.net", "scheißWinows", "Nope", "Avi", Date.valueOf("1998-4-14"));
        User t2 = new User("Dogist", "4str.fun@gdot.com", "noPlan", "Martinus", "Sixtus", Date.valueOf("1998-9-7"));
        uMan.add(t1);
        uMan.add(t2);
        List<User> l = uMan.getSortedList();
        assertEquals(0,l.get(0).compareTo(t1));
        assertEquals(0,l.get(1).compareTo(t2));
    }

    @Test
    public void testGetSortedByMailList() {
        System.out.println("getSortedByMailList");
        User t1 = new User("Irish", "neej.fun@gdot.net", "scheißWinows", "Nope", "Avi", Date.valueOf("1998-4-14"));
        User t2 = new User("Dogist", "4str.fun@gdot.com", "noPlan", "Martinus", "Sixtus", Date.valueOf("1998-9-7"));
        uMan.add(t1);
        uMan.add(t2);
        List<User> l = uMan.getSortedByMailList();
        assertEquals(0,l.get(0).compareTo(t2));
        assertEquals(0,l.get(1).compareTo(t1));
    }

    @Test
    public void testGetSortedByAgeList() {
        System.out.println("getSortedByAgeList");
        User t1 = new User("Irish", "neaej.fun@gdot.net", "scheißWinows", "Nope", "Avi", Date.valueOf("1998-4-14"));
        User t2 = new User("Dogist", "4str.fun@gdot.com", "noPlan", "Martinus", "Sixtus", Date.valueOf("1998-9-7"));
        uMan.add(t1);
        uMan.add(t2);
        List<User> l = uMan.getSortedByAgeList();
        assertEquals(0,l.get(0).compareTo(t1));
        assertEquals(0,l.get(1).compareTo(t2));
    }

}
