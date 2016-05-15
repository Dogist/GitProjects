package at.htlpinkafeld.projectmanager.pojo;

import java.util.Date;

/**
 * Created by User on 04.04.2016.
 */
public class CalendarEvent {
    private long calendar_id;
    private String title;
    private Date dtstart;
    private Date dtend;
    private boolean allDay;
    private String duration;
    private String eventTimezone;


    public CalendarEvent(long calendar_id, String title, Date dtstart, boolean allDay, String duration, String eventTimezone) {
        this.calendar_id = calendar_id;
        this.title = title;
        this.dtstart = dtstart;
        this.allDay = allDay;
        this.duration = duration;
        this.eventTimezone = eventTimezone;
    }

    public CalendarEvent(long calendar_id, String title, Date dtstart, Date dtend, boolean allDay, String eventTimezone) {
        this.calendar_id = calendar_id;
        this.title = title;
        this.dtstart = dtstart;
        this.dtend = dtend;
        this.allDay = allDay;
        this.eventTimezone = eventTimezone;
    }

    public long getCalendar_id() {
        return calendar_id;
    }

    public void setCalendar_id(long calendar_id) {
        this.calendar_id = calendar_id;
    }

    public boolean isAllDay() {
        return allDay;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDtstart() {
        return dtstart;
    }

    public void setDtstart(Date dtstart) {
        this.dtstart = dtstart;
    }

    public Date getDtend() {
        return dtend;
    }

    public void setDtend(Date dtend) {
        this.dtend = dtend;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEventTimezone() {
        return eventTimezone;
    }

    public void setEventTimezone(String eventTimezone) {
        this.eventTimezone = eventTimezone;
    }
}
