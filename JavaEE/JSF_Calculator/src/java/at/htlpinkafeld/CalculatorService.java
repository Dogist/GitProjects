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
    private final List<Character> calcOp = new LinkedList<>();

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

    public Double calculate(Calculation c) {
        Double erg = 0.0;

        if (c != null && calcOp.contains(c.getOp())) {
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
                default:
                    erg = null;
            }
        }

        return erg;
    }

    public List<Character> getCalcOp() {
        return calcOp;
    }

//    public SelectItem getSelectItemFromLab(String lab){
//        for(SelectItem i:calcList){
//            if(i.getLabel().contentEquals(lab))
//                return i;
//        }            
//        return null;
//    }
    public Calculation getCalc(int i) {
        return calcList.get(i);
    }

    public Calculation removeCalc(int index) {
        return calcList.remove(index);
    }

    public int sizeCalcL() {
        return calcList.size();
    }

    public double getTotal() {
        double erg = 0.0;
        for (Calculation c : calcList) {
            erg += calculate(c);
        }
        return erg;
    }
}
