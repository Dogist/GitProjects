/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public class CalculatorService {

    private final List<Calculation> calcList;
    private static final List<Character> calcOp=new LinkedList<>();

    public CalculatorService() {
        this.calcList = new LinkedList<>();
        calcOp.add('+');
        calcOp.add('-');
        calcOp.add('*');
        calcOp.add('/');
        calcOp.add('%');
        calcOp.add('^');
    }

    public void addCalc(Calculation c) {
        calcList.add(c);
    }

    public static Double calculate(Calculation c) {
        Double erg = null;

        if(calcOp.contains(c.getOp()))
        switch (c.getOp()) {
            case '+':
                erg = c.getLeOp() + c.getRiOp();
                break;
            case '-':
                erg = c.getLeOp() - c.getRiOp();
                break;
            case '*':
                erg = c.getLeOp() * c.getRiOp();
                break;
            case '/':
                erg = c.getLeOp() / c.getRiOp();
                break;
            case '^':
                erg = Math.pow(c.getLeOp(), c.getRiOp());
                break;
            case '%':
                erg = c.getLeOp() % c.getRiOp();
                break;
        }
        return erg;
    }
}
