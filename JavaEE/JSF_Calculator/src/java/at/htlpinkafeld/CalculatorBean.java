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
public class CalculatorBean {

    private final CalculatorService cs;
    private Calculation curCalc;

    public CalculatorBean() {
        cs = new CalculatorService();
        curCalc = new Calculation();
    }

    public CalculatorService getCs() {
        return cs;
    }

    public Calculation getCurCalc() {
        return curCalc;
    }

    public void setCurCalc(Calculation curCalc) {
        this.curCalc = curCalc;
    }

}
