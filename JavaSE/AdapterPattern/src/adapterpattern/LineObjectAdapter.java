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
public class LineObjectAdapter implements Shape{

    private LegacyLine l=new LegacyLine();
        
    @Override
    public void draw(int x, int y, int w, int h) {
        l.draw(x, y, x+w, y+h);
    }
    
}
