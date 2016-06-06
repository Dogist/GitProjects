/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.windowdecorator;

/**
 *
 * @author Martin Six
 */
public class SimpleWindow implements Window {

    @Override
    public void draw() {
        System.out.println("Draw Simple-Window.");
    }

    @Override
    public String getDescription() {
        return "Simple Window";
    }

}
