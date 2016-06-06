package at.htlpinkafeld.minesweeperv2.cp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Created by User on 17.05.2016.
 */
public class MSProvider extends ContentProvider {

    public static final int SAVEGAME_LIST = 1;
    public static final int SAVEGAME_ID = 2;
    public static final int SCORE_LIST = 5;
    public static final int SCORE_ID = 6;
    public static final UriMatcher URI_MATCHER;

    static {
        URI_MATCHER=new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(MSContract.AUTHORITY, "savegames", SAVEGAME_LIST);
        URI_MATCHER.addURI(MSContract.AUTHORITY, "savegames/#", SAVEGAME_ID);
        URI_MATCHER.addURI(MSContract.AUTHORITY, "scores", SCORE_LIST);
        URI_MATCHER.addURI(MSContract.AUTHORITY, "scores/#", SCORE_ID);
    }

    private MSDBHelper msDatabaseHelper = null;

    @Override
    public boolean onCreate() {
        msDatabaseHelper=new MSDBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = msDatabaseHelper.getReadableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        boolean useAuthorityUri = false;
        switch (URI_MATCHER.match(uri)) {
            case SAVEGAME_LIST:
                builder.setTables(MSContract.SaveGames.TABLE_NAME);
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = MSContract.SaveGames.SORT_ORDER_DEFAULT;
                }
                break;
            case SAVEGAME_ID:
                builder.setTables(MSContract.SaveGames.TABLE_NAME);
                // limit query to one row at most:
                builder.appendWhere(MSContract.SaveGames._ID + " = " + uri.getLastPathSegment());
                break;
            case SCORE_LIST:
                builder.setTables(MSContract.Scores.TABLE_NAME);
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = MSContract.Scores.SORT_ORDER_DEFAULT;
                }
                break;
            case SCORE_ID:
                builder.setTables(MSContract.Scores.TABLE_NAME);
                // limit query to one row at most:
                builder.appendWhere(MSContract.Scores._ID + " = " + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        Cursor cursor = builder.query(
                db,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
        // if we want to be notified of any changes:
        if (useAuthorityUri) {
            cursor.setNotificationUri(getContext().getContentResolver(), MSContract.CONTENT_URI);
        }
        else {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;

    }

    @Override
    public String getType(Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case SAVEGAME_LIST:
                return MSContract.SaveGames.CONTENT_TYPE;
            case SAVEGAME_ID:
                return MSContract.SaveGames.CONTENT_SAVEGAME_TYPE;
            case SCORE_LIST:
                return MSContract.Scores.CONTENT_TYPE;
            case SCORE_ID:
                return MSContract.Scores.CONTENT_SCORE_TYPE;
            default:
                return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if(URI_MATCHER.match(uri)!=SAVEGAME_LIST && URI_MATCHER.match(uri)!=SCORE_LIST)
            throw new IllegalArgumentException("Unsupported URI for insertion: " + uri);
        SQLiteDatabase db = msDatabaseHelper.getWritableDatabase();

        if (URI_MATCHER.match(uri)==SAVEGAME_LIST){
            long id=db.insert(MSContract.SaveGames.TABLE_NAME,null,values);
            return getUriForId(id,uri);
        } else {
            long id=db.insert(MSContract.Scores.TABLE_NAME,null,values);
            return getUriForId(id,uri);
        }
    }

    private Uri getUriForId(long id, Uri uri) {
        if (id > 0) {
            Uri itemUri = ContentUris.withAppendedId(uri, id);
            return itemUri;
        }
        // s.th. went wrong:
        throw new SQLException("Problem while inserting into uri: " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = msDatabaseHelper.getWritableDatabase();
        int delCount = 0;
        String idStr, where;

        switch (URI_MATCHER.match(uri)) {
            case SAVEGAME_LIST:
                delCount = db.delete(
                        MSContract.SaveGames.TABLE_NAME,
                        selection,
                        selectionArgs);
                break;
            case SAVEGAME_ID:
                idStr = uri.getLastPathSegment();
                where = MSContract.SaveGames._ID + " = " + idStr;
                if (!TextUtils.isEmpty(selection)) {
                    where += " AND " + selection;
                }
                delCount = db.delete(
                        MSContract.SaveGames.TABLE_NAME,
                        where,
                        selectionArgs);
                break;
            case SCORE_LIST:
                delCount = db.delete(
                        MSContract.Scores.TABLE_NAME,
                        selection,
                        selectionArgs);
                break;
            case SCORE_ID:
                idStr = uri.getLastPathSegment();
                where = MSContract.SaveGames._ID + " = " + idStr;
                if (!TextUtils.isEmpty(selection)) {
                    where += " AND " + selection;
                }
                delCount = db.delete(
                        MSContract.Scores.TABLE_NAME,
                        where,
                        selectionArgs);
                break;
        }

        return delCount;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = msDatabaseHelper.getWritableDatabase();
        int updateCount = 0;
        String idStr, where;

        switch (URI_MATCHER.match(uri)) {
            case SAVEGAME_LIST:
                updateCount = db.update(
                        MSContract.SaveGames.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs);
                break;
            case SAVEGAME_ID:
                idStr = uri.getLastPathSegment();
                where = MSContract.SaveGames._ID + " = " + idStr;
                if (!TextUtils.isEmpty(selection)) {
                    where += " AND " + selection;
                }
                updateCount = db.update(
                        MSContract.SaveGames.TABLE_NAME,
                        values,
                        where,
                        selectionArgs);
                break;
            case SCORE_LIST:
                updateCount = db.update(
                        MSContract.SaveGames.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs);
                break;
            case SCORE_ID:
                idStr = uri.getLastPathSegment();
                where = MSContract.SaveGames._ID + " = " + idStr;
                if (!TextUtils.isEmpty(selection)) {
                    where += " AND " + selection;
                }
                updateCount = db.update(
                        MSContract.SaveGames.TABLE_NAME,
                        values,
                        where,
                        selectionArgs);
                break;
        }
        return updateCount;
    }
}
