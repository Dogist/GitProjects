package at.htlpinkafeld.projectmanager.dao.activityDAO;

import android.database.sqlite.SQLiteDatabase;

import java.util.logging.Logger;

import at.htlpinkafeld.projectmanager.dao.projectDAO.ProjectTable;

/**
 * Created by User on 07.03.2016.
 */
public class ActivityTable {
    private static final Logger log = Logger.getLogger(ActivityTable.class.getName());

    public static final String TABLE_NAME = "Activity";
    public static final String COLUMN_AID ="Activity_ID";
    public static final String COLUMN_PID = "Project_ID";
    public static final String COLUMN_ANAME = "ActName";
    public static final String COLUMN_APRIORITY = "ActPriority";
    public static final String COLUMN_ASTARTDATE = "ActStartDate";
    public static final String COLUMN_AENDDATE = "ActEndDate";
    public static final String COLUMN_AEFFORT = "ActEffort";

    public static final String[] ALL_COLUMNS ={COLUMN_AID,COLUMN_PID , COLUMN_ANAME,COLUMN_APRIORITY, COLUMN_ASTARTDATE, COLUMN_AENDDATE, COLUMN_AEFFORT};

    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME
            + "("
            + COLUMN_AID + " INTEGER primary key autoincrement, "
            + COLUMN_PID + " INTEGER, "
            + COLUMN_ANAME + " TEXT,"
            + COLUMN_APRIORITY +" TEXT,"
            + COLUMN_ASTARTDATE+ " INTEGER,"
            + COLUMN_AENDDATE + " INTEGER,"
            + COLUMN_AEFFORT +" INTEGER,"
            + "FOREIGN KEY("+COLUMN_PID+") REFERENCES Project(" + ProjectTable.COLUMN_PID + ")"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        log.warning(ActivityTable.class.getName()
                + ": Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }
}
