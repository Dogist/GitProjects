/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rock_paper_scissor_threads;

import java.util.Random;

/**
 *
 * @author Martin Six
 */
public enum PossibleForms {
    ROCK, PAPER, SCISSOR;

    private static final Random r = new Random();

    public static PossibleForms getRandom() {
        PossibleForms[] pfs = PossibleForms.values();
        return pfs[r.nextInt(pfs.length)];
    }
}
