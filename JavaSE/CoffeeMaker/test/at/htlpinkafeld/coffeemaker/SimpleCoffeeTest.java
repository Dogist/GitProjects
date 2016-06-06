/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.coffeemaker;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Martin Six
 */
public class SimpleCoffeeTest {

    Coffee c;

    @Before
    public void setUp() {
        c = new SimpleCoffee();
    }

    /**
     * Test of getCost method, of class SimpleCoffee.
     */
    @Test
    public void testGetCost() {
        System.out.println("getCost");
        double expResult = 1.0;
        double result = c.getCost();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getIngredients method, of class SimpleCoffee.
     */
    @Test
    public void testGetIngredients() {
        System.out.println("getIngredients");
        String expResult = "Coffee";
        String result = c.getIngredients();
        assertEquals(expResult, result);
    }

}
