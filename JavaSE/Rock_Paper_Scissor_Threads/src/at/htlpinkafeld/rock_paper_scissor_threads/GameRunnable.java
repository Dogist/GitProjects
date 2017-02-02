/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rock_paper_scissor_threads;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Six
 */
public class GameRunnable implements Runnable {

    private Thread ownThread;

    private final CyclicBarrier barrier;
    private final Exchanger exchanger;

    public GameRunnable(CyclicBarrier barrier, Exchanger exchanger) {
        this.barrier = barrier;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        ownThread = Thread.currentThread();
        try {
            for (;;) {
                barrier.await();
                exchanger.exchange(PossibleForms.getRandom());
            }
        } catch (InterruptedException | BrokenBarrierException ex) {
            Logger.getLogger(GameRunnable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return ownThread.getName();
    }

}
