/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.dtconfig;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author Martin Six
 */
public class DateBean {
    private Date now;
    private Date holiday;
    private List<SelectItem> possVal;

    /**
     * Creates a new instance of dtconfig
     */
    public DateBean() {
        possVal=new ArrayList<>();
        possVal.add(new SelectItem(new GregorianCalendar(2016 ,Calendar.DECEMBER,25).getTime(),"Christmas"));
        possVal.add(new SelectItem(new GregorianCalendar(2016 ,Calendar.MARCH,29).getTime(),"Easter Monday"));
        possVal.add(new SelectItem(new GregorianCalendar(2016 ,Calendar.JANUARY,7).getTime(),"Epiphany"));
        possVal.add(new SelectItem(new GregorianCalendar(2016 ,Calendar.NOVEMBER,2).getTime(),"Hallowmas"));
    }

    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }
    
    public Date getHoliday() {
        return holiday;
    }

    public void setHoliday(Date holiday) {
        this.holiday = holiday;
    }

    public List<SelectItem> getPossVal() {
        return possVal;
    }

    public void setPossVal(List<SelectItem> possVal) {
        this.possVal = possVal;
    }
    
}
