package at.htlpinkafeld.projectmanager.dao.projectDAO;

import android.database.sqlite.SQLiteDatabase;

import java.util.logging.Logger;

/**
 * Created by User on 07.03.2016.
 */
public class ProjectTable {
    private static final Logger log = Logger.getLogger(ProjectTable.class.getName());

    public static final String TABLE_NAME = "Project";
    public static final String COLUMN_PID ="_ID";
    public static final String COLUMN_PNAME = "PRNAME";
    public static final String COLUMN_PCONTR = "PRCONTRACTOR";
    public static final String COLUMN_PRPROCMOD = "PRPROCMOD";
    public static final String COLUMN_PRSTARTDATE = "PRSTARTDATE";
    public static final String COLUMN_PRENDDATE = "PRENDDATE";
    public static final String COLUMN_PRDESC = "PRDESC";

    public static final String[] ALL_COLUMNS ={COLUMN_PID,COLUMN_PNAME , COLUMN_PCONTR,COLUMN_PRPROCMOD, COLUMN_PRSTARTDATE, COLUMN_PRENDDATE, COLUMN_PRDESC};

    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME
            + "("
            + COLUMN_PID + " INTEGER primary key autoincrement, "
            + COLUMN_PNAME + " TEXT, "
            + COLUMN_PCONTR + " TEXT, "
            + COLUMN_PRPROCMOD +" TEXT,"
            + COLUMN_PRSTARTDATE+ " INTEGER,"
            + COLUMN_PRENDDATE + " INTEGER,"
            + COLUMN_PRDESC +" TEXT"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        log.warning(ProjectTable.class.getName()
                + ": Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }


}
