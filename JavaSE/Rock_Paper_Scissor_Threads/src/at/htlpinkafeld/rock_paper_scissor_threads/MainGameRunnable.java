/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rock_paper_scissor_threads;

import java.time.LocalTime;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Six
 */
public class MainGameRunnable implements Runnable {

    static final private AtomicInteger COUNTER = new AtomicInteger(1);

    Rock_Paper_Scissor_Threads master;

    private final CyclicBarrier cb;
    private final Exchanger<PossibleForms> e1;
    private final Exchanger<PossibleForms> e2;
    private final Runnable game1;
    private final Runnable game2;

    MainGameRunnable(Rock_Paper_Scissor_Threads master, CyclicBarrier cb, Exchanger<PossibleForms> e1, Exchanger<PossibleForms> e2, Runnable game1, Runnable game2) {
        this.master = master;
        this.cb = cb;
        this.e1 = e1;
        this.e2 = e2;
        this.game1 = game1;
        this.game2 = game2;
    }

    @Override
    public void run() {

        try {
            cb.await();
            determineWinner(e1.exchange(null), e2.exchange(null));

        } catch (InterruptedException | BrokenBarrierException ex) {
            Logger.getLogger(MainGameRunnable.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void determineWinner(PossibleForms f1, PossibleForms f2) {
        String result = LocalTime.now() + " | " + COUNTER.getAndIncrement() + ". Versuch: \t";
        switch (f1) {
            case PAPER:
                switch (f2) {
                    case PAPER:
                        result += "Unentschieden mit Papier";
                        break;
                    case ROCK:
                        result += game1 + " hat mit Papier gewonnen";
                        break;
                    case SCISSOR:
                        result += game2 + " hat mit Schere gewonnen";
                        break;
                }
                break;
            case ROCK:
                switch (f2) {
                    case PAPER:
                        result += game2 + " hat mit Papier gewonnen";
                        break;
                    case ROCK:
                        result += "Unentschieden mit Stein";
                        break;
                    case SCISSOR:
                        result += game1 + " hat mit Stein gewonnen";
                        break;
                }
                break;
            case SCISSOR:
                switch (f2) {
                    case PAPER:
                        result += game1 + " hat mit Schere gewonnen";
                        break;
                    case ROCK:
                        result += game2 + " hat mit Stein gewonnen";
                        break;
                    case SCISSOR:
                        result += "Unentschieden mit Schere";
                        break;
                }
                break;
        }
//        System.out.println(result);
        master.addResult(result);
    }

}
