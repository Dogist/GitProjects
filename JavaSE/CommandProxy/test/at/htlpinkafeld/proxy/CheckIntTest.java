/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.proxy;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Martin Six
 */
public class CheckIntTest {

    public CheckIntTest() {
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of run method, of class CheckInt.
     */
    @Test
    public void testRunNoException() {
        String[] args = {"1", "2", "3"};
        CheckInt cI = new CheckInt();
        cI.run(args);
    }

    /**
     * Test of run method, of class CheckInt.
     */
    @Test(expected = NumberFormatException.class)
    public void testRunWithException() {
        String[] args = {"1", "2", "drei"};
        CheckInt cI = new CheckInt();
        cI.run(args);
    }

}
