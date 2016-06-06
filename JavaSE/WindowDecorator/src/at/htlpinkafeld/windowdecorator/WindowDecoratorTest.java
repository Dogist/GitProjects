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
public class WindowDecoratorTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Window w = new HorizontalScrollBarDecorator(new VerticalScrollBarDecorator(new HorizontalScrollBarDecorator(new SimpleWindow())));

        w.draw();
        System.out.println("Description: " + w.getDescription());
    }

}
