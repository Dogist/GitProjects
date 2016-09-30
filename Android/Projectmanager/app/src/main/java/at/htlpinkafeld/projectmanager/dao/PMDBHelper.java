package at.htlpinkafeld.projectmanager.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import at.htlpinkafeld.projectmanager.dao.activityDAO.ActivityTable;
import at.htlpinkafeld.projectmanager.dao.projectDAO.ProjectTable;
import at.htlpinkafeld.projectmanager.dao.teamMemberDAO.TeamMemberTable;


/**
 * Created by User on 29.02.2016.
 */
public class PMDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "projectM.db";


    public PMDBHelper(Context context) {
        // This method always returns very quickly. The database is not actually created
        // or opened until one of getWritableDatabase() or getReadableDatabase() is called.
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        ProjectTable.onCreate(db);
        ActivityTable.onCreate(db);
        TeamMemberTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        ProjectTable.onUpgrade(db, oldVersion, newVersion);
        ActivityTable.onUpgrade(db, oldVersion, newVersion);
        TeamMemberTable.onUpgrade(db, oldVersion, newVersion);
    }
}
