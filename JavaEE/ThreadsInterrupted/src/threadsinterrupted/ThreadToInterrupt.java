/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadsinterrupted;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Six
 */
public class ThreadToInterrupt extends Thread {

    private char letter;

    public char getChar() {
        return letter;
    }

    @Override
    public void run() {
        while (true) {
            if (this.isInterrupted()) {
                System.out.println(this.getName() + ": Thread wurde unterbrochen");
                break;
            }
            this.letter = (char) ((char) 'A' + Math.floor(Math.random() * 24));
            System.out.println(" "+this.getName() + ": "+letter);
            try {
                Thread.sleep(ThreadsInterrupted.PAUSE);
            } catch (InterruptedException ex) {
//                try {
//                    //zum Testen
//                    Thread.sleep(0);
//                } catch (InterruptedException ex1) {
//                    Logger.getLogger(ThreadToInterrupt.class.getName()).log(Level.SEVERE, null, ex1);
//                }
                
                this.interrupt();
            }
        }
    }

}
