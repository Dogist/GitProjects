/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.windowdecorator;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Martin Six
 */
public class SimpleWindowTest {

    /**
     * Test of getDescription method, of class SimpleWindow.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        SimpleWindow instance = new SimpleWindow();
        String expResult = "Simple Window";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

}
