/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld;

import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

/**
 *
 * @author Martin Six
 */
public class CalculatorBean {

    private final CalculatorService cs;
    private Calculation curCalc;
    private final List<SelectItem> calcSelectList;

    public CalculatorBean() {
        cs = new CalculatorService();
        curCalc = new Calculation();
        calcSelectList = new LinkedList<>();
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

    public double getErg() {
        Double erg = cs.calculate(curCalc);
        return erg;
    }

    public void addCalcEventH(ActionEvent e) {
        if (curCalc.getOp() != 0) {

            calcSelectList.add(new SelectItem(cs.sizeCalcL(), curCalc.toString()));
            cs.addCalc(curCalc);
            curCalc = new Calculation(curCalc);

        }
    }

    public void delCalcEventH(ActionEvent e) {
        if (calcSelectList.size() > 0) {
            UIComponent c = e.getComponent();
            UIComponent list = c.getParent().findComponent("selectedCalc");
            int key = Integer.parseInt(list.getAttributes().get("value").toString());
            cs.removeCalc(key);
            calcSelectList.remove(key);
            for (int i = key; i < calcSelectList.size(); i++) {
                calcSelectList.get(i).setValue(i);
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error:  list empty"));

    }

    public void selectionChangeMethod(ValueChangeEvent event) {
        if (event.getNewValue() != null) {
            this.curCalc = new Calculation(cs.getCalc(Integer.parseInt(event.getNewValue().toString())));
        }
    }

    public double getTotal() {
        return cs.getTotal();
    }

    public List<SelectItem> getCalcSelectList() {
        return calcSelectList;
    }

}
