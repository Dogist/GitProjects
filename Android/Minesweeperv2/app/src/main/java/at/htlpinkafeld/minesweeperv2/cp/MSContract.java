package at.htlpinkafeld.minesweeperv2.cp;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by User on 17.05.2016.
 */
public class MSContract {
    /**
     * The authority of the minesweeper provider.
     */
    public static final String AUTHORITY = "at.htlpinkafeld.minesweeperv2";

    /**
     * The content URI for the top-level
     * minesweeper authority.
     */
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    /**
     * permission to set in the attribute android:readPermission
     * of the provider tag
     */
    public static final String PERMISSION_READ = AUTHORITY + ".READ";
    /**
     * permission to set in the attribute android:writePermission
     * of the provider tag
     */
    public static final String PERMISSION_WRITE =AUTHORITY + ".WRITE";

    public static final class SaveGames implements BaseColumns {
        public static final String TABLE_NAME = "savegames";

        public static final String BOARD = SaveGameTable.COLUMN_BOARD;
        public static final String NUMMINES = SaveGameTable.COLUMN_NUMMINES;
        public static final String FIELDSUNCOVERED = SaveGameTable.COLUMN_FIELDSUNCOVERED;
        public static final String TIME = SaveGameTable.COLUMN_TIME;


        public static final Uri CONTENT_URI = Uri.withAppendedPath(MSContract.CONTENT_URI, TABLE_NAME);

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE+"/vnd.at.htlpinkafeld.minesweeperv2_savegames";

        public static final String CONTENT_SAVEGAME_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE+ "/vnd.at.htlpinkafeld.minesweeperv2_savegames";

        public static final String[] PROJECTION_ALL = {_ID,BOARD, NUMMINES, FIELDSUNCOVERED,TIME};

        public static final String SORT_ORDER_DEFAULT = _ID + " ASC";
    }

    public static final class Scores implements BaseColumns {
        public static final String TABLE_NAME = "scores";

        public static final String PNAME = ScoreTable.COLUMN_PNAME;
        public static final String TIME = ScoreTable.COLUMN_TIME;
        public static final String HEIGHT = ScoreTable.COLUMN_HEIGHT;
        public static final String WIDTH = ScoreTable.COLUMN_WIDTH;
        public static final String NUMMINES = ScoreTable.COLUMN_NUMMINES;
        public static final String FIELDS_CLEARED = ScoreTable.COLUMN_FIELDS_CLEARED;

        public static final Uri CONTENT_URI = Uri.withAppendedPath(MSContract.CONTENT_URI, TABLE_NAME);

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE+"/vnd.at.htlpinkafeld.minesweeperv2_scores";

        public static final String CONTENT_SCORE_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE+ "/vnd.at.htlpinkafeld.minesweeperv2_scores";

        public static final String[] PROJECTION_ALL = {_ID, PNAME, TIME, HEIGHT, WIDTH,  NUMMINES, FIELDS_CLEARED};

        public static final String SORT_ORDER_DEFAULT = TIME + " DESC";
    }

}
