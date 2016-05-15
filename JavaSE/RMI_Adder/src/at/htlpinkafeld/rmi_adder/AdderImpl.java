/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rmi_adder;

/**
 *
 * @author Martin Six
 */
public class AdderImpl implements Adder {

    @Override
    public int add(int x, int y) {
        return x + y;
    }
}
