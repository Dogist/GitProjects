/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.proxy;

/**
 *
 * @author Martin Six
 */
public class Shutdown implements Command {
    
    @Override
    public void run(String[] args) {
        System.exit(0);
    }
    
}
