package at.htlpinkafeld.projectmanager.dao;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract.Calendars;

import at.htlpinkafeld.projectmanager.MainActivity;
import at.htlpinkafeld.projectmanager.pojo.CalendarEvent;
import at.htlpinkafeld.projectmanager.pojo.MyCalendar;


/**
 * Created by User on 04.04.2016.
 */
public class CalendarContentProviderDAO implements CalendarDAO{

    public static final String[] EVENT_PROJECTION = new String[] {
            Calendars._ID,                           // 0
            Calendars.ACCOUNT_NAME,                  // 1
            Calendars.CALENDAR_DISPLAY_NAME,         // 2
            Calendars.OWNER_ACCOUNT                  // 3
    };

    private Context context;

    private static final int PROJECTION_ID_INDEX = 0;
    private static final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
    private static final int PROJECTION_DISPLAY_NAME_INDEX = 2;
    private static final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;

    public CalendarContentProviderDAO() {
    }

    @Override
    public MyCalendar getFirstCalendar(String accountName, String accountType, String ownerAccount) {
        Cursor cur = null;
        ContentResolver cr = MainActivity.context.getContentResolver();
        Uri uri = Calendars.CONTENT_URI;
        String selection = "((" + Calendars.ACCOUNT_NAME + " = ?) AND ("
                + Calendars.ACCOUNT_TYPE + " = ?) AND ("
                + Calendars.OWNER_ACCOUNT + " = ?))";
        String[] selectionArgs = new String[] {"sampleuser@gmail.com", "com.google",
                "sampleuser@gmail.com"};
        // Submit the query and get a Cursor object back.
        cur = cr.query(uri, EVENT_PROJECTION, selection, selectionArgs, null);

        if (cur.moveToNext()){
            long calID = 0;
            String displayName = null;
            String ownerName = null;

            calID = cur.getLong(PROJECTION_ID_INDEX);
            displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
            ownerName = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX);

            return new MyCalendar(calID,displayName,accountType, accountName,ownerName);

        }


        return null;
    }

    @Override
    public void insertEvent(CalendarEvent calendarEvent) {

    }
}
