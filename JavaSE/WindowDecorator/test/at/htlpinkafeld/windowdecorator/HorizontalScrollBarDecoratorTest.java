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
public class HorizontalScrollBarDecoratorTest {

    /**
     * Test of getDescription method, of class HorizontalScrollBarDecorator.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        HorizontalScrollBarDecorator instance = new HorizontalScrollBarDecorator(new SimpleWindow());
        String expResult = "Simple Window, including horizontal scrollbars";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

}
