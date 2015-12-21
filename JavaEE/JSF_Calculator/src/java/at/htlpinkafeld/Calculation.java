/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld;

/**
 *
 * @author Martin Six
 */
public class Calculation {
    private double leOp;
    private double riOp;
    private char op;

    public Calculation() {
    }

    public Calculation(double lOp, double rOp, char op) {
        this.leOp = lOp;
        this.riOp = rOp;
        this.op = op;
    }

    public double getLeOp() {
        return leOp;
    }

    public void setLeOp(double leOp) {
        this.leOp = leOp;
    }

    public double getRiOp() {
        return riOp;
    }

    public void setRiOp(double riOp) {
        this.riOp = riOp;
    }

    public char getOp() {
        return op;
    }

    public void setOp(char op) {
        this.op = op;
    }

    
    
}
