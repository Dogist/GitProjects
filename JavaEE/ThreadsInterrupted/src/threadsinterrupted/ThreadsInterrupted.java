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
public class ThreadsInterrupted {

    public static final int NUMBER_OF_COMPARES = 100;
    public static final int PAUSE = 20;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ThreadToInterrupt thread = new ThreadToInterrupt();
        thread.start();
        //wait 10ms
        try {
            Thread.sleep(PAUSE);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadsInterrupted.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < 100; i++) {
            char c=thread.getChar();
            
            if (c == 'A') {                
                break;
            }
            System.out.print(c);
            //wait 0-9 ms
            try {
                Thread.sleep((long) Math.floor(Math.random() * PAUSE));
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadsInterrupted.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        thread.interrupt();
        if (thread.getChar() == 'A') {
            System.out.println("Der Thread " + thread.getName() + " wird unterbrochen (A gefunden)");
        } else {
            System.out.println("Der Thread " + thread.getName() + " wird unterbrochen (bei 100 Abfragen kein A gefunden)");
        }
        for (int i = 1; thread.isAlive(); i++) {
            System.out.println("Der Thread " + thread.getName() + " ist " + i + "-mal erreichbar");
            System.out.println(thread.getState().toString());
        }
        System.out.println(thread.getState().toString());

    }

}
