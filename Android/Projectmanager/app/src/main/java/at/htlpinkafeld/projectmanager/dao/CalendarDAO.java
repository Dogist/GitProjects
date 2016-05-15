package at.htlpinkafeld.projectmanager.dao;


import at.htlpinkafeld.projectmanager.pojo.CalendarEvent;
import at.htlpinkafeld.projectmanager.pojo.MyCalendar;

/**
 * Created by User on 04.04.2016.
 */
public interface CalendarDAO {
    public abstract MyCalendar getFirstCalendar(String accountName, String accountType, String ownerAccount);
    public abstract void insertEvent(CalendarEvent calendarEvent);
}
