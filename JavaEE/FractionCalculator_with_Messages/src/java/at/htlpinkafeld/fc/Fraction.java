/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.fc;

import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @author Martin Six
 */
public class Fraction implements Serializable {

    private int num;
    private int denom;

    public Fraction(int num, int denom) {
        if (denom == 0) {
            throw new ArithmeticException("Zero denominator");
        }
        this.num = num;
        this.denom = denom;
        checkSign();
        reduce();
    }

    private void checkSign() {
        if (denom < 0) {
            denom = -denom;
            num = -num;
        }
    }

    private void reduce() {
        BigInteger b1 = BigInteger.valueOf(num);
        BigInteger b2 = BigInteger.valueOf(denom);
        int ggt = b1.gcd(b2).intValue();
        num /= ggt;
        denom /= ggt;
    }

    public static Fraction calc(Character op, Fraction f1, Fraction f2) {
        Fraction res = null;
        switch (op) {
            case '+':
                res = new Fraction(f1.num * f2.denom + f2.num * f1.denom, f1.denom * f2.denom);
                break;
            case '-':
                res = new Fraction(f1.num * f2.denom - f2.num * f1.denom, f1.denom * f2.denom);
                break;
            case '*':
                res = new Fraction(f1.num * f2.num, f1.denom * f2.denom);
                break;
            case '/':
                res = new Fraction(f1.num * f2.denom, f1.denom * f2.num);
                break;
        }
        return res;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getDenom() {
        return denom;
    }

    public void setDenom(int denom) {
        this.denom = denom;
    }
}
