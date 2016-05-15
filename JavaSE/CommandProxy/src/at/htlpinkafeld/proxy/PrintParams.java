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
public class PrintParams implements Command {

    @Override
    public void run(String[] args) {
        String res = "";
        for (String s : args) {
            res += s + ";";
        }
        System.out.print(res);
    }

}
