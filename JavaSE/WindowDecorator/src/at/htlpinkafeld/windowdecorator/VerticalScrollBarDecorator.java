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
public class VerticalScrollBarDecorator extends WindowDecorator {

    public VerticalScrollBarDecorator(Window decoratedWindow) {
        super(decoratedWindow);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", including vertical scrollbars";
    }

    @Override
    public void draw() {
        super.draw();
        System.out.println("Draw vertical scrollbars");
    }

}
