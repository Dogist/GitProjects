/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.windowdecorator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Martin Six
 */
public class VerticalScrollBarDecoratorTest {

    /**
     * Test of getDescription method, of class VerticalScrollBarDecorator.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        VerticalScrollBarDecorator instance = new VerticalScrollBarDecorator(new SimpleWindow());
        String expResult = "Simple Window, including vertical scrollbars";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

}
