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
public abstract class CoffeeDecorator implements Coffee {

    private final Coffee decoratedCoffe;

    public CoffeeDecorator(Coffee decoratedCoffe) {
        this.decoratedCoffe = decoratedCoffe;
    }

    @Override
    public double getCost() {
        return decoratedCoffe.getCost();
    }

    @Override
    public String getIngredients() {
        return decoratedCoffe.getIngredients();
    }

}
