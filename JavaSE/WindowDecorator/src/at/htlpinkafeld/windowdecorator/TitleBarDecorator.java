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
public class TitleBarDecorator extends WindowDecorator {

    public TitleBarDecorator(Window decoratedWindow) {
        super(decoratedWindow);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", including titlebar";
    }

    @Override
    public void draw() {
        super.draw();
        System.out.println("Draw titlebar");
    }

}
