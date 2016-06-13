package at.htlpinkafeld.minesweeperv2.cp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 17.05.2016.
 */
public class MSDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "minesweeper.db";

    public MSDBHelper(Context context) {
        // This method always returns very quickly. The database is not actually created
        // or opened until one of getWritableDatabase() or getReadableDatabase() is called.
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        SaveGameTable.onCreate(db);
        ScoreTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        SaveGameTable.onUpgrade(db, oldVersion, newVersion);
        ScoreTable.onUpgrade(db, oldVersion, newVersion);
    }
}
