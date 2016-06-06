/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.coffeemaker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Six
 */
public class CoffeeMaker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Coffee c = new SimpleCoffee();
        boolean cont = true;
        System.out.println("CoffeeMaker has started. type in menu to see the menu and quit to exit CoffeeMaker.");
        while (cont) {
            try {
                String inp = br.readLine();
                switch (inp) {
                    case "milk":
                        c = new Milk(c);
                        System.out.println("milk was added to the coffee");
                        break;
                    case "whip":
                        c = new WhippedCream(c);
                        System.out.println("whip was added to the coffee");
                        break;
                    case "sprinkles":
                        c = new Sprinkles(c);
                        System.out.println("sprinkles were added to the coffee");
                        break;
                    case "coffee":
                        c = new SimpleCoffee();
                        System.out.println("A new coffee was made");
                        break;
                    case "cost":
                        System.out.println("The current cost of the coffe is: " + c.getCost());
                        break;
                    case "ingr":
                        System.out.println("The current ingredients of the coffe are: " + c.getIngredients());
                        break;
                    case "quit":
                        System.out.println("CoffeeMaker is shutting down");
                        cont = false;
                        break;
                    case "menu":
                        System.out.println("Commands:\n\t'coffee' to create a new Coffee\n\t'milk' to add milk to the coffee\n"
                                + "\t'whip' to add whipped cream to the coffee\n\t'sprinkles' to add sprinkles to the coffee\n\t'cost' to see the current cost of the coffe\n"
                                + "\t'ingr' to see the currently added ingredients");
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(CoffeeMaker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
