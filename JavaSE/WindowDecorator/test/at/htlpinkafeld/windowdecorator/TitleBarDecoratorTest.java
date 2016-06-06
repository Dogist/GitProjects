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
public class TitleBarDecoratorTest {

    /**
     * Test of getDescription method, of class TitleBarDecorator.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        TitleBarDecorator instance = new TitleBarDecorator(new SimpleWindow());
        String expResult = "Simple Window, including titlebar";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }
}
