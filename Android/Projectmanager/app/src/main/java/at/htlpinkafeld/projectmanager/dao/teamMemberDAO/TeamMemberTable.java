package at.htlpinkafeld.projectmanager.dao.teamMemberDAO;

import android.database.sqlite.SQLiteDatabase;

import java.util.logging.Logger;

/**
 * Created by User on 14.03.2016.
 */
public class TeamMemberTable {
    private static final Logger log = Logger.getLogger(TeamMemberTable.class.getName());

    public static final String TABLE_NAME = "TeamMember";
    public static final String COLUMN_TMID ="_ID";
    public static final String COLUMN_TMTitle = "TMTITLE";
    public static final String COLUMN_TMJob = "TMJOB";
    public static final String COLUMN_TMFirst_Name = "TMFIRST_NAME";
    public static final String COLUMN_TMLast_Name = "TMLAST_NAME";
    public static final String COLUMN_TMDepartment = "TMDEPARTMENT";

    public static final String[] ALL_COLUMNS ={COLUMN_TMID,COLUMN_TMTitle,COLUMN_TMJob,COLUMN_TMFirst_Name,COLUMN_TMLast_Name,COLUMN_TMDepartment};

    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME
            + "("
            + COLUMN_TMID + " INTEGER primary key autoincrement, "
            + COLUMN_TMTitle + " TEXT, "
            + COLUMN_TMJob + " TEXT,"
            + COLUMN_TMFirst_Name +" TEXT,"
            + COLUMN_TMLast_Name+ " TEXT,"
            + COLUMN_TMDepartment + " TEXT"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        log.warning(TeamMemberTable.class.getName()
                + ": Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }
}
