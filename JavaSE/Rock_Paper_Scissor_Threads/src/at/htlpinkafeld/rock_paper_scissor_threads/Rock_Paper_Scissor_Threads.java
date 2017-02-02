/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rock_paper_scissor_threads;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Six
 */
public class Rock_Paper_Scissor_Threads {

    private Queue<String> results;
    final static private AtomicInteger COUNTER = new AtomicInteger(1);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(3);
        Exchanger<PossibleForms> e1 = new Exchanger<>();
        Exchanger<PossibleForms> e2 = new Exchanger<>();
        Rock_Paper_Scissor_Threads rpst = new Rock_Paper_Scissor_Threads();

        ExecutorService executor = Executors.newCachedThreadPool();

        Runnable game1 = new GameRunnable(cb, e1);
        Runnable game2 = new GameRunnable(cb, e2);
        executor.execute(game1);
        executor.execute(game2);

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        scheduledExecutorService.scheduleAtFixedRate(new MainGameRunnable(rpst, cb, e1, e2, game1, game2), 0, 1, TimeUnit.SECONDS);
        while (true) {
            try {
                Thread.sleep(5000);
                System.out.println(COUNTER.getAndIncrement() + ". Results:");
                for (String res : rpst.results) {
                    System.out.println(res);
                }
                System.out.println("\n\n");
            } catch (InterruptedException ex) {
                Logger.getLogger(Rock_Paper_Scissor_Threads.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Rock_Paper_Scissor_Threads() {
        this.results = new ConcurrentLinkedQueue<>();
    }

    public synchronized void addResult(String result) {
        results.add(result);
    }

}
