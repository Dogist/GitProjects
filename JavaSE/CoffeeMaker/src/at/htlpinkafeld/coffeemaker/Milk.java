/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.coffeemaker;

/**
 *
 * @author Martin Six
 */
public class Milk extends CoffeeDecorator {

    public Milk(Coffee decoratedCoffe) {
        super(decoratedCoffe);
    }

    @Override
    public String getIngredients() {
        return super.getIngredients() + ", Milk";
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.5;
    }

}
