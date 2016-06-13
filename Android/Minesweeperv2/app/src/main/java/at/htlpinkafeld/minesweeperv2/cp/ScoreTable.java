package at.htlpinkafeld.minesweeperv2.cp;

import android.database.sqlite.SQLiteDatabase;

import java.util.logging.Logger;

/**
 * Created by User on 17.05.2016.
 */
public class ScoreTable {
    public static final String TABLE_NAME = "Scores";
    public static final String COLUMN_SID = "_id";
    public static final String COLUMN_PNAME = "PNAME";
    public static final String COLUMN_TIME = "TIME";
    public static final String COLUMN_HEIGHT = "HEIGHT";
    public static final String COLUMN_WIDTH = "WIDTH";
    public static final String COLUMN_NUMMINES = "NUMMINES";
    public static final String COLUMN_FIELDS_CLEARED = "FIELDSCLEARED";
    public static final String[] ALL_COLUMS = {COLUMN_SID, COLUMN_PNAME, COLUMN_TIME, COLUMN_HEIGHT, COLUMN_WIDTH, COLUMN_NUMMINES, COLUMN_FIELDS_CLEARED};
    public static final String DATABASE_CREATE = "create table "
            + TABLE_NAME
            + "("
            + COLUMN_SID + " INTEGER primary key autoincrement, "
            + COLUMN_PNAME + " TEXT, "
            + COLUMN_TIME + " INTEGER, "
            + COLUMN_HEIGHT + " INTEGER,"
            + COLUMN_WIDTH + " INTEGER,"
            + COLUMN_NUMMINES + " INTEGER,"
            + COLUMN_FIELDS_CLEARED + " INTEGER"
            + ");";
    private static final Logger log = Logger.getLogger(ScoreTable.class.getName());

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        log.warning(ScoreTable.class.getName()
                + ": Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }

}