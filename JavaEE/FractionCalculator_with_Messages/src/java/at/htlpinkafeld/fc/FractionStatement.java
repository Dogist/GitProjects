/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.fc;

import java.io.Serializable;

/**
 *
 * @author Martin Six
 */
public class FractionStatement implements Serializable {

    private Fraction lval;
    private Fraction rval;
    private char op;
    private Fraction result;

    public FractionStatement(Fraction lval, Fraction rval, char op, Fraction result) {
        this.lval = lval;
        this.rval = rval;
        this.op = op;
        this.result = result;
    }

    public Fraction getLval() {
        return lval;
    }

    public void setLval(Fraction lval) {
        this.lval = lval;
    }

    public Fraction getRval() {
        return rval;
    }

    public void setRval(Fraction rval) {
        this.rval = rval;
    }

    public char getOp() {
        return op;
    }

    public void setOp(char op) {
        this.op = op;
    }

    public Fraction getResult() {
        return result;
    }

    public void setResult(Fraction result) {
        this.result = result;
    }
}
