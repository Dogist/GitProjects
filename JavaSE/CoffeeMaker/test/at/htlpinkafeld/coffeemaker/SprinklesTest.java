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
public class SprinklesTest {

    Coffee c;

    @Before
    public void setUp() {
        c = new Sprinkles(new SimpleCoffee());
    }

    /**
     * Test of getIngredients method, of class Sprinkles.
     */
    @Test
    public void testGetIngredients() {
        System.out.println("getIngredients");
        String expResult = "Coffee, Sprinkles";
        String result = c.getIngredients();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCost method, of class Sprinkles.
     */
    @Test
    public void testGetCost() {
        System.out.println("getCost");
        double expResult = 1.2;
        double result = c.getCost();
        assertEquals(expResult, result, 0.0);
    }

}
