/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.fc;

import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author Martin Six
 */
@ManagedBean
@SessionScoped
public class FractionCalcBean {

    private final List<Character> operands;
    private List<Character> selectedOp;
    private List<FractionStatement> history;

    private Fraction op1;
    private Fraction op2;
    private Character op;
    
    public FractionCalcBean() {
        operands = new LinkedList<>();
        history = new LinkedList<>();
        operands.add('+');
        operands.add('-');
        operands.add('*');
        operands.add('/');
        selectedOp = new LinkedList<>(operands);
        op=operands.get(0);
    }

    public List<Character> getOperands() {
        return operands;
    }

    public List<Character> getSelectedOp() {
        return selectedOp;
    }

    public void setSelectedOp(List<Character> selectedOp) {
        this.selectedOp = selectedOp;
    }

    public List<FractionStatement> getHistory() {
        return history;
    }

    public void setHistory(List<FractionStatement> history) {
        this.history = history;
    }

    public Fraction getOp1() {
        return op1;
    }

    public void setOp1(Fraction op1) {
        this.op1 = op1;
    }

    public Fraction getOp2() {
        return op2;
    }

    public void setOp2(Fraction op2) {
        this.op2 = op2;
    }

    public Character getOp() {
        return op;
    }

    public void setOp(Character op) {
        this.op = op;
    }

    public Fraction getResult() {
        if (op != null && op1 != null && op2 != null) {
            return Fraction.calc(op, op1, op2);
        }
        return null;
    }

    public String addFractionStatEvent() {
        if (op != null && op1 != null && op2 != null) {
            history.add(new FractionStatement(op1, op2, op, getResult()));
        }
        return null;
    }

    public String removeFractionStat(FractionStatement fs) {
        history.remove(fs);
        return null;
    }

}
