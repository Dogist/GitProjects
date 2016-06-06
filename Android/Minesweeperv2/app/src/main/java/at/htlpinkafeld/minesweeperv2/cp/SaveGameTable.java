package at.htlpinkafeld.minesweeperv2.cp;

import android.database.sqlite.SQLiteDatabase;

import java.util.logging.Logger;

/**
 * Created by User on 17.05.2016.
 */
public class SaveGameTable {
    private static final Logger log = Logger.getLogger(SaveGameTable.class.getName());

    public static final String TABLE_NAME = "SaveGames";
    public static final String COLUMN_SGID = "_id";
    public static final String COLUMN_BOARD = "BOARD";
    public static final String COLUMN_NUMMINES = "NUMMINES";
    public static final String COLUMN_FIELDSUNCOVERED = "FIELDSUNCOVERED";
    public static final String COLUMN_TIME = "TIME";

    public static final String[] ALL_COLUMS = {COLUMN_SGID, COLUMN_BOARD, COLUMN_NUMMINES, COLUMN_FIELDSUNCOVERED, COLUMN_TIME};

    public static final String DATABASE_CREATE = "create table "
            + TABLE_NAME
            + "("
            + COLUMN_SGID + " INTEGER primary key autoincrement, "
            + COLUMN_BOARD + " TEXT, "
            + COLUMN_FIELDSUNCOVERED  + " INTEGER, "
            + COLUMN_NUMMINES + " INTEGER,"
            + COLUMN_TIME+ " INTEGER"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        log.warning(SaveGameTable.class.getName()
                + ": Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }

}
