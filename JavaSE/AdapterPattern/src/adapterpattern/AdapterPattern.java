/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adapterpattern;

/**
 *
 * @author Martin Six
 */
public class AdapterPattern {

    public static void main(String[] args) {
//        Shape[] shapes
//                = {
//                    new LineClassAdapter(), new RectangleClassAdapter()
//                };

        Shape[] shapes
                = {
                    new LineObjectAdapter(), new RectangleObjectAdapter()
                };
        // A begin and end point from a graphical editor

        int x1 = 10, y1 = 20;
        int x2 = 30, y2 = 60;
        for (int i = 0; i < shapes.length; ++i) {
            shapes[i].draw(x1, y1, x2, y2);
        }
    }

}
