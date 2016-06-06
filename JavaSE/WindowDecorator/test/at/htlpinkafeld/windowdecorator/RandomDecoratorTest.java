/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.windowdecorator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Martin Six
 */
public class RandomDecoratorTest {

    /**
     * Test of various WindowDecorator Compinations
     */
    @Test
    public void randomDescriptionTests() {
        Window w = new HorizontalScrollBarDecorator(new TitleBarDecorator(new VerticalScrollBarDecorator(new SimpleWindow())));
        assertEquals("Simple Window, including vertical scrollbars, including titlebar, including horizontal scrollbars", w.getDescription());
        for (int y = 0; y < 10; y++) {
            w = new SimpleWindow();
            String res = "Simple Window";
            for (int i = 0; i < 4; i++) {
                int x = (int) Math.floor(Math.random() * 3);
                switch (x) {
                    case 0:
                        w = new VerticalScrollBarDecorator(w);
                        res += ", including vertical scrollbars";
                        break;
                    case 1:
                        w = new TitleBarDecorator(w);
                        res += ", including titlebar";
                        break;
                    case 2:
                        w = new HorizontalScrollBarDecorator(w);
                        res += ", including horizontal scrollbars";
                        break;
                }
                assertEquals(res, w.getDescription());
            }
        }
    }
}
