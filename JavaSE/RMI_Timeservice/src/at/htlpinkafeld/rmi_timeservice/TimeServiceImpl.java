/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rmi_timeservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Martin Six
 */
public class TimeServiceImpl extends UnicastRemoteObject implements TimeService {

    public TimeServiceImpl() throws RemoteException {
    }

    public String getTime() throws RemoteException {
        GregorianCalendar cal = new GregorianCalendar();
        StringBuffer sb = new StringBuffer();
        sb.append(cal.get(Calendar.HOUR_OF_DAY));
        sb.append(":" + cal.get(Calendar.MINUTE));
        sb.append(":" + cal.get(Calendar.SECOND));
        return sb.toString();
    }

    public TimeStore storeTime(TimeStore store) throws RemoteException {
        store.setTime(getTime());
        return store;
    }
}
