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
public class CoffeeDecoratorTest {

    /**
     * Test of getCost method, of class CoffeeDecorator.
     */
    @Test
    public void testRandomGetCost() {
        System.out.println("getCost");
        for (int y = 0; y < 100; y++) {
            Coffee c = new SimpleCoffee();
            double res = 1.0;
            for (int i = 0; i < 8; i++) {
                int x = (int) Math.floor(Math.random() * 3);
                switch (x) {
                    case 0:
                        c = new Milk(c);
                        res += 0.5;
                        break;
                    case 1:
                        c = new Sprinkles(c);
                        res += 0.2;
                        break;
                    case 2:
                        c = new WhippedCream(c);
                        res += 0.7;
                        break;
                }
                assertEquals(res, c.getCost(), 0.0);
            }
        }
    }

    /**
     * Test of getIngredients method, of class CoffeeDecorator.
     */
    @Test
    public void testRandomGetIngredients() {
        System.out.println("getIngredients");
        Coffee c;
        for (int y = 0; y < 100; y++) {
            c = new SimpleCoffee();
            String res = "Coffee";
            for (int i = 0; i < 8; i++) {
                int x = (int) Math.floor(Math.random() * 3);
                switch (x) {
                    case 0:
                        c = new Milk(c);
                        res += ", Milk";
                        break;
                    case 1:
                        c = new Sprinkles(c);
                        res += ", Sprinkles";
                        break;
                    case 2:
                        c = new WhippedCream(c);
                        res += ", Whipped Cream";
                        break;
                }
                assertEquals(res, c.getIngredients());
            }
        }
    }
}
