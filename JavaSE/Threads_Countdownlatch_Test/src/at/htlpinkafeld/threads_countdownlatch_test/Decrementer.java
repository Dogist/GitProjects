/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.threads_countdownlatch_test;

import java.util.concurrent.CountDownLatch;

/**
 *
 * @author Martin Six
 */
public class Decrementer implements Runnable {

    CountDownLatch latch = null;

    public Decrementer(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {

        try {
            while (latch.getCount() > 0) {
                Thread.sleep(1000);
                this.latch.countDown();
                System.out.println("Countdown Latch: " + latch.getCount());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
